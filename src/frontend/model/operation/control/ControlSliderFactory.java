package frontend.model.operation.control;

import javafx.beans.property.DoubleProperty;

public class ControlSliderFactory {
	private double width;
	private double height;
	
	public ControlSliderFactory(double w, double h) {
	    width = w;
	    height = h;
	}
	
	public ControlSlider getSlider(ControlType controlType, DoubleProperty property) {
		ControlSlider slider = new ControlSlider(controlType, width, height);
		property.bind(slider.valueProperty());
		return slider;
	}

}
