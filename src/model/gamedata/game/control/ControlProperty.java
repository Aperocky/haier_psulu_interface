package model.gamedata.game.control;

import java.util.HashMap;
import java.util.Map;

import frontend.model.operation.control.ControlType;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;
import model.gamedata.game.param.ParamIO;

/**
 * Container for all user's controls over path planning
 * 
 * @author Feng
 *
 */
public class ControlProperty {

	private Map<ControlType, DoubleProperty> controlMap;
	private ParamIO paramIO;

	public ControlProperty() {
		paramIO = new ParamIO();
		createTempParamFile();
		controlMap = load();
	}

	/**
	 * Load the parameters from yaml file and store them in controlMap
	 * 
	 * @param yamlFilePath
	 * @return
	 */
	public Map<ControlType, DoubleProperty> load() {
		Map<String, Object> rawMap = paramIO.loadTemp();
		Map<ControlType, DoubleProperty> processedMap = new HashMap<>();
		for (ControlType type : ControlType.values()) {
			String str = (String) rawMap.get(type.key());
			double value = Double.parseDouble(str);
			DoubleProperty doubleProperty = new SimpleDoubleProperty(value);
			processedMap.put(type, doubleProperty);
		}
		return processedMap;
	}

	public void setOnChanged(Runnable controlHandler) {
		for (ControlType type : ControlType.values()) {
			controlMap.get(type).addListener((obs, oldv, newv) -> {
				// System.out.println("Changed value of " + type.key() + "to" +
				// newv.doubleValue());
				updateParamFile(controlHandler);
			});
		}
	}

	public double getControlValue(ControlType type) {
		return controlMap.get(type).doubleValue();
	}

	public DoubleProperty getControlProperty(ControlType type) {
		return controlMap.get(type);
	}

	private void updateParamFile(Runnable valueChangeHandler) {
		Task<Void> updateTask = new Task<Void>() {
			@Override
			protected Void call() {
				Map<String, Object> updateMap = paramIO.loadTemp();
				for (ControlType type : ControlType.values()) {
					// System.out.println("Update Value of " + type.key() + " to
					// " +
					// controlMap.get(type).doubleValue());
					String updateValue = null;
					if (type.key().equals("waypoints"))
						updateValue = Integer.toString((int) Math.round(controlMap.get(type).doubleValue()));
					else
						updateValue = Double.toString(controlMap.get(type).doubleValue());
					updateMap.put(type.key(), updateValue);
				}
				paramIO.writeTemp(updateMap);
				return null;
			}
		};

		updateTask.setOnSucceeded(evt -> valueChangeHandler.run());

		Thread valueChangeThread = new Thread(updateTask);
		valueChangeThread.setDaemon(true);
		valueChangeThread.start();
	}

	private void createTempParamFile() {
		Map<String, Object> rawMap = paramIO.loadOriginal();
		paramIO.writeTemp(rawMap);
	}

}
