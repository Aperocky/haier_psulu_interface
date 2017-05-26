package secondVersion.frontend.util;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class GridPaneInitializer {
	private double[] rowRatios;
	private double[] columnRatios;
	private GridPane grid;

	public GridPaneInitializer(GridPane grid) {
		this.grid = grid;
	}

	public GridPaneInitializer rowRatios(double... ratios) {
		this.rowRatios = ratios;
		return this;
	}

	public GridPaneInitializer columnRatios(double... ratios) {
		this.columnRatios = ratios;
		return this;
	}

	public void build() {
		for (double rowR : this.rowRatios) {
			grid.getRowConstraints().add(getRowConstraints(rowR));
		}
		for (double colR : this.columnRatios) {
			grid.getColumnConstraints().add(getColumnConstraints(colR));
		}
	}
	
	private ColumnConstraints getColumnConstraints(double percent) {
        ColumnConstraints ret = new ColumnConstraints();
        ret.setHgrow(Priority.NEVER);
        ret.setPercentWidth(percent);
        return ret;
    }

    private static RowConstraints getRowConstraints(double percent) {
        RowConstraints ret = new RowConstraints();
        ret.setVgrow(Priority.NEVER);
        ret.setPercentHeight(percent);
        return ret;
    }
	
}
