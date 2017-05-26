package secondVersion.frontend.util.grid;

import javafx.scene.canvas.GraphicsContext;

public class LineStyle extends GridPaintStyle {

	public LineStyle(double width, double height, double unit) {
		super(width, height, unit);

	}

	@Override
	public void paint(GraphicsContext gc) {
		for (double row = 0; row < height(); row += unit()) {
			gc.strokeLine(0, row, width(), row);
		}

		for (double col = 0; col < width(); col += unit()) {
			gc.strokeLine(col, 0, col, height());
		}
	}

}
