package frontend.model.operation.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControlPanelUnitTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane primary = new Pane();
		primary.setPrefSize(500, 500);
		ControlPanel control = new ControlPanel(500, 500);
		primary.getChildren().add(control);
		Scene scene = new Scene(primary, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static final void main(String[] args) {
		launch(args);
	}

	
}
