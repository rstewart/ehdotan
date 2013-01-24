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
