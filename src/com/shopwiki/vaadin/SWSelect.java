package com.shopwiki.vaadin;

import java.util.Collection;

import com.vaadin.data.Container;
import com.vaadin.ui.Select;

/**
 * Why are there no accessor methods for Select's pageLength !?
 *
 * @owner rstewart
 */
public class SWSelect extends Select {

	public SWSelect() {
		super();
	}

	public SWSelect(String caption) {
		super(caption);
	}

    public SWSelect(String caption, Collection<?> options) {
        super(caption, options);
    }

    public SWSelect(String caption, Container dataSource) {
        super(caption, dataSource);
    }

	public int getPageLength() {
		return super.pageLength;
	}

	public void setPageLength(int pageLength) {
		super.pageLength = pageLength;
	}
}
