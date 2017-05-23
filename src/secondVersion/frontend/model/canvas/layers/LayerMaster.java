package secondVersion.frontend.model.canvas.layers;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.StackPane;
import secondVersion.frontend.model.canvas.layers.base.ILayer;
import secondVersion.frontend.model.canvas.layers.base.LayerType;
import secondVersion.frontend.model.canvas.layers.concrete.obstaclelayer.ObstacleLayer;
import secondVersion.frontend.model.canvas.layers.concrete.pathlayer.PathLayer;

public class LayerMaster extends StackPane {

	// private ObstacleLayer obstacleLayer;
	// private PathLayer pathLayer;
	private List<ILayer> layers;

	public LayerMaster(double width, double height) {
		ObstacleLayer obstacleLayer = new ObstacleLayer(width, height);
		PathLayer pathLayer = new PathLayer(width, height);
		layers = new ArrayList<>();
		layers.add(obstacleLayer);
		layers.add(pathLayer);
		getChildren().addAll(obstacleLayer, pathLayer);
	}

	public void activateLayer(LayerType type) {
		layers.stream().filter(layer -> layer.getType().equals(type)).findFirst().ifPresent(layer -> {
			layer.activate();
			layer.toFront();
		});
	}

	public ILayer getLayer(LayerType type) {
		return layers.stream().filter(layer -> layer.getType().equals(type)).findFirst().get();
	}

}
