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

import java.util.Collection;

import com.vaadin.data.Container;
import com.vaadin.ui.Select;

/**
 * Why are there no accessor methods for Select's pageLength !?
 *
 * @owner rstewart
 */
public class SWSelect extends Select {

	public SWSelect() {
		super();
	}

	public SWSelect(String caption) {
		super(caption);
	}

    public SWSelect(String caption, Collection<?> options) {
        super(caption, options);
    }

    public SWSelect(String caption, Container dataSource) {
        super(caption, dataSource);
    }

	public int getPageLength() {
		return super.pageLength;
	}

	public void setPageLength(int pageLength) {
		super.pageLength = pageLength;
	}
}
