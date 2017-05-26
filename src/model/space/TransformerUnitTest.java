package model.space;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;

public class TransformerUnitTest {
	
	public static void main(String[] args) {
		DiscreteTransformer transformer = new DiscreteTransformer(1);
		List<Point2D> polygon = new ArrayList<>();
		polygon.add(new Point2D(7, 14));
		polygon.add(new Point2D(16, 23));
		polygon.add(new Point2D(25, 11));

//		for(Point2D point : transformer.toDiscretePolygon(100, 100, polygon)){
//			System.out.println("Polygon represented in discrete space");
//			System.out.println("Point X: " + point.getX() + " ,Point Y: " + point.getY());
//		}
	}

}
