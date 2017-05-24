package secondVersion.frontend.model.unit.obstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import secondVersion.frontend.util.usercontrol.basic.BasicCommand;
import secondVersion.frontend.util.usercontrol.basic.KeyboardControl;
import secondVersion.frontend.util.usercontrol.drag.DragGesture;

public class ObstacleUserControl extends KeyboardControl {

	private DragGesture dragGesture;
	private BasicCommand<Obstacle> basicCommand;
	private Consumer<Obstacle> deleteConsumer;
	private Consumer<Obstacle> pasteConsumer;

	public ObstacleUserControl(Obstacle obstacle, Function<Point2D, Point2D> sceneToLocal) {
		super(obstacle);
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

	public void deleteHandler(Consumer<Obstacle> deleteConsumer) {
		this.deleteConsumer = deleteConsumer;
	}

	public void pasteHandler(Consumer<Obstacle> pasteConsumer) {
		this.pasteConsumer = pasteConsumer;
	}

	public Consumer<Obstacle> getPasteHandler() {
		return this.pasteConsumer;
	}

	/**
	 * Mark the obstacle being clicked as {@link #selected}
	 * 
	 * @param event
	 */
	@Override
	protected void primaryPressed(MouseEvent event) {
		Obstacle base = (Obstacle) event.getSource();
		// This makes KeyEvent functional
		base.setFocusTraversable(true);
		base.requestFocus();
		basicCommand.select(base);
	}

	@Override
	protected void deletePressed(KeyEvent event) {
		if (deleteConsumer != null && basicCommand.delete().isPresent()) {
			deleteConsumer.accept(basicCommand.delete().get());
		}
	}

	public Consumer<Obstacle> getDeleteHandler() {
		return this.deleteConsumer;
	}

	@Override
	protected void ctlCPressed(KeyEvent event) {
		basicCommand.copy();
	}

	@Override
	protected void ctlVPressed(KeyEvent event) {
		if (pasteConsumer != null && basicCommand.paste().isPresent())
			pasteConsumer.accept(basicCommand.paste().get());
	}

	// /**
	// * Initialize a new obstacle that is passed in or finish up initializing.
	// * Pass the new obstacle on to the {@link #initConsumer} for further
	// action
	// */
	// @Override
	// protected void secondaryPressed(MouseEvent event) {
	// if (initializing == null && initConsumer != null && initSupplier != null)
	// {
	// initializing = initSupplier.get();
	// Point2D vertex = sceneToLocal.apply(new Point2D(event.getSceneX(),
	// event.getSceneY()));
	// initializing.addVertex(vertex);
	// initConsumer.accept(initializing);
	// } else
	// initializing = null;
	// }

	// public Optional<Obstacle> initializeObstacle(Obstacle obstacle, double
	// localX, double localY) {
	// // System.out.println("Secondary Mouse Pressed on ObstacleLayer.");
	// if (initializing == null) {
	// initializing = obstacle;
	// Point2D vertex = new Point2D(localX, localY);
	// initializing.addVertex(vertex);
	// } else {
	// initializing = null;
	// }
	// return Optional.ofNullable(initializing);
	// }
	//
	// /**
	// * If currently initializing an obstacle, add one more vertex to it, else
	// mark
	// * the obstacle being clicked as {@link #selected}
	// *
	// * @param event
	// */
	// public void editObstacle(Optional<Obstacle> select, double localX, double
	// localY) {
	// System.out.println("Primary Mouse Pressed on ObstacleLayer.");
	// if (initializing != null) {
	// // Add one more vertex to the initializing polygon being created
	// Point2D vertex = new Point2D(localX, localY);
	// initializing.addVertex(vertex);
	// } else {
	// if (select.isPresent())
	// selected = select.get();
	// }
	// }

}
