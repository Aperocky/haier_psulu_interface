package frontend.model.unit.keycomponent;

import frontend.model.unit.DraggableImageView;
import javafx.scene.image.Image;

public class KeyComponent extends DraggableImageView {

	public static final double DEFAULT_SIZE = 30d;

	public KeyComponent(String image) {

		this.setImage(new Image(image));
		this.setFitWidth(DEFAULT_SIZE);
		this.setFitHeight(DEFAULT_SIZE);

	}

}
