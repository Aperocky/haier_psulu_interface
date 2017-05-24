package secondVersion.frontend.model.canvas.layers.concrete.obstaclelayer;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import secondVersion.frontend.model.canvas.layers.base.LayerBase;
import secondVersion.frontend.model.canvas.layers.base.LayerType;
import secondVersion.frontend.model.unit.obstacle.Obstacle;
import secondVersion.frontend.util.grid.Grid;
import secondVersion.model.gamedata.constant.Constants;
import secondVersion.model.gamedata.game.GameStats;

public class ObstacleLayer extends LayerBase {
	private List<Obstacle> obstacles;
	private Obstacle creating;

	public ObstacleLayer(double width, double height) {
		super(width, height);
		obstacles = new ArrayList<>();

		addGrid();
		this.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> secondaryPressed(event));
		this.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> primaryPressed(event));
	}

	private void addGrid() {
		Grid grid = new Grid(this.prefWidthProperty(), this.prefHeightProperty());
		grid.setUnit(Constants.UNIT.value());
		this.getChildren().add(grid.canvas());
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
	private void secondaryPressed(MouseEvent event) {
		if (!event.isSecondaryButtonDown())
			return;
		if (creating != null) {
			creating.doneInitializing();
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
		// Point2D localPos = this.sceneToLocal(event.getSceneX(),
		// event.getSceneY());
		// Optional<Obstacle> initialized = userControl.initializeObstacle(new
		// Obstacle(this), localPos.getX(),
		// localPos.getY());
		// if (initialized.isPresent()) {
		// this.getChildren().add(initialized.get());
		// }
	}
	
	private void primaryPressed(MouseEvent event) {
		if(!event.isPrimaryButtonDown())
			return;
		if (creating != null) {
			// Add one more vertex to the initializing polygon being created
			Point2D vertex = this.sceneToLocal(event.getSceneX(), event.getSceneY());
			creating.addVertex(vertex);
		}
	}

	// /**
	// * If we are currently creating an obstacle, then the mouse press adds one
	// * more vertex to the polygon
	// *
	// * @param event
	// */
	// @Override
	// protected void primaryMousePressed(MouseEvent event) {
	// Obstacle selected = null;
	// if (event.getSource() instanceof Obstacle) {
	// selected = (Obstacle) event.getSource();
	// }
	// Point2D local = this.sceneToLocal(event.getSceneX(), event.getSceneY());
	// System.out.println("Selected is null: " + event.getSource());
	// userControl.editObstacle(Optional.ofNullable(selected), local.getX(),
	// local.getY());
	// }
	//
	// @Override
	// protected void deleteKeyPressed(KeyEvent event) {
	// Optional<Obstacle> delete = userControl.deleteObstacle();
	// if (delete.isPresent())
	// this.getChildren().remove(delete.get());
	// }
	//
	// @Override
	// protected void copyKeyPressed(KeyEvent event) {
	// userControl.copyObstacle();
	// }
	//
	// @Override
	// protected void pasteKeyPressed(KeyEvent event) {
	// Optional<Obstacle> paste = userControl.pasteObstacle();
	// if (paste.isPresent())
	// this.getChildren().add(paste.get());
	// }
}
