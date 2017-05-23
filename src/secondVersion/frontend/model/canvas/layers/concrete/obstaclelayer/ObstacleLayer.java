package secondVersion.frontend.model.canvas.layers.concrete.obstaclelayer;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import secondVersion.frontend.model.canvas.layers.base.LayerBase;
import secondVersion.frontend.model.canvas.layers.base.LayerType;
import secondVersion.frontend.util.grid.Grid;
import secondVersion.model.gamedata.constant.Constants;
import secondVersion.model.gamedata.game.GameStats;

public class ObstacleLayer extends LayerBase {
	private ObstacleLayerController controller;

	public ObstacleLayer(double width, double height) {
		super(width, height);
		controller = new ObstacleLayerController(this);

		addGrid();
	}

	private void addGrid() {
		Grid grid = new Grid(this.prefWidthProperty(), this.prefHeightProperty());
		grid.setUnit(Constants.UNIT.value());
		this.getChildren().add(grid.canvas());
	}

	@Override
	public void clear() {
		super.clear();
		controller.clearObstacles();
	}

	@Override
	public LayerType getType() {
		return LayerType.ObstacleLayer;
	}

	@Override
	public void update(GameStats game) {
		return;
	}

	/**
	 * If there is no polygon being drawn right now, initialize one, else finish
	 * up creating the polygon and assign null to creating.
	 * 
	 * @param event
	 */
	@Override
	protected void secondaryMousePressed(MouseEvent event) {
		controller.initializeObstacle(event);
	}

	/**
	 * If we are currently creating an obstacle, then the mouse press adds one
	 * more vertex to the polygon
	 * 
	 * @param event
	 */
	@Override
	protected void primaryMousePressed(MouseEvent event) {
		controller.editObstacle(event);
	}

	@Override
	protected void deleteKeyPressed(KeyEvent event) {
		controller.deleteObstacle();
	}

	@Override
	protected void copyKeyPressed(KeyEvent event) {
		controller.copyObstacle();
	}

	@Override
	protected void pasteKeyPressed(KeyEvent event) {
		controller.pasteObstacle();
	}
}
