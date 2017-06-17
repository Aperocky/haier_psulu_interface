package frontend.model.canvas.layers.base;

import com.jfoenix.controls.JFXMasonryPane;

import frontend.util.SpaceTransformer;
import frontend.util.usercontrol.basic.KeyboardControl;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public abstract class LayerBase extends JFXMasonryPane implements ILayer {

	private KeyboardControl keyboardControl;
	private SpaceTransformer transformer;

	public LayerBase(double width, double height) {
		this.setPrefSize(width, height);
		transformer = new SpaceTransformer();
		keyboardControl = new KeyboardControl(this);
		keyboardControl.setPrimaryHandler(event -> primaryPressed(event));
		keyboardControl.setSecondaryHandler(event -> secondaryPressed(event));
		keyboardControl.setCtlZHandler(event -> ctlZPressed(event));
		
	}
	
	protected Point2D transform(Point2D point) {
		Point2D resized = transformer.transformTo(1, 1, this.getPrefWidth(), this.getPrefHeight(), point);
		return transformer.verticalFlip(resized, this.getPrefHeight());
	}

	@Override
	public void activate() {
		keyboardControl.activate();
	};

	@Override
	public void deactivate() {
		keyboardControl.deactivate();
	};

	@Override
	public boolean isActive() {
		return keyboardControl.isActive();
	}

	@Override
	public void clear() {
		this.getChildren().clear();
	}

	protected void secondaryPressed(MouseEvent event) {

	}

	protected void primaryPressed(MouseEvent event) {

	}

	protected void ctlZPressed(KeyEvent event) {

	}
}
