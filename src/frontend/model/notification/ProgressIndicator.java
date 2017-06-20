package frontend.model.notification;

import com.jfoenix.controls.JFXSpinner;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.status.StatusManager;
import util.Observer;
import util.ResourceParser;

public class ProgressIndicator implements Observer<StatusManager> {

	private ResourceParser parser;
	private static double DEFAULT_WIDTH = 200d;
	private static double DEFAULT_HEIGHT = 150d;
	private Stage stage;
	private Scene scene;
	private Pane pane;
	private VBox vbox;
	private JFXSpinner spinner;

	public ProgressIndicator() {
		parser = new ResourceParser("path");
		stage = new Stage();
		stage.setAlwaysOnTop(true);
		stage.initStyle(StageStyle.UNDECORATED);
		pane = new Pane();
		pane.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		vbox = new VBox();
		vbox.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		spinner = new JFXSpinner();

		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(40d);
		vbox.getChildren().addAll(spinner);
		pane.getChildren().add(vbox);

		scene = new Scene(pane);
		scene.getStylesheets().add(parser.getString("css"));
		stage.setScene(scene);

	}

	public void setMessage(String message) {
		vbox.getChildren().add(getLabel(message));
	}

	public void run() {
		stage.show();
	}

	public void close() {
		stage.close();
	}

	private Label getLabel(String msg) {
		return new Label(msg);
	}

	@Override
	public void update(StatusManager status) {
		if (status.isPlanning())
			this.run();
		else
			this.close();
	}

}
