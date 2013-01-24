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
