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

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ConfirmDialog extends Window {

	public static interface Handler {
		public void handle() throws Exception;
	}

	private final Window parentWindow;
	private final Handler handler;
	private final Button confirmButton;
	private final Button cancelButton = new Button("Cancel", this , "cancel");

	public ConfirmDialog(Window parentWindow, Handler handler, String confirmButtonText, String caption, String... messages) {
		setModal(true);
		this.parentWindow = parentWindow;
		this.handler = handler;
		confirmButton = new Button(confirmButtonText, this, "confirm");

		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSpacing(true);
		addComponent(mainLayout);

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		buttonLayout.addComponent(confirmButton);
		buttonLayout.addComponent(cancelButton);

		setCaption(caption);

		for (String message : messages) {
			mainLayout.addComponent(new Label(message));
		}
		mainLayout.addComponent(buttonLayout);
	}

	public void confirm() throws Exception {
		handler.handle();
		parentWindow.removeWindow(this);
	}

	public void cancel() {
		parentWindow.removeWindow(this);
	}
}
