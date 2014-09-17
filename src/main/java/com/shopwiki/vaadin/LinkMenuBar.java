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

import com.vaadin.ui.MenuBar;

/**
 * @owner rstewart
 */
public class LinkMenuBar extends MenuBar {

    private final String urlPrefix;

    public LinkMenuBar(String urlPrefix) {
        setHtmlContentAllowed(true);
        this.urlPrefix = urlPrefix;
    }

    public MenuItem addLinkMenu(String caption) {
        return addItem(caption, null);
    }

    public MenuItem addLink(String caption, String path) {
        String html = makeLink(caption, path);
        return addItem(html, null);
    }

    private String makeLink(String caption, String path) {
        return "<A href='" + urlPrefix + path + "'>" + caption + "</A>";
    }

    public MenuItem addLink(MenuItem menuItem, String caption, String path) {
        String html = makeLink(caption, path);
        return menuItem.addItem(html, null);
    }
}
