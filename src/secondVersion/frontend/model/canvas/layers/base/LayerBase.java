package secondVersion.frontend.model.canvas.layers.base;

import com.jfoenix.controls.JFXMasonryPane;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public abstract class LayerBase extends JFXMasonryPane implements ILayer {
	private boolean active = false;

	private EventHandler<MouseEvent> mouseHandler = e -> {
		if (!withinBound(e) || !active)
			return;
		if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			if (e.getButton().equals(MouseButton.SECONDARY))
				secondaryMousePressed(e);
			else
				primaryMousePressed(e);
		}
	};

	private EventHandler<KeyEvent> keyHandler = e -> {
		System.out.println("Key Pressed on LayerBase.");
		if (!active)
			return;
		KeyCode key = e.getCode();
		if (key.equals(KeyCode.BACK_SPACE)) {
			deleteKeyPressed(e);
		}
		if (e.isControlDown()) {
			switch(key) {
			case C:
				copyKeyPressed(e);
				break;
			case V:
				pasteKeyPressed(e);
				break;
			case Z:
				undoKeyPressed(e);
				break;
			default:
				break;
			}
		}
	};

	public LayerBase(double width, double height) {
		this.setPrefSize(width, height);
		//setColor(Color.BLUE);
	}

	@Override
	public void activate() {
		active = true;
		setEventHandler(MouseEvent.MOUSE_PRESSED, mouseHandler);
		setEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
	};

	@Override
	public void deactivate() {
		active = false;
		removeEventHandler(MouseEvent.MOUSE_PRESSED, mouseHandler);
		removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
	};

	@Override
	public boolean isActive() {
		return active;
	}
	
	@Override
	public void clear() {
		super.getChildren().clear();
	};
	
	@Override
	public void toFront() {
		toFront();
	}

	private boolean withinBound(MouseEvent e) {
		return true;
	}

	protected void deleteKeyPressed(KeyEvent event) {
		return;
	}

	protected void copyKeyPressed(KeyEvent event) {
		return;
	}

	protected void pasteKeyPressed(KeyEvent event) {
		return;
	}
	
	protected void undoKeyPressed(KeyEvent event) {
		return;
	}

	protected abstract void secondaryMousePressed(MouseEvent event);

	protected abstract void primaryMousePressed(MouseEvent event);

}
