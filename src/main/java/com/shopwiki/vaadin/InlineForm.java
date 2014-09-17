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

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

/**
 * @owner rtewart
 */
public class InlineForm<T> extends HorizontalLayout {

    public interface Handler<T> {
        void handle(T value) throws Exception;
    }

    private final AbstractField field;
    private final Handler<T> handler;

    public static <T> InlineForm<T> create(AbstractField field, String submitCaption, Handler<T> handler) {
        return new InlineForm<T>(field, submitCaption, handler);
    }

    protected InlineForm(final AbstractField field, String submitCaption, Handler<T> handler) {
        this.field = field;
        this.handler = handler;

        Button submitButton = new Button(submitCaption, this, "handle");

        //submitButton.setClickShortcut(KeyCode.ENTER);
        // Putting the shortcut listener on the textField is better since it can check it the textField is the target
        field.addShortcutListener(new ShortcutListener(submitCaption, KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                if (target == field) {
                    handle();
                }
            }
        });

        addComponent(field);
        addComponent(submitButton);
        setSpacing(true);
    }

    protected AbstractField getField(){
        return field;
    }

    public T getValue() {
        return (T)field.getValue();
    }

    public void handle() {
        try {
            handler.handle(getValue());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setValueAndHandle(T value) {
        field.setValue(value);
        handle();
    }
}
