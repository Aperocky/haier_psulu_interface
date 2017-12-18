package frontend.model.canvas.layers.concrete;

import java.util.List;
import java.util.function.Consumer;

import frontend.model.canvas.layers.base.LayerBase;
import frontend.model.canvas.layers.base.LayerType;
import frontend.model.unit.keycomponent.KeyComponent;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.gamedata.game.gamestats.GameStats;

public class KeyComponentLayer extends LayerBase {

	private static final String GOAL_IMG = "coral.png";
	private static final String TARGET_IMG = "fish.png";
	private KeyComponent vehicle;
	private KeyComponent goal;

	public KeyComponentLayer(double width, double height) {
		super(width, height);
		vehicle = new KeyComponent(TARGET_IMG);
		goal = new KeyComponent(GOAL_IMG);
		this.getChildren().addAll(vehicle, goal);
	}

	@Override
	public LayerType getType() {
		return LayerType.KeyComponentLayer;
	}
	
	public KeyComponent getVehicle() {
		return vehicle;
	}

	/**
	 * Enable users to drag around the Vehicle and Goal. Let GameStats observe
	 * both positions.
	 */
	@Override
	public void activate() {
		super.activate();
		vehicle.enableDrag(point -> this.sceneToLocal(point));
		goal.enableDrag(point -> this.sceneToLocal(point));
	}

	@Override
	public void deactivate() {
		super.deactivate();
		vehicle.disableDrag();
		goal.disableDrag();
	}

	@Override
	public void update(GameStats gameStats) {
		Point2D start = transform(gameStats.getCurrentPosition());
		Point2D end = transform(gameStats.getFinalDestination());
		goal.setLayoutX(end.getX() - goal.getFitWidth() /2);
		goal.setLayoutY(end.getY() - goal.getFitHeight() / 2);
		vehicle.setLayoutX(start.getX() - vehicle.getFitWidth() / 2);
		vehicle.setLayoutY(start.getY() - vehicle.getFitHeight() / 2);
		// Animate currently executed path
//		animatePath(game.getExecutedPath());

	}

	public void setGoalPositionListener(Consumer<Point2D> handler) {
		goal.setPositionListener(handler);
	}

	public void setVehiclePositionListener(Consumer<Point2D> handler) {
		vehicle.setPositionListener(handler);
	}
	
	
	private void animatePath(List<Point2D> executedPath) {
		if(executedPath == null || executedPath.size() == 0)
			return;
		Path path = new Path();
		for(int i = 0; i < executedPath.size(); i++) {
			Point2D stop = transform(executedPath.get(i));
			path.getElements().add(new MoveTo(stop.getX(), stop.getY()));
		}
	    
	    getChildren().add(path);
	    getChildren().add(vehicle);
	    
	    PathTransition pathTransition = new PathTransition();
	    pathTransition.setDuration(Duration.seconds(8.0));
	    pathTransition.setDelay(Duration.seconds(.5));
	    pathTransition.setPath(path);
	    pathTransition.setNode(vehicle);
	    pathTransition
	        .setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
	    pathTransition.setCycleCount(Timeline.INDEFINITE);
	    pathTransition.play();
	}

}
