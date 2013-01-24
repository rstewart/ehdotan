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
