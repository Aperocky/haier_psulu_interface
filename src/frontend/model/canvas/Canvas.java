package frontend.model.canvas;

import frontend.model.canvas.layers.LayerMaster;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Canvas extends Pane{
	
	private static final double BORDER = 20d;
	private static final double BORDER_GLOW = 40d;
	private LayerMaster layerMaster;
	
	public Canvas(double size) {
		layerMaster = new LayerMaster(size - 0.8d * BORDER, size - 0.8d * BORDER);
		layerMaster.setTranslateX(BORDER);
		layerMaster.setTranslateY(BORDER);
		addBorderGlow(this);
		this.setPrefSize(size, size);
		this.setStyle("-fx-background-color: rgb(40, 40, 40)");
		this.getChildren().add(layerMaster); 
	}
	
	public LayerMaster getLayerMaster() {
		return layerMaster;
	}
	
	private void addBorderGlow(Node node) {		 
		DropShadow borderGlow= new DropShadow();
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setColor(Color.BLACK);
		borderGlow.setWidth(BORDER_GLOW);
		borderGlow.setHeight(BORDER_GLOW);
		 
		node.setEffect(borderGlow);
	}

}
