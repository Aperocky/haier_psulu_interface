package util.layers;

import javafx.scene.layout.StackPane;

public class LayerMaster extends StackPane {

	private ObstacleLayer obstacleLayer;
	private PathLayer pathLayer;

	public LayerMaster(double width, double height) {
		obstacleLayer = new ObstacleLayer(width, height);
		pathLayer = new PathLayer(width, height);
		getChildren().addAll(obstacleLayer, pathLayer);
	}

	public void activatePathLayer() {
		obstacleLayer.deactivate();
		pathLayer.activate();
		pathLayer.toFront();
	}

	public void activateObstacleLayer() {
		obstacleLayer.activate();
		pathLayer.deactivate();
		obstacleLayer.toFront();
	}

}
