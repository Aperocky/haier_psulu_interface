package frontend.model.canvas.layers.concrete;

import java.util.function.Consumer;

import frontend.model.canvas.layers.base.LayerBase;
import frontend.model.canvas.layers.base.LayerType;
import frontend.model.unit.keycomponent.KeyComponent;
import javafx.geometry.Point2D;
import model.gamedata.game.GameStats;

public class KeyComponentLayer extends LayerBase {

	private static final String GOAL_IMG = "flag.png";
	private static final String TARGET_IMG = "submarine.png";
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
		vehicle.setLayoutX(start.getX() - vehicle.getFitWidth() / 2);
		vehicle.setLayoutY(start.getY() - vehicle.getFitHeight() / 2);
		goal.setLayoutX(end.getX() - goal.getFitWidth() /2);
		goal.setLayoutY(end.getY() - goal.getFitHeight() / 2);
	}

	public void setGoalPositionListener(Consumer<Point2D> handler) {
		goal.setPositionListener(handler);
	}

	public void setVehiclePositionListener(Consumer<Point2D> handler) {
		vehicle.setPositionListener(handler);
	}

}
