package frontend.model.canvas.layers;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;

public class Canvas extends Pane{
	
	private static final double PADDING = 20d;
	private LayerMaster layerMaster;
	
	public Canvas(double width, double height) {
		layerMaster = new LayerMaster(width - 2 * PADDING, height - 2 * PADDING);
		layerMaster.setLayoutX(PADDING);
		layerMaster.setLayoutY(PADDING);
		this.setPrefSize(width, height);
		this.setPadding(new Insets(40d));
		this.getChildren().add(layerMaster); 
	}
	
	public LayerMaster getLayerMaster() {
		return layerMaster;
	}

}
