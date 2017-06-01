package frontend.util.grid;

import javafx.scene.canvas.GraphicsContext;

public class DotStyle extends GridPaintStyle {

	public DotStyle(double width, double height, double unit) {
		super(width, height, unit);
	}

	@Override
	public void paint(GraphicsContext gc) {
		for (double row = 0; row < height(); row += unit()) {
			for (double col = 0; col < width(); col += unit()) {
				gc.strokeLine(col, row, col, row);
			}
		}
	}

}
