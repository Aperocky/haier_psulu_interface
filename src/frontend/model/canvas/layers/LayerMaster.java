package frontend.model.canvas.layers;

import java.util.List;

import frontend.model.canvas.layers.base.ILayer;
import frontend.model.canvas.layers.base.LayerType;
import frontend.model.canvas.layers.concrete.obstaclelayer.ObstacleLayer;
import frontend.model.canvas.layers.concrete.pathlayer.PathLayer;
import javafx.scene.layout.StackPane;

public class LayerMaster extends StackPane {

//	private ObstacleLayer obstacleLayer;
//	private PathLayer pathLayer;
	private List<ILayer> layers;

	public LayerMaster(double width, double height) {
		ObstacleLayer obstacleLayer = new ObstacleLayer(width, height);
		PathLayer pathLayer = new PathLayer(width, height);
		layers.add(pathLayer);
		layers.add(obstacleLayer);
		getChildren().addAll(obstacleLayer, pathLayer);
	}

	public void activateLayer(LayerType type) {
		layers.stream().filter(layer -> layer.getType().equals(type)).peek(layer -> {
			layer.activate();
			layer.toFront();
		});
	}
	
	public ILayer getPathLayer(LayerType type) {
		return layers.stream().filter(layer -> layer.getType().equals(type)).findFirst().orElse(null);
	}
	

}
