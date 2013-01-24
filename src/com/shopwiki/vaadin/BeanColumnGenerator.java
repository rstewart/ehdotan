package com.shopwiki.vaadin;

import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

/**
 * @owner rstewart
 */
public abstract class BeanColumnGenerator<B,C> extends BeanTableGenerator<B> implements ColumnGenerator {

	protected abstract C generateCell(B bean);

	@Override
	public Object generateCell(Table table, Object itemId, Object columnId) {
		B bean = getBean(table, itemId);
		return generateCell(bean);
	}
}
