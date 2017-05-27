package frontend.model.canvas.layers.test;

import java.util.ArrayList;
import java.util.List;

import frontend.model.canvas.layers.concrete.pathlayer.PathLayer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.gamedata.game.GameStats;

public class LayerUnitTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		PathLayer layerMaster = new PathLayer(500, 500);
		layerMaster.activate();
		
		// Test on PathLayer with PlannedPath in layerMaster
		GameStats gameStats = new GameStats();
		List<Point2D> path = new ArrayList<>();
		path.add(new Point2D(10, 23));
		path.add(new Point2D(45,100));
		gameStats.setPlannedPath(path);
		gameStats.setCurrentPosition(new Point2D(5,5));
		layerMaster.update(gameStats);
		
		Scene scene = new Scene(layerMaster, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static final void main(String[] args) {
		launch(args);
	}
	

}
