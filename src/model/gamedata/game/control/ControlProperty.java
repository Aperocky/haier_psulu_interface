package model.gamedata.game.control;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import frontend.model.operation.control.ControlType;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.gamedata.game.param.ParamIO;

/**
 * Container for all user's controls over path planning
 * 
 * @author Feng
 *
 */
public class ControlProperty {

	private Map<ControlType, DoubleProperty> controlMap;
	private ExecutorService executor;
	private ParamIO paramIO;

	public ControlProperty() {
		paramIO = new ParamIO();
		executor = Executors.newSingleThreadExecutor();
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

	public Future<Integer> updateParamFile() {
		Callable<Integer> updateTask = () -> {
			Map<String, Object> updateMap = paramIO.loadTemp();
			for (ControlType type : ControlType.values()) {
				// System.out.println("Update Value of " + type.key() + " to " +
				// controlMap.get(type).doubleValue());
				String updateValue = null;
				if (type.key().equals("waypoints"))
					updateValue = Integer.toString((int) Math.round(controlMap.get(type).doubleValue()));
				else
					updateValue = Double.toString(controlMap.get(type).doubleValue());
				updateMap.put(type.key(), updateValue);
			}
			paramIO.writeTemp(updateMap);
			return 1;
		};
		Future<Integer> updateResult = executor.submit(updateTask);
		return updateResult;

	}

	public void setOnChanged(Runnable controlHandler) {
		for (ControlType type : ControlType.values()) {
			controlMap.get(type).addListener((obs, oldv, newv) -> {
				try {
					// System.out.println("Changed value of " + type.key() + "to "
					// + newv.doubleValue());
					Future<Integer> result = updateParamFile();
					result.get();
					if (result.isDone())
						controlHandler.run();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			});
		}
	}

	public double getControlValue(ControlType type) {
		return controlMap.get(type).doubleValue();
	}

	public DoubleProperty getControlProperty(ControlType type) {
		return controlMap.get(type);
	}

	private void createTempParamFile() {
		Map<String, Object> rawMap = paramIO.loadOriginal();
		paramIO.writeTemp(rawMap);
	}

}
