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
import model.gamedata.game.gamestats.GameStats;
import util.Observer;

public class ObstacleLayer extends LayerBase {
	private ObstacleMaster obstacleMaster;
	private Obstacle creating;

	public ObstacleLayer(double width, double height) {
		super(width, height);
		obstacleMaster = new ObstacleMaster();

		addGrid();
	}

	@Override
	public void update(GameStats gameStats) {
		this.clear();
		gameStats.getObstacles().stream().forEach(obstacle -> {
			List<Point2D> transformed = new ArrayList<>();
			obstacle.forEach(point -> transformed.add(transform(point)));
			addObstacle(transformed);
		});
	}

	public void addObstacle(List<Point2D> vertices) {
		Obstacle obs = new Obstacle(this);
		vertices.forEach(point -> {
			obs.addVertex(point);
		});
		this.addObstacle(obs);
	}

	public void addObstacle(Obstacle obstacle) {
		obstacleMaster.add(obstacle);
		getChildren().add(obstacle);
	}

	public void addObserver(Observer<ObstacleMaster> observer) {
		obstacleMaster.addObserver(observer);
	}

	@Override
	public void clear() {
		super.clear();
		obstacleMaster.clear();
	}

	@Override
	public void activate() {
		super.activate();
		obstacleMaster.activateAll();
	}

	@Override
	public void deactivate() {
		super.deactivate();
		obstacleMaster.deactivateAll();
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
				obstacleMaster.remove(obstacle);
			});
			creating.pasteHandler(obstacle -> {
				this.getChildren().add(obstacle);
				obstacleMaster.add(obstacle);
			});
			obstacleMaster.add(creating);
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

	private void addGrid() {
		Grid grid = new Grid(this.prefWidthProperty(), this.prefHeightProperty());
		grid.setUnit(Constants.UNIT.value());
		this.getChildren().add(grid.canvas());
	}
}
