package frontend.model.operation.control;

import com.jfoenix.controls.JFXSlider;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;

/**
 * Customized Slider that fits a specific {@code ControlType}
 * Always gives a visual range of 0 to 100 which maps to the actual lower and 
 * upper bound value.
 * 
 * @author Feng
 *
 */
public class ControlSlider extends JFXSlider {

	/**
	 * 
	 * @param type Control Type
	 * @param width slider width
	 * @param height slider height
	 * @param boundProperty property that binds to the slider value property, note the bound value is after adjustment
	 */
	public ControlSlider(ControlType type, double width, double height, DoubleProperty boundProperty) {
		super(0, 100, 50);
		this.setPrefSize(width, height);
		
		double valueRange = type.max() - type.min();
		double sliderRange = this.getMax() - this.getMin();
		double ratio = valueRange / sliderRange;
	    DoubleBinding adjustedValueBinding = this.valueProperty().multiply(ratio).add(type.min());
	    boundProperty.bind(adjustedValueBinding);
	}

}
