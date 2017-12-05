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
		ControlSlider slider = new ControlSlider(controlType, width, height, property);
		
		// If control type is WayPoints, make the slider snap to UNIT
		if(controlType.equals(ControlType.WayPoints)) {
			slider.valueProperty().addListener((obs, oldValue, newValue) -> {
				double ov = newValue.doubleValue();
			    slider.setValue((int)ov);
			});
		}
		
		return slider;
	}

}
