package com.shopwiki.vaadin;

import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;

/**
 * @owner rstewart
 */
public class BeanChangeListener<T> implements ValueChangeListener {

    public static interface BeanChangeHandler<T> {
        public void handle(BeanItem<T> item);
    }

    private final Container container;
    private final BeanChangeHandler<T> handler;

    public BeanChangeListener(Container container, BeanChangeHandler<T> handler) {
        this.container = container;
        this.handler = handler;
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        Object id = event.getProperty().getValue();
        BeanItem<T> item = (BeanItem<T>)container.getItem(id);
        handler.handle(item);
    }

    public static <T,F extends Field & Container> BeanChangeListener<T> add(F field, BeanChangeHandler<T> handler) {
        BeanChangeListener<T> listener = new BeanChangeListener<T>(field, handler);
        field.addListener(listener);
        return listener;
    }
}
