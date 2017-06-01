package frontend.general;

import com.jfoenix.controls.JFXMasonryPane;

import frontend.model.canvas.layers.LayerMaster;

public class Editor extends JFXMasonryPane {
	private LayerMaster layerMaster;

	public Editor(double width, double height) {
		this.setPrefSize(width, height);
		layerMaster = new LayerMaster(500, 500);
		
		this.getChildren().add(layerMaster);
	}

	public LayerMaster getLayerMaster() {
		return layerMaster;
	}

}
