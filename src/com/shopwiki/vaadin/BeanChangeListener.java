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
