package frontend.model.operation.control;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Slider;

/**
 * Customized Slider that fits a specific {@code ControlType} Always gives a
 * visual range of 0 to 100 which maps to the actual lower and upper bound
 * value.
 * 
 * @author Feng
 *
 */
public class ControlSlider extends Slider {
	private ControlType type;
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
		this.type = type;
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
	
	public ControlType getType() {
		return type;
	}

	private double uiToAlgo(ControlType type, double uiValue) {
		double algoRange = type.algoMax() - type.algoMin();
		double uiRange = type.uiMax() - type.uiMin();
		double ratio = algoRange / uiRange;
		
		return ratio * (uiValue - type.uiMin()) + type.algoMin();
	}

}
