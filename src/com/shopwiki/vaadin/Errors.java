/*
 * Copyright [2014] [ShopWiki]
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

import com.vaadin.terminal.UserError;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Window.Notification;

/**
 * @owner rstewart
 */
public class Errors { // TODO: Move this to Edhotan ???

	private Errors() {
		// can't instantiate
	}

	public static void notify(Component component, String caption, String description) {
		component.getWindow().showNotification(caption, description, Notification.TYPE_ERROR_MESSAGE);
	}

	public static void notify(Component component, String caption, Throwable t) {
		notify(component, caption, Utils.stackTraceAsString(t));
	}

	public static void notify(Component component, String caption) {
		notify(component, caption, (String)null);
	}

	public static void set(AbstractComponent component, Throwable t) {
		set(component, t.toString());
		notify(component, "ERROR", t);
	}

	public static void set(AbstractComponent component, String errorMsg) {
		component.setComponentError(new UserError(errorMsg));
	}

	public static void clear(AbstractComponent component) {
		component.setComponentError(null);
	}
}
