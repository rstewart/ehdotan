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

import com.vaadin.ui.UriFragmentUtility;
import com.vaadin.ui.UriFragmentUtility.FragmentChangedEvent;
import com.vaadin.ui.UriFragmentUtility.FragmentChangedListener;
import com.vaadin.ui.Window;

/**
 * @owner rstewart
 */
public class UrlFragWindow extends Window { // TODO: Move to ehdotan.jar

    private final UriFragmentUtility uriFragUtil = new UriFragmentUtility();
    private volatile boolean ignoreUrlFragChange = false;

    protected UrlFragWindow(String name) {
        super(name);

        addComponent(uriFragUtil);
        uriFragUtil.addListener(new FragmentChangedListener() {
            @Override
            public void fragmentChanged(FragmentChangedEvent source) {
                String frag = null;
                synchronized(uriFragUtil) {
                    if (ignoreUrlFragChange) {
                        ignoreUrlFragChange = false;
                        return;
                    }
                    frag = uriFragUtil.getFragment();
                }
                handleUrlFragChange(new UrlParams(frag));
            }
        });
    }

    public void setUrlFrag(UrlParams params) {
        synchronized(uriFragUtil) {
            ignoreUrlFragChange = true;
            uriFragUtil.setFragment(params.toString());
        }
    }

    public UrlParams getUrlParams() {
        return new UrlParams(uriFragUtil.getFragment());
    }

    @SuppressWarnings("unused")
    protected void handleUrlFragChange(UrlParams params) {
        // Override this!
    }
}
