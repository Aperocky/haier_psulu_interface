package secondVersion.frontend.model.unit.obstacle;

import java.util.Collection;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public interface IObstacle{
	
	public void addVertex(Point2D vertex);
	
	public Collection<Point2D> getVertices();
	
	public boolean selected();
	
	public void unselect();
	
	public IObstacle copy();
	
	public void setColor(Color color);
	
}
