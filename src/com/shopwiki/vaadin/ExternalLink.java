package com.shopwiki.vaadin;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Link;

/**
 * @owner rstewart
 */
public class ExternalLink extends Link {

	public ExternalLink(String caption, String url) {
		super(caption, new ExternalResource(url));
		setTargetName("_blank");
	}
}
