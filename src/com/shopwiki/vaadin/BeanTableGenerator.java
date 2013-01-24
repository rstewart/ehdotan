package com.shopwiki.vaadin;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;

/**
 * @owner rstewart
 */
public abstract class BeanTableGenerator<B> {

    protected B getBean(Table source, Object itemId) {
        BeanItem<B> item = (BeanItem<B>)source.getItem(itemId);
        return item.getBean();
    }
}
