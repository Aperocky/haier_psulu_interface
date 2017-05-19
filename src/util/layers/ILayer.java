package util.layers;

import javafx.scene.paint.Color;

public interface ILayer {
	
	public boolean isActive();
	
	public void activate();
	
	public void deactivate();
	
	public void setTransparent();
	
	public void clear();
	
	public void setColor(Color color);

}
