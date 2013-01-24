package com.shopwiki.vaadin;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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
	        byte[] bytes = s.getBytes(Utils.UTF_8);
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
