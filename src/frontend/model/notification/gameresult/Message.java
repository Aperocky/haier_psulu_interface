package frontend.model.notification.gameresult;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import util.ResourceParser;

public class Message{
	private static double WIDTH = 100d;
	private static double HEIGHT = 100d;
	private Alert alert;
	private ResourceParser parser;

	public Message(String header, String content) {
		alert = new Alert(AlertType.INFORMATION);
		alert.setWidth(WIDTH);
		alert.setHeight(HEIGHT);
		parser = new ResourceParser("path");
		
		initializeContent(header, content);
	}

	public void show() {
		alert.showAndWait();
	}

	public void close() {
		alert.close();
	}

	private void initializeContent(String header, String content) {
		alert.setHeaderText(header);
		alert.setContentText(content);
		DialogPane pane = alert.getDialogPane();
		pane.getStylesheets().add(parser.getString("css"));
	}
	
}
