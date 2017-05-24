package secondVersion.frontend.util.usercontrol.basic;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Translate basic key board action into event handling. 
 * 
 * @author Feng
 *
 */
public class KeyboardControl {
	private Node base;
	private boolean active;
	
	public KeyboardControl(Node base) {
		this.base = base;
	}

	public void activate() {
		active = true;
		base.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseHandler);
		base.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
	};

	public void deactivate() {
		active = false;
		base.removeEventHandler(MouseEvent.MOUSE_PRESSED, mouseHandler);
		base.removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
	};

	protected void secondaryPressed(MouseEvent event) {
		return;
	}

	protected void primaryPressed(MouseEvent event) {
		return;
	}

	protected void deletePressed(KeyEvent handler) {
		return;
	}

	protected void ctlCPressed(KeyEvent event) {
		return;
	}

	protected void ctlVPressed(KeyEvent event) {
		return;
	}

	protected void ctlZPressed(KeyEvent event) {
		return;
	}

	private EventHandler<MouseEvent> mouseHandler = e -> {
		if (!active)
			return;
		if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			if (e.getButton().equals(MouseButton.SECONDARY))
				secondaryPressed(e);
			else
				primaryPressed(e);
		}
	};

	private EventHandler<KeyEvent> keyHandler = e -> {
		System.out.println("Key Pressed on LayerBase.");
		if (!active)
			return;
		KeyCode key = e.getCode();
		if (key.equals(KeyCode.BACK_SPACE)) {
			deletePressed(e);
		}
		if (e.isControlDown()) {
			switch (key) {
			case C:
				ctlCPressed(e);
				break;
			case V:
				ctlVPressed(e);
				break;
			case Z:
				ctlZPressed(e);
				break;
			default:
				break;
			}
		}
	};

}
