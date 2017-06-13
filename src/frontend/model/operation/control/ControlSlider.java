package frontend.model.operation.control;

import javafx.scene.control.Slider;

/**
 * Customized Slider that bind to a specific type of {@code ControlType}
 * 
 * @author Feng
 *
 */
public class ControlSlider extends Slider {

	public ControlSlider(ControlType type, double width, double height) {
		super(type.min(), type.max(), (type.min() + type.max()) / 2);
		this.setPrefSize(width, height);
	}

}
