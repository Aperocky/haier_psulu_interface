package frontend.model.canvas.layers.base;

import com.jfoenix.controls.JFXMasonryPane;

import frontend.util.usercontrol.basic.KeyboardControl;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public abstract class LayerBase extends JFXMasonryPane implements ILayer {

	private KeyboardControl keyboardControl;

	public LayerBase(double width, double height) {
		this.setPrefSize(width, height);
		keyboardControl.setPrimaryHandler(event -> primaryPressed(event));
		keyboardControl.setSecondaryHandler(event -> secondaryPressed(event));
		keyboardControl.setCtlZHandler(event -> ctlZPressed(event));
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
