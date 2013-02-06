/*
 * Copyright [2013] [ShopWiki]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shopwiki.vaadin;

import java.util.*;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.*;

/**
 * Copied from shopwiki repo
 *
 * @owner rstewart
 */
public class UrlParams {

    private ListMultimap<String,String> map = ArrayListMultimap.create();

    public UrlParams() { }

    public UrlParams(String queryString) {
        if (queryString == null) {
            return;
        }

        Splitter kvSplitter = Splitter.on('=');

        for (String param : Splitter.on('&').split(queryString)) {
            List<String> kv = Lists.newArrayList(kvSplitter.split(param));
            if (kv.size() >= 2) {
                add(kv.get(0), kv.get(1));
            }
        }
    }

    public UrlParams add(String key, Object value) {
        if (value != null) {
            map.put(key, value.toString());
        }
        return this;
    }

    public UrlParams remove(String key) {
        map.removeAll(key);
        return this;
    }

    public UrlParams put(String key, Object value) {
        remove(key);
        add(key, value);
        return this;
    }

    public Set<String> keySet() {
        return ImmutableSet.copyOf(map.keySet());
    }

    public List<String> getList(String key) {
        return map.get(key);
    }

    public String get(String key) {
        List<String> list = getList(key);
        if (list.isEmpty()) {
            return null;
        }
        int i = list.size() - 1; // last wins
        return list.get(i);
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<String>(map.size());
        for (Map.Entry<String,String> entry : map.entries()) {
            list.add(entry.getKey() + "=" + String.valueOf(entry.getValue()));
        }
        return Joiner.on('&').join(list);
    }
}
