package frontend.model.operation.control;

import com.jfoenix.controls.JFXSlider;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;

/**
 * Customized Slider that fits a specific {@code ControlType} Always gives a
 * visual range of 0 to 100 which maps to the actual lower and upper bound
 * value.
 * 
 * @author Feng
 *
 */
public class ControlSlider extends JFXSlider {

	/**
	 * 
	 * @param type
	 *            Control Type
	 * @param width
	 *            slider width
	 * @param height
	 *            slider height
	 * @param boundProperty
	 *            property that binds to the slider value property, note the
	 *            bound value is after adjustment
	 */
	public ControlSlider(ControlType type, double width, double height, DoubleProperty boundProperty) {
		super(type.uiMin(), type.uiMax(), (type.uiMin() + type.uiMax()) / 2);
		this.setPrefSize(width, height);

		// notify new value after user finishes dragging
		this.valueChangingProperty().addListener((obs, oldv, newv) -> {
			if (newv == false) {
				boundProperty.set(uiToAlgo(type, this.getValue()));
			}
		});

		// notify new value after user clicks on slider
		this.valueProperty().addListener((obs, oldv, newv) -> {
			if (!this.isValueChanging()) {
				boundProperty.set(uiToAlgo(type, newv.doubleValue()));
			}
		});
	}

	private double uiToAlgo(ControlType type, double uiValue) {
		double algoRange = type.algoMax() - type.algoMin();
		double uiRange = type.uiMax() - type.uiMin();
		double ratio = algoRange / uiRange;
		
		return ratio * uiValue;
	}

}
