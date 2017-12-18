package frontend.model.canvas.layers.concrete;

import java.util.List;
import java.util.function.Consumer;

import frontend.model.canvas.layers.base.LayerBase;
import frontend.model.canvas.layers.base.LayerType;
import frontend.model.unit.keycomponent.KeyComponent;
import javafx.animation.PathTransition;
import javafx.geometry.Point2D;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.gamedata.game.gamestats.GameStats;
import model.status.StatusManager;

public class KeyComponentLayer extends LayerBase {

	private static final String GOAL_IMG = "coral.png";
	private static final String TARGET_IMG = "fish.png";
	private final KeyComponent vehicle;
	private final KeyComponent goal;
	private StatusManager statusManager;

	public KeyComponentLayer(double width, double height) {
		super(width, height);
		vehicle = new KeyComponent(TARGET_IMG);
		goal = new KeyComponent(GOAL_IMG);
		this.getChildren().addAll(vehicle, goal);
		
		Point2D end = transform(new Point2D(1,1));
		goal.setTranslateX(end.getX() - goal.getFitWidth() /2);
		goal.setTranslateY(end.getY() - goal.getFitHeight() / 2);	
		Point2D start = transform(new Point2D(0,0));
		vehicle.setTranslateX(start.getX() - vehicle.getFitWidth() / 2);
		vehicle.setTranslateY(start.getY() - vehicle.getFitHeight() / 2);
	}

	public void setStatusManager(StatusManager manager){
		this.statusManager = manager;
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
		if(statusManager != null && statusManager.isExecuting()){
			// Animate currently executed path
			animatePath(gameStats.getExecutedPath());
		}
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
		
		final Path path = new Path();
		Point2D first = transform(executedPath.get(0));
		path.getElements().add(new MoveTo(first.getX(), first.getY()));
		for(int i = 1; i < executedPath.size(); i++) {
			Point2D stop = transform(executedPath.get(i));
			LineTo lineTo = new LineTo(stop.getX(), stop.getY());
			path.getElements().add(lineTo);
		}
		path.setOpacity(0);
	    getChildren().add(path);
	    
	    final PathTransition pathTransition = new PathTransition();
	    pathTransition.setDuration(Duration.seconds(2.0));
	    pathTransition.setDelay(Duration.seconds(0.5));
	    pathTransition.setPath(path);
	    pathTransition.setNode(vehicle);
	    pathTransition
	        .setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
	    pathTransition.setCycleCount(1);
	    pathTransition.play();
	    
	}

}
