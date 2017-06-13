package model.gamedata.game;

import java.util.HashMap;
import java.util.Map;

import frontend.model.operation.control.ControlType;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Container for all user's controls over path planning
 * 
 * @author Feng
 *
 */
public class ControlProperty {

	private Map<ControlType, DoubleProperty> controlMap;

	public ControlProperty() {
		controlMap = new HashMap<>();
		for (ControlType type : ControlType.values()) {
			controlMap.put(type, new SimpleDoubleProperty());
		}
	}

	public void setOnChanged(Runnable controlHandler) {
		for (ControlType type : ControlType.values()) {
			controlMap.get(type).addListener((obs, oldv, newv) -> controlHandler.run());
		}
	}

	public void setValueToType(ControlType type, double value) {
		controlMap.get(type).set(value);
	}

	public double getControlValue(ControlType type) {
		return controlMap.get(type).doubleValue();
	}

	public DoubleProperty getControlProperty(ControlType type) {
		return controlMap.get(type);
	}

}
