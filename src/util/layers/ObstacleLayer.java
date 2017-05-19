package util.layers;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import util.layers.models.Obstacle;

public class ObstacleLayer extends LayerBase {
	private List<Obstacle> obstacles;

	private Obstacle selected;
	private Obstacle creating;
	private Obstacle copying;

	public ObstacleLayer(double width, double height) {
		super(width, height);
		obstacles = new ArrayList<>();
	}

	@Override
	public void clear() {
		super.clear();
		obstacles.clear();
	}

	/**
	 * If there is no polygon being drawn right now, initialize one, else finish
	 * up creating the polygon and assign null to creating.
	 * 
	 * @param event
	 */
	@Override
	protected void secondaryMousePressed(MouseEvent event) {
		System.out.println("Secondary Mouse Pressed on ObstacleLayer.");
		if (creating == null) {
			creating = createObstacle(event);
			getChildren().add(creating);
			obstacles.add(creating);
		} else {
			creating = null;
		}
	}

	/**
	 * If we are currently creating an obstacle, then the mouse press adds one
	 * more vertex to the polygon
	 * 
	 * @param event
	 */
	@Override
	protected void primaryMousePressed(MouseEvent event) {
		System.out.println("Primary Mouse Pressed on ObstacleLayer.");
		if (creating != null) {
			// Add one more vertex to the creating polygon being created
			Point2D vertex = this.sceneToLocal(event.getSceneX(), event.getSceneY());
			creating.addVertex(vertex);
		} else {
			selected = obstacles.stream().filter(obs -> obs.selected()).findAny().orElse(null);
			obstacles.stream().peek(obs -> obs.unselect());
		}
	}

	@Override
	protected void deleteKeyPressed(KeyEvent event) {
		System.out.println("Delete Key Pressed on ObstacleLayer.");
		if (selected != null) {
			obstacles.remove(selected);
			getChildren().remove(selected);
			selected = null;
		}
	}

	@Override
	protected void copyKeyPressed(KeyEvent event) {
		System.out.println("Copy Key Pressed on ObstacleLayer.");
		if (selected != null)
			copying = selected;
	}

	@Override
	protected void pasteKeyPressed(KeyEvent event) {
		System.out.println("Paste Key Pressed on ObstacleLayer.");
		if (copying != null) {
			copying = copying.copy();
			copying.setLayoutX(copying.getLayoutX() + 5);
			copying.setLayoutY(copying.getLayoutY() + 5);
			obstacles.add(copying);
			getChildren().add(copying);
		}
		event.consume();
	}

	private Obstacle createObstacle(MouseEvent event) {
		Obstacle obstacle = new Obstacle(this);
		Point2D vertex = this.sceneToLocal(event.getSceneX(), event.getSceneY());
		obstacle.addVertex(vertex);
		return obstacle;
	}
}
