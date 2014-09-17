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
import com.vaadin.ui.TextField;

/**
 * @owner rtewart
 */
public class InlineTextForm extends InlineForm<String> {

    public interface Handler extends InlineForm.Handler<String> { }

    public final TextField textField;
    private boolean clearOnSubmit = false;

    public InlineTextForm(String submitCaption, Handler handler) {
        super(new TextField(), submitCaption, handler);

        this.textField = (TextField)super.getField();

        textField.addListener(new FieldEvents.FocusListener() {
            @Override
            public void focus(FocusEvent event) {
                textField.selectAll();
            }
        });

        //textField.setNullRepresentation("");
        //textField.setNullSettingAllowed(true);
    }

    public void setClearOnSubmit(boolean b) {
        clearOnSubmit = b;
    }

    public String getTextValue() {
        return (String)textField.getValue();
    }

    @Override
    public void handle() {
        super.handle();

        if (clearOnSubmit) {
            textField.setValue("");
        }
    }
}
