package secondVersion.frontend.model.operation.control;

import com.jfoenix.controls.JFXSlider;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ControlPanel extends VBox {
	private static final double WIDTH_RATIO = 0.8d;
	private static final double HEIGHT = 20;
	private static final double SPACING = 40;

	private Label riskLabel;
	private Label horizonLabel;
	private JFXSlider riskSlider;
	private JFXSlider horizonSlider;
	private DoubleProperty riskProperty;
	private DoubleProperty horizonProperty;
	
	public ControlPanel(double width, double height) {
		this.setPrefSize(width, height);
		riskLabel = initLabel("Risk Budget");
		horizonLabel = initLabel("Horizon Radius");
		riskSlider = initSlider(0 ,100, riskProperty);
		horizonSlider = initSlider(0 ,100, horizonProperty);
		
		this.setSpacing(SPACING);
		this.setAlignment(Pos.TOP_CENTER);
		this.getChildren().addAll(riskLabel, riskSlider, horizonLabel, horizonSlider);	
	}
	
	/**
	 * Give the observable risk budget property 
	 * @return observable risk budget property 
	 */
	public DoubleProperty getRiskProperty() {
		return riskProperty;
	}
	
	/**
	 * Give the observable horizon radius property 
	 * @return observable horizon radius property 
	 */
	public DoubleProperty getHorizonProperty() {
		return horizonProperty;
	}
	
	private JFXSlider initSlider(double min, double max, DoubleProperty property) {
		JFXSlider slider = new JFXSlider(min, max, (min + max) / 2);
		property.bind(slider.valueProperty());
		slider.setPrefWidth(WIDTH_RATIO * this.getPrefWidth());
		slider.setPrefHeight(HEIGHT);
		return slider;
	}
	
	private Label initLabel(String name) {
		Label label = new Label(name);
		return label;
	}

}
