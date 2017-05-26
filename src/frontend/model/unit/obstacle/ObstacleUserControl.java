package frontend.model.unit.obstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import frontend.util.usercontrol.basic.BasicCommand;
import frontend.util.usercontrol.basic.KeyboardControl;
import frontend.util.usercontrol.drag.DragGesture;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ObstacleUserControl {

	private KeyboardControl keyboardControl;
	private DragGesture dragGesture;
	private BasicCommand<Obstacle> basicCommand;
	private Consumer<Obstacle> deleteConsumer;
	private Consumer<Obstacle> pasteConsumer;

	public ObstacleUserControl(Obstacle obstacle, Function<Point2D, Point2D> sceneToLocal) {
		this.keyboardControl = new KeyboardControl(obstacle);
		this.keyboardControl.setPrimaryHandler(event -> primaryPressed(event));
		this.keyboardControl.setDeleteHandler(event -> deletePressed(event));
		this.keyboardControl.setCtlCHandler(event -> ctlCPressed(event));
		this.keyboardControl.setCtlVHandler(event -> ctlVPressed(event));
		
		this.dragGesture = new DragGesture(obstacle, sceneToLocal);
		this.dragGesture.setDraggedHandler((dx, dy) -> {
			List<Point2D> altVertices = new ArrayList<>();
			for (Point2D point : obstacle.getVertices()) {
				altVertices.add(new Point2D(point.getX() + dx, point.getY() + dy));
			}
			obstacle.setVertices(altVertices);
		});
		this.basicCommand = new BasicCommand<>();
	}
	
	public void activate() {
		keyboardControl.activate();
	}
	
	public void deactivate() {
		keyboardControl.deactivate();
	}

	public void setDeleteHandler(Consumer<Obstacle> deleteConsumer) {
		this.deleteConsumer = deleteConsumer;
	}

	public Consumer<Obstacle> getDeleteHandler() {
		return this.deleteConsumer;
	}

	public void setPasteHandler(Consumer<Obstacle> pasteConsumer) {
		this.pasteConsumer = pasteConsumer;
	}

	public Consumer<Obstacle> getPasteHandler() {
		return this.pasteConsumer;
	}

	/**
	 * Mark the obstacle being clicked as selected in {@link #basicCommand}
	 * 
	 * @param event
	 */
	private void primaryPressed(MouseEvent event) {
		Obstacle base = (Obstacle) event.getSource();
		// This makes KeyEvent functional
		base.setFocusTraversable(true);
		base.requestFocus();
		basicCommand.select(base);
	}

	private void deletePressed(KeyEvent event) {
		if (deleteConsumer != null && basicCommand.delete().isPresent()) {
			deleteConsumer.accept(basicCommand.delete().get());
		}
	}

	private void ctlCPressed(KeyEvent event) {
		basicCommand.copy();
	}

	private void ctlVPressed(KeyEvent event) {
		if (pasteConsumer != null && basicCommand.paste().isPresent())
			pasteConsumer.accept(basicCommand.paste().get());
	}

}
