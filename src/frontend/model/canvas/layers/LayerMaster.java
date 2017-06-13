package frontend.model.canvas.layers;

import java.util.ArrayList;
import java.util.List;

import frontend.model.canvas.layers.base.ILayer;
import frontend.model.canvas.layers.base.LayerType;
import frontend.model.canvas.layers.concrete.KeyComponentLayer;
import frontend.model.canvas.layers.concrete.obstaclelayer.ObstacleLayer;
import frontend.model.canvas.layers.concrete.pathlayer.PathLayer;
import javafx.scene.layout.StackPane;

public class LayerMaster extends StackPane {

	// private ObstacleLayer obstacleLayer;
	// private PathLayer pathLayer;
	private List<ILayer> layers;

	public LayerMaster(double width, double height) {
		ObstacleLayer obstacleLayer = new ObstacleLayer(width, height);
		PathLayer pathLayer = new PathLayer(width, height);
		KeyComponentLayer goalLayer = new KeyComponentLayer(width, height);
		layers = new ArrayList<>();
		layers.add(pathLayer);
		layers.add(obstacleLayer);
		layers.add(goalLayer);
		getChildren().addAll(obstacleLayer, pathLayer, goalLayer);
	}

	public void activateLayer(LayerType type) {
		layers.stream().filter(layer -> layer.getType().equals(type)).forEach(layer -> {
			layer.activate();
			layer.toFront();
		});
	}

	public ILayer getLayer(LayerType type) {
		return layers.stream().filter(layer -> layer.getType().equals(type)).findFirst().orElse(null);
	}

}
