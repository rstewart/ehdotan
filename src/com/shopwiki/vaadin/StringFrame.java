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

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.google.common.base.Charsets;
import com.vaadin.Application;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.StreamResource.StreamSource;
import com.vaadin.ui.Embedded;

/**
 * @owner rstewart
 */
public class StringFrame extends Embedded {

	public static class StringSource implements StreamSource {

		public final String s;

		public StringSource(String s) {
			this.s = s;
		}

		@Override
		public InputStream getStream() {
	        byte[] bytes = s.getBytes(Charsets.UTF_8);
	        return new ByteArrayInputStream(bytes);
		}
	}

	private final Application app;

	public StringFrame(Application app) {
		this.app = app;
		setType(Embedded.TYPE_BROWSER); // FIXME: Put the content inside of HTML/BODY !!!
	}

	public void setContent(String content, String mimeType) {
		String filename = "";
		setContent(content, mimeType, filename);
	}

	public void setContent(String content, String mimeType, String filename) {
		StreamSource source = new StringSource(content);
		StreamResource resource = new StreamResource(source, filename, app);
		resource.setMIMEType(mimeType);
		setSource(resource);
	}
}
