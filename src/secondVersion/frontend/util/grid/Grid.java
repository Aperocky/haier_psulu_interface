package secondVersion.frontend.util.grid;

import javafx.beans.property.DoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Decorative grid background on a pane that will bind to the width and height
 * of its parent pane. It helps game developer to more accurately position their
 * templates onto the view. Allow user to specify the size of the grid cell.
 * 
 * @author Feng
 *
 */
public class Grid {
	public static final Color DEFAULT_COLOR = Color.web("#767777");
	public static final Double DEFAULT_STROKE = 1d;
	public static final Double DEFAULT_UNIT = 20d;
	private double unit = DEFAULT_UNIT;
	private Canvas grid;
	private IPaintStyle paintStyle;

	private double width;
	private double height;

	public Grid(DoubleProperty widthProperty, DoubleProperty heightProperty) {
		grid = new Canvas();
		width = widthProperty.doubleValue();
		height = heightProperty.doubleValue();
		grid.widthProperty().bind(widthProperty);
		grid.heightProperty().bind(heightProperty);
		grid.setMouseTransparent(true);
		grid.toBack();
		GraphicsContext gc = grid.getGraphicsContext2D();
		gc.setStroke(DEFAULT_COLOR);
		gc.setLineWidth(DEFAULT_STROKE);

		widthProperty.addListener((observable, o_value, n_value) -> {
			width = n_value.doubleValue();
			update();
		});

		heightProperty.addListener((observable, o_value, n_value) -> {
			height = n_value.doubleValue();
			update();
		});
	}

	public Canvas canvas() {
		update();
		return grid;
	}

	public void setStrokeWidth(double stroke) {
		GraphicsContext gc = grid.getGraphicsContext2D();
		gc.setLineWidth(stroke);
	}

	public void setStrokeColor(Color color) {
		GraphicsContext gc = grid.getGraphicsContext2D();
		gc.setStroke(color);
	}

	public void setUnit(double unit) {
		this.unit = unit;
		update();
	}

	private void update() {
		GraphicsContext gc = grid.getGraphicsContext2D();
		gc.clearRect(0, 0, width, height);
		paintStyle = new LineStyle(width, height, unit);
		paintStyle.paint(gc);
	}

}
