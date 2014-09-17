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

import com.vaadin.ui.Table;
import com.vaadin.ui.Table.CellStyleGenerator;

/**
 * @owner rstewart
 */
public abstract class BeanCellStyleGenerator<B> extends BeanTableGenerator<B> implements CellStyleGenerator {

	private final Table table;

	protected BeanCellStyleGenerator(Table table) {
		this.table = table;
	}

	protected abstract String getStyle(B bean);

	@Override
	public String getStyle(Object itemId, Object propertyId) {
		B bean = getBean(table, itemId);
		return getStyle(bean);
	}
}
