package frontend.model.operation.control;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Slider;

public class ControlSlider extends Slider{

	public ControlSlider(double width, double height, double min, double max, DoubleProperty boundProperty) {
		super(min, max, (min + max) / 2);
		this.setPrefSize(width, height);
		boundProperty.bind(this.valueProperty());
	}

}
