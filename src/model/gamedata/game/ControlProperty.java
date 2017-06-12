package model.gamedata.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import util.TriConsumer;

/**
 * Container for all user's controls over path planning
 * 
 * @author Feng
 *
 */
public class ControlProperty implements Iterable<DoubleProperty>{

	/**
	 * In Haier this property is called risk budget
	 */
	private DoubleProperty chanceConstraintProperty;
	
	/**
	 * Number of way points from start to end
	 */
	private DoubleProperty wayPointsProperty;
	
	/**
	 * Maximum step size. This together with {@link #wayPointsProperty} determine each step size 
	 */
	private DoubleProperty maxVelocityProperty;

	public ControlProperty() {
		chanceConstraintProperty = new SimpleDoubleProperty();
		wayPointsProperty = new SimpleDoubleProperty();
		maxVelocityProperty = new SimpleDoubleProperty();
	}
	
	public void setOnChanged(TriConsumer<Double, Double, Double> changedHandler) {
		chanceConstraintProperty.addListener((obs, oldv, newv) -> {
			changedHandler.accept(newv.doubleValue(), this.getWayPoints().doubleValue(), this.getMaxVelocity());
		}); 
		wayPointsProperty.addListener((obs, oldv, newv) -> {
			changedHandler.accept(this.getChanceConstraint(), newv.doubleValue(), this.getMaxVelocity());
		}); 
		maxVelocityProperty.addListener((obs, oldv, newv) -> {
			changedHandler.accept(this.getChanceConstraint(), this.getWayPoints().doubleValue(), newv.doubleValue());
		}); 
	}

	public void setChanceConstraint(double constraint) {
		chanceConstraintProperty.set(constraint);
	}

	public DoubleProperty getChanceConstraintProperty() {
		return chanceConstraintProperty;
	}

	public DoubleProperty getWayPointsProperty() {
		return wayPointsProperty;
	}

	public DoubleProperty getMaxVelocityProperty() {
		return maxVelocityProperty;
	}

	public void setWayPoints(double waypoints) {
		wayPointsProperty.set(waypoints);
	}
	
	public void setMaxVelocity(double maxVelocity) {
		maxVelocityProperty.set(maxVelocity);
	}

	public Double getChanceConstraint() {
		return chanceConstraintProperty.doubleValue();
	}

	public Integer getWayPoints() {
		return wayPointsProperty.intValue();
	}
	
	public Double getMaxVelocity() {
		return maxVelocityProperty.doubleValue();
	}

	@Override
	public Iterator<DoubleProperty> iterator() {
		List<DoubleProperty> list = new ArrayList<>();
		list.add(chanceConstraintProperty);
		list.add(maxVelocityProperty);
		list.add(wayPointsProperty);
		return list.iterator();
	}

}
