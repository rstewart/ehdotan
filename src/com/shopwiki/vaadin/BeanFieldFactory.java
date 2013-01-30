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
