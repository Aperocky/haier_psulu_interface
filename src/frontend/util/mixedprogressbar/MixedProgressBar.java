package frontend.util.mixedprogressbar;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Multiple Progress Bar layered on top of each other
 * @author Feng
 *
 */
public class MixedProgressBar extends Pane {

	private static final double PADDING = 6d;
	private StackPane background;
	private List<Pane> panes;
	private List<Rectangle> bars;
	private List<DoubleProperty> properties;

	public MixedProgressBar(double width, double height, int numBars) {
		this.setPrefSize(width, height);
		this.setMaxSize(width, height);
		background = new StackPane();
		background.setPrefSize(width, height);
		background.setStyle(
				"-fx-border-radius: 6 6 6 6; -fx-background-radius: 6 6 6 6; -fx-background-color: rgb(30, 30, 30);");

		bars = new ArrayList<>();
		properties = new ArrayList<>();
		panes = new ArrayList<>();
		for (int i = 0; i < numBars; i++) {
			Pane pane = getProgressPane(width, height);
			Rectangle rect = getProgressRect(width, height, Color.color(Math.random(), Math.random(), Math.random()));
			DoubleProperty property = new SimpleDoubleProperty();
			property.addListener((obs, oldv, newv) -> {
				newv = newv.doubleValue() < 0 ? 0 : newv.doubleValue();
				newv = newv.doubleValue() > 1 ? 1 : newv.doubleValue();
				double w = widthTransform(newv.doubleValue(), width - 2 * PADDING);
				double layoutX = width  - PADDING  - w;
				rect.setLayoutX(layoutX);
				rect.setWidth(w);
			});
			// Backward progress bar has initial width 0
			if(i == numBars-1)
				rect.setWidth(0);
			pane.getChildren().add(rect);
			panes.add(pane);
			bars.add(rect);
			properties.add(property);
		}
		background.getChildren().addAll(panes);
		this.getChildren().add(background);
	}
	
	public void setColor(Color color) {
		setColor(color, 0);
	} 
	
	public void setColor(Color color, int bar) {
		if(bar >= bars.size())
			return;
		bars.get(bar).setFill(color);
	}

	public void setProgress(double progress) {
		setProgress(progress, 0);
	}

	public void setProgress(double progress, int bar) {
		if (bar >= bars.size())
			return;
		properties.get(bar).set(progress);
	}

	private double widthTransform(double value, double width) {
		return value * width;
	}

	private Pane getProgressPane(double width, double height) {
		Pane pane = new Pane();
		pane.setPrefSize(width, height);
		pane.setStyle("-fx-background-color: transparent;");
		return pane;
	}
	
	private Rectangle getProgressRect(double width, double height, Color color) {
		Rectangle rect = new Rectangle();
		rect.setWidth(width - 2 * PADDING);
		rect.setHeight(height - 2 * PADDING);
		rect.setLayoutX(PADDING);
		rect.setLayoutY(PADDING);
		rect.setFill(color);
		rect.setArcWidth(5);
		rect.setArcHeight(5);
		return rect;
	}

}
