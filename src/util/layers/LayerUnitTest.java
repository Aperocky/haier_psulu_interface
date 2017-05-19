package util.layers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LayerUnitTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		LayerMaster layerMaster = new LayerMaster(500, 500);
		layerMaster.activatePathLayer();
		Scene scene = new Scene(layerMaster, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static final void main(String[] args) {
		launch(args);
	}
	

}
