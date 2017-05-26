package frontend.model.canvas.layers.concrete.obstaclelayer;

import java.util.ArrayList;
import java.util.List;

import frontend.model.canvas.layers.base.LayerBase;
import frontend.model.canvas.layers.base.LayerType;
import frontend.model.unit.obstacle.Obstacle;
import frontend.util.grid.Grid;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import model.gamedata.constant.Constants;
import model.gamedata.game.GameStats;
import util.Observer;

public class ObstacleLayer extends LayerBase {
	private List<Obstacle> obstacles;
	private Obstacle creating;

	public ObstacleLayer(double width, double height) {
		super(width, height);
		obstacles = new ArrayList<>();

		addGrid();
	}

	private void addGrid() {
		Grid grid = new Grid(this.prefWidthProperty(), this.prefHeightProperty());
		grid.setUnit(Constants.UNIT.value());
		this.getChildren().add(grid.canvas());
	}
	
	@Override
	public void update(GameStats gameStats) {
		
	}

	@Override
	public void clear() {
		super.clear();
		obstacles.clear();
	}

	@Override
	public void activate() {
		obstacles.forEach(obstacle -> obstacle.activate());
	}

	@Override
	public void deactivate() {
		obstacles.forEach(obstacle -> obstacle.deactivate());
	}

	@Override
	public LayerType getType() {
		return LayerType.ObstacleLayer;
	}

	/**
	 * If there is no polygon being drawn right now, initialize one, else finish
	 * up creating the polygon and assign null to creating.
	 * 
	 * @param event
	 */
	@Override
	protected void secondaryPressed(MouseEvent event) {
		if (!isActive() || !event.isSecondaryButtonDown())
			return;
		if (creating != null) {
			creating = null;
		} else {
			creating = new Obstacle(this);
			Point2D vertex = this.sceneToLocal(event.getSceneX(), event.getSceneY());
			creating.addVertex(vertex);
			creating.deleteHandler(obstacle -> {
				this.getChildren().remove(obstacle);
				this.obstacles.remove(obstacle);
			});
			creating.pasteHandler(obstacle -> {
				this.getChildren().add(obstacle);
				this.obstacles.add(obstacle);
			});
			this.obstacles.add(creating);
			this.getChildren().add(creating);
		}
	}

	@Override
	protected void primaryPressed(MouseEvent event) {
		if (!isActive() || !event.isPrimaryButtonDown())
			return;
		if (creating != null) {
			// Add one more vertex to the initializing polygon being created
			Point2D vertex = this.sceneToLocal(event.getSceneX(), event.getSceneY());
			creating.addVertex(vertex);
		}
	}
}
