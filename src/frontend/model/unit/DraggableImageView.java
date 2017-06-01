package frontend.model.unit;

import java.util.function.Consumer;
import java.util.function.Function;

import frontend.util.usercontrol.drag.DragGesture;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DraggableImageView extends ImageView {

	private DragGesture dragGesture;
	private String image;

	public DraggableImageView() {

	}

	public void enableDrag(Function<Point2D, Point2D> sceneToLocal) {
		dragGesture = new DragGesture(this, sceneToLocal);
		dragGesture.primaryEnable();
	}

	public void disableDrag() {
		dragGesture.disable();
	}

	public void setImage(String image) {
		this.image = image;
		this.setImage(new Image(getClass().getClassLoader().getResourceAsStream(image)));
	}

	public void setPositionListener(Consumer<Point2D> handler) {
		this.setOnMouseReleased(evt -> {
			Point2D pos = new Point2D(this.getLayoutX() + this.getTranslateX(),
					this.getLayoutY() + this.getTranslateY());
			handler.accept(pos);
		});
	}

}
