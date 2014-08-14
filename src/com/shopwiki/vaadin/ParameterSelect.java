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

/**
 * @owner rstewart
 */
public abstract class ParameterSelect<T> extends SWSelect {

	protected ParameterSelect() {
		super();
	}

	/** Deserialize */
	protected abstract T parse(String s);

	/** Serialize */
	protected abstract String toString(T value);

	/** Sets the value of the Select according to the UrlParams */
	public void set(UrlParams params, String key) {
		setComponentError(null);

		if (isNullSelectionAllowed()) {
			setValue(null);
		}

		String s = params.get(key);
		if (s == null || s.isEmpty()) {
			return;
		}

		try {
			T value = parse(s);
			setValue(value);
		} catch (NumberFormatException e) {
			Errors.set(this, "Invalid " + key + ": " + s);
		}
	}

	/** Sets the UrlParamsvalue of the Select according to the UrlParams */
	public void put(UrlParams params, String key) {
		T value = (T)getValue();
		if (value == null) {
			params.remove(key);
		} else {
			String s = toString(value);
			params.add(key, s);
		}
	}
}
