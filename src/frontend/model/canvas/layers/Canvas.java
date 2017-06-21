package frontend.model.canvas.layers;

import javafx.scene.layout.Pane;

public class Canvas extends Pane{
	
	private static final double PADDING = 20d;
	private LayerMaster layerMaster;
	
	public Canvas(double size) {
		layerMaster = new LayerMaster(size - 2 * PADDING, size - 2 * PADDING);
		layerMaster.setLayoutX(PADDING);
		layerMaster.setLayoutY(PADDING);
		this.setPrefSize(size, size);
		this.getChildren().add(layerMaster); 
	}
	
	public LayerMaster getLayerMaster() {
		return layerMaster;
	}

}
