package frontend.model.operation.control;

import com.jfoenix.controls.JFXButton;

import frontend.util.GridPaneInitializer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.gamedata.game.ControlProperty;

public class ControlPanel extends GridPane {
	private static final double ROW_CONSTRAINT1 = 80;
	private static final double ROW_CONSTRAINT2 = 20;
	private static final double WIDTH_RATIO = 0.8d;
	private static final double HEIGHT = 20;

	private ControlProperty controlProperty;
	private ControlSliderFactory sliderFactory;
	private VBox vbox;
	private JFXButton executeButton;

	public ControlPanel(double width, double height) {
		this.setPrefSize(width, height);
		vbox = new VBox();
		vbox.setPrefSize(width, height);
		controlProperty = new ControlProperty();
		sliderFactory = new ControlSliderFactory(this.getPrefWidth() * WIDTH_RATIO, HEIGHT);

		vbox.setSpacing(HEIGHT);
		vbox.setAlignment(Pos.TOP_CENTER);
		for (ControlType type : ControlType.values()) {
			Label label = initLabel(type.label());
			ControlSlider slider = sliderFactory.getSlider(type, controlProperty.getControlProperty(type));
			vbox.getChildren().addAll(label, slider);
		}

		executeButton = new JFXButton("Execute");
		executeButton.setPrefSize(80, 20);
		initializeLayout();
		fillGrid();
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
	
	private void initializeLayout() {
		GridPaneInitializer initializer = new GridPaneInitializer(this);
		initializer.rowRatios(ROW_CONSTRAINT1, ROW_CONSTRAINT2).build();
	}

	private void fillGrid() {
		this.add(vbox, 0, 0);
		this.add(executeButton, 0, 1);
	}

	private Label initLabel(String name) {
		Label label = new Label(name);
		return label;
	}

}
