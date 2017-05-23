package view.editor.canvas.grid;

import javafx.beans.property.DoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//This entire file is part of my masterpiece.
//Yuansong Feng

/**
 * The reason I think this design is good is that it demonstrates the following OOD principles:
 * 1. This feature is well encapsulated and combines power with flexibility. It
 * is able to draw on any kind of Pane or Control and only need the information 
 * of widthProperty and heightProperty, so it is powerful. 
 * It is also flexible in that to switch to different Grid style, user
 * only needs to make one change in one method. All detailed implementaions are hidden.
 * 
 * 2. It employs Strategy Pattern so its logic is really clear to follow and easy to extend.
 * If developers want to add more styles, he could simply extend the GridPaintStyle to do so. 
 * 
 * 3. This feature does not depend on any other classes. It is a complete modular design that 
 * can be employed anywhere without limitations. It will also bind to whatever node it is 
 * drawn onto. 
 * 
 * 
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

	public Canvas draw() {
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
	}

	private void update() {
		GraphicsContext gc = grid.getGraphicsContext2D();
		gc.clearRect(0, 0, width, height);
		paintStyle = new LineStyle(width, height, unit);
		paintStyle.paint(gc);
	}

}
