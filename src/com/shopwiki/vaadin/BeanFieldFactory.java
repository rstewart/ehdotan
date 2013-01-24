package com.shopwiki.vaadin;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

/**
 * @owner rstewart
 */
public class BeanFieldFactory<T> extends DefaultFieldFactory {

    @Override
    public Field createField(Item item, Object propertyId, Component uiContext) {
        @SuppressWarnings("unchecked")
        BeanItem<T> bean = (BeanItem<T>)item;
        Field field = createField(bean.getBean(), propertyId);
        if (field == DUMMY_FIELD) {
            return super.createField(item, propertyId, uiContext);
        }
        return field;
    }

    private static final Field DUMMY_FIELD = new TextField("DUMMY_FIELD");

    @SuppressWarnings("unused")
    protected Field createField(T bean, Object propertyId) {
        return DUMMY_FIELD;
    }
}
