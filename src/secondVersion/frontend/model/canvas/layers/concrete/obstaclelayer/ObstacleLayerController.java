package secondVersion.frontend.model.canvas.layers.concrete.obstaclelayer;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import secondVersion.frontend.model.unit.Obstacle;

public class ObstacleLayerController {
	private ObstacleLayer layer;
	private List<Obstacle> obstacles;

	private Obstacle selected;
	private Obstacle creating;
	private Obstacle copying;

	public ObstacleLayerController(ObstacleLayer layer) {
		this.layer = layer;
		obstacles = new ArrayList<>();
	}

	public void clearObstacles() {
		obstacles.clear();
	}

	public void initializeObstacle(MouseEvent event) {
		System.out.println("Secondary Mouse Pressed on ObstacleLayer.");
		if (creating == null) {
			creating = createObstacle(event);
			layer.getChildren().add(creating);
			obstacles.add(creating);
		} else {
			creating = null;
		}
	}

	public void editObstacle(MouseEvent event) {
		System.out.println("Primary Mouse Pressed on ObstacleLayer.");
		if (creating != null) {
			layer.getChildren().removeAll(creating.getDiscreteForm());
			// Add one more vertex to the creating polygon being created
			Point2D vertex = layer.sceneToLocal(event.getSceneX(), event.getSceneY());
			creating.addVertex(vertex);
			//creating.updateDiscreteForm();
			//layer.getChildren().addAll(creating.getDiscreteForm());
		} else {
			selected = obstacles.stream().filter(obs -> obs.selected()).findAny().orElse(null);
			obstacles.stream().peek(obs -> obs.unselect());
		}
	}

	public void deleteObstacle() {
		System.out.println("Delete Key Pressed on ObstacleLayer.");
		if (selected != null) {
			obstacles.remove(selected);
			layer.getChildren().remove(selected);
			//layer.getChildren().removeAll(selected.getDiscreteForm());
			selected = null;
		}
	}

	public void copyObstacle() {
		System.out.println("Copy Key Pressed on ObstacleLayer.");
		if (selected != null)
			copying = selected;
	}

	public void pasteObstacle() {
		System.out.println("Paste Key Pressed on ObstacleLayer.");
		if (copying != null) {
			copying = copying.copy();
			copying.setLayoutX(copying.getLayoutX() + 5);
			copying.setLayoutY(copying.getLayoutY() + 5);
			obstacles.add(copying);
			layer.getChildren().add(copying);
			//layer.getChildren().addAll(copying.getDiscreteForm());
		}
	}

	private Obstacle createObstacle(MouseEvent event) {
		Obstacle obstacle = new Obstacle(layer);
		Point2D vertex = layer.sceneToLocal(event.getSceneX(), event.getSceneY());
		obstacle.addVertex(vertex);
//		obstacle.setOnDragDetected(evt -> {
//			layer.getChildren().removeAll(obstacle.getDiscreteForm());
//		});
//		obstacle.setOnMouseReleased(evt -> {
//			obstacle.updateDiscreteForm();
//			layer.getChildren().addAll(obstacle.getDiscreteForm());
//		});
		return obstacle;
	}

}
