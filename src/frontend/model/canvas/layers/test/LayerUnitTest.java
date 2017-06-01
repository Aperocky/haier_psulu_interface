package frontend.model.canvas.layers.test;

import frontend.model.canvas.layers.LayerMaster;
import frontend.model.canvas.layers.base.LayerType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LayerUnitTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		LayerMaster layerMaster = new LayerMaster(500, 500);
		layerMaster.activateLayer(LayerType.GoalLayer);
		
		// Test on PathLayer with PlannedPath in layerMaster
//		GameStats gameStats = new GameStats();
//		List<Point2D> path = new ArrayList<>();
//		path.add(new Point2D(10, 23));
//		path.add(new Point2D(45,100));
//		gameStats.setPlannedPath(path);
//		gameStats.setCurrentPosition(new Point2D(5,5));
//		layerMaster.update(gameStats);
		
		Scene scene = new Scene(layerMaster, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static final void main(String[] args) {
		launch(args);
	}
	

}
