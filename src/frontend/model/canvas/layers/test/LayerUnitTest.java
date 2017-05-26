package frontend.model.canvas.layers.test;

import frontend.model.canvas.layers.concrete.obstaclelayer.ObstacleLayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LayerUnitTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ObstacleLayer layerMaster = new ObstacleLayer(500, 500);
		layerMaster.activate();
		Scene scene = new Scene(layerMaster, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static final void main(String[] args) {
		launch(args);
	}
	

}
