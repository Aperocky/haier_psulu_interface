package frontend.model.canvas.layers.test;

import java.util.List;

import frontend.model.canvas.layers.LayerMaster;
import frontend.model.canvas.layers.base.LayerType;
import frontend.model.canvas.layers.concrete.obstaclelayer.ObstacleLayer;
import frontend.model.unit.obstacle.Obstacle;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.gamedata.game.ObstacleFactory;

public class LayerUnitTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		LayerMaster layerMaster = new LayerMaster(500, 500);
		layerMaster.activateLayer(LayerType.ObstacleLayer);
		
		ObstacleLayer layer = new ObstacleLayer(500, 500);
		ObstacleFactory factory = new ObstacleFactory();
		factory.setCanvasSize(500, 500);
		List<List<Point2D>> obstacles = factory
				.loadObstacleFrom("[/Users/Feng/Documents/workspace/haier_psulu_interface/obstacleRsrc/newEnvi.yaml]");
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
