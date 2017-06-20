package frontend.model.notification.gameresult;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.status.StatusManager;
import util.Observer;

public class SuccessMessage implements Observer<StatusManager> {

	private static double WIDTH = 100d;
	private static double HEIGHT = 100d;
	private Alert alert;

	public SuccessMessage() {
		alert = new Alert(AlertType.INFORMATION);
		alert.setWidth(WIDTH);
		alert.setHeight(HEIGHT);
		initializeContent();
	}

	public void show() {
		alert.showAndWait();
	}

	public void close() {
		alert.close();
	}

	private void initializeContent() {
		alert.setHeaderText("Success!");
		alert.setContentText("You have completed the path!");

	}

	@Override
	public void update(StatusManager status) {
		if (status.isSuccess())
			this.show();
	}

}
