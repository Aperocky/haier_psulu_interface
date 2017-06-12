package frontend.model.operation.control;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.gamedata.game.ControlProperty;

public class ControlPanel extends Pane {
	private static final double WIDTH_RATIO = 0.8d;
	private static final double HEIGHT = 20;
	private static final double SPACING = 20;

	private ControlProperty controlProperty;
	private ControlSliderFactory sliderFactory;
	private VBox vbox;
	private Label chanceLabel;
	private Label wayPointLabel;
	private Label velocityLabel;
	private ControlSlider chanceConstraintSlider;
	private ControlSlider wayPointSlider;
	private ControlSlider maxVelocitySlider;
	private JFXButton executeButton;

	public ControlPanel(double width, double height) {
		this.setPrefSize(width, height);
		vbox = new VBox();
		vbox.setPrefSize(width, height);
		controlProperty = new ControlProperty();
		sliderFactory = new ControlSliderFactory(this.getPrefWidth() * WIDTH_RATIO, HEIGHT);
		chanceLabel = initLabel("Chance Constraint");
		wayPointLabel = initLabel("Number of Waypoints");
		velocityLabel = initLabel("Maximum Velocity");

		chanceConstraintSlider = sliderFactory.getSlider(0, 0.5, controlProperty.getChanceConstraintProperty());
		wayPointSlider = sliderFactory.getSlider(3, 15, controlProperty.getWayPointsProperty());
		maxVelocitySlider = sliderFactory.getSlider(0, 5, controlProperty.getMaxVelocityProperty());

		vbox.setSpacing(SPACING);
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.getChildren().addAll(chanceLabel, chanceConstraintSlider, wayPointLabel, wayPointSlider, velocityLabel,
				maxVelocitySlider);
		
		executeButton = new JFXButton("Execute");
		executeButton.setPrefSize(40, 10);
		executeButton.setLayoutX(0.7d*this.getPrefWidth());
		executeButton.setLayoutY(0.9d*this.getPrefHeight());
		this.getChildren().addAll(vbox, executeButton);
	}

	/**
	 * Give the observable controlProperty
	 * 
	 * @return observable control property
	 */
	public ControlProperty getControlProperty() {
		return controlProperty;
	}
	
	/**
	 * Fire the input action if the execute button is hit
	 * 
	 * @param executeHandler
	 */
	public void setOnExecuted(EventHandler<ActionEvent> executeHandler) {
		executeButton.setOnAction(executeHandler);
	}

	private Label initLabel(String name) {
		Label label = new Label(name);
		return label;
	}

}
