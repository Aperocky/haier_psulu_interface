package frontend.model.canvas.layers.test;

import java.util.List;

import frontend.model.canvas.layers.LayerMaster;
import frontend.model.canvas.layers.base.LayerType;
import frontend.model.canvas.layers.concrete.obstaclelayer.ObstacleLayer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.gamedata.game.obstacles.ObstacleFactory;

public class LayerUnitTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		ObstacleLayer layer = new ObstacleLayer(500, 500);
		ObstacleFactory factory = new ObstacleFactory();
		List<List<Point2D>> obstacles = factory.loadObstacles();
		obstacles.forEach(obstacle -> {
			layer.addObstacle(obstacle);
		});
		Scene scene = new Scene(layer, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();

		// Test on PathLayer with PlannedPath in layerMaster
		// GameStats gameStats = new GameStats();
		// List<Point2D> path = new ArrayList<>();
		// path.add(new Point2D(10, 23));
		// path.add(new Point2D(45,100));
		// gameStats.setPlannedPath(path);
		// gameStats.setCurrentPosition(new Point2D(5,5));
		// layerMaster.update(gameStats);
	}

	public static final void main(String[] args) {
		launch(args);
	}

}
