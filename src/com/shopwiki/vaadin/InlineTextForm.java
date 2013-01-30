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

import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

/**
 * @owner rtewart
 */
public class InlineTextForm extends HorizontalLayout {

    public interface Handler {
        public void handle(String text) throws Exception;
    }

    public final TextField textField = new TextField();

    private final Handler handler;

    private boolean clearOnSubmit = false;

    public InlineTextForm(String submitCaption, Handler handler) {
        this.handler = handler;

        Button submitButton = new Button(submitCaption, this, "handle");

        //submitButton.setClickShortcut(KeyCode.ENTER);
        // Putting the shortcut listener on the textField is better since it can check it the textField is the target
        textField.addShortcutListener(new ShortcutListener(submitCaption, KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                if (target == textField) {
                    handle();
                }
            }
        });

        textField.addListener(new FieldEvents.FocusListener() {
            @Override
            public void focus(FocusEvent event) {
                textField.selectAll();
            }
        });

        //textField.setNullRepresentation("");
        //textField.setNullSettingAllowed(true);

        addComponent(textField);
        addComponent(submitButton);
        setSpacing(true);
    }

    public void setClearOnSubmit(boolean b) {
        clearOnSubmit = b;
    }

    public String getTextValue() {
        return (String)textField.getValue();
    }

    public void handle() {
        try {
            handler.handle(getTextValue());
            if (clearOnSubmit) {
                textField.setValue("");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setValueAndHandle(String s) {
        textField.setValue(s);
        handle();
    }
}
