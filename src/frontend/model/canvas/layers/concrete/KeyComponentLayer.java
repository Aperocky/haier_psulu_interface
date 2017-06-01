package frontend.model.canvas.layers.concrete;

import java.util.function.Consumer;

import frontend.model.canvas.layers.base.LayerBase;
import frontend.model.canvas.layers.base.LayerType;
import frontend.model.unit.Goal;
import frontend.model.unit.Vehicle;
import javafx.geometry.Point2D;
import model.gamedata.game.GameStats;

public class KeyComponentLayer extends LayerBase {

	private Vehicle vehicle;
	private Goal goal;

	public KeyComponentLayer(double width, double height) {
		super(width, height);
		vehicle = new Vehicle();
		goal = new Goal();
		this.getChildren().addAll(vehicle, goal);
	}

	@Override
	public LayerType getType() {
		return LayerType.KeyComponentLayer;
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
		this.clear();
		Point2D start = gameStats.getCurrentPosition();
		Point2D end = gameStats.getFinalDestination();
		vehicle.setLayoutX(start.getX());
		vehicle.setLayoutY(start.getY());
		goal.setLayoutX(end.getX());
		goal.setLayoutY(end.getY());
	}

	public void setGoalPositionListener(Consumer<Point2D> handler) {
		goal.setPositionListener(handler);
	}

	public void setVehiclePositionListener(Consumer<Point2D> handler) {
		vehicle.setPositionListener(handler);
	}

}
