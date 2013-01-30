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

import java.util.Arrays;
import java.util.Collection;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;

/**
 * @owner rstewart
 */
public class EditForm<T> extends Form {

	private final HorizontalLayout footer = new HorizontalLayout();
	private final Button commitButton = new Button("Save", this, "commit");
	private final Button discardButton = new Button("Reset", this, "discard");
	private final Collection<?> propertyIds;

	public EditForm (String... fieldNames) {
		propertyIds = Arrays.asList(fieldNames);

		// Enable buffering so that commit() must be called for the form before input is written to the data.
		// Form input is not written immediately through to the underlying object.
		setWriteThrough(false);

		footer.setSpacing(true);
		footer.addComponent(commitButton);
		footer.addComponent(discardButton);
		footer.setVisible(false);
		setFooter(footer);
/*
		addShortcutListener(new ShortcutListener("Commit", KeyCode.ENTER, null) {
			@Override
			public void handleAction(Object sender, Object target) {
				System.out.println("In commit shortcut");
				//if (target == this) {
					commit();
				//}
			}
		});

		addShortcutListener(new ShortcutListener("Discard", KeyCode.ESCAPE, null) {
			@Override
			public void handleAction(Object sender, Object target) {
				//if (target == this) {
					discard();
				//}
			}
		});
*/
/*
		commitButton.setEnabled(false);
		discardButton.setEnabled(false);

		addListener(new ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				commitButton.setEnabled(true);
				discardButton.setEnabled(true);
			}
		});
*/
	}

	@Override
	public void setItemDataSource(Item item) {
		footer.setVisible(item != null);
		super.setItemDataSource(item, propertyIds);
	}

	@Override
	public void setItemDataSource(Item item, Collection<?> propertyIds) {
		throw new UnsupportedOperationException("Use the single argument overloading!");
	}

	public void setBeanDataSource(BeanItem<T> item) {
		setItemDataSource(item);
	}

	public void setBean(T bean) {
		setBeanDataSource(new BeanItem<T>(bean));
	}

	public BeanItem<T> getBeanDataSource() {
		return (BeanItem<T>)getItemDataSource();
	}

	public T getBean() {
		return getBeanDataSource().getBean();
	}

	@Override
	public void commit() {
		try {
			super.commit();
			getWindow().showNotification("Edits Saved", Window.Notification.TYPE_HUMANIZED_MESSAGE);
		} catch (Throwable e) {
			getWindow().showNotification("Save Failed", Utils.stackTraceAsString(e), Window.Notification.TYPE_ERROR_MESSAGE);
		}
	}
}
