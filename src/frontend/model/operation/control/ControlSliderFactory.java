package frontend.model.operation.control;

import javafx.beans.property.DoubleProperty;

public class ControlSliderFactory {
	private double width;
	private double height;
	
	public ControlSliderFactory(double w, double h) {
	    width = w;
	    height = h;
	}
	
	public ControlSlider getSlider(double min, double max, DoubleProperty property) {
		ControlSlider slider = new ControlSlider(width, height, min, max, property);
		return slider;
	}

}
