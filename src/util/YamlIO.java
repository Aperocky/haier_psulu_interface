package util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import net.sourceforge.yamlbeans.YamlException;
import net.sourceforge.yamlbeans.YamlReader;
import net.sourceforge.yamlbeans.YamlWriter;

public class YamlIO {

	/**
	 * Load the parameters from yaml file
	 * 
	 * @param yamlFilePath
	 * @return
	 */
	public Map<?, ?> loadMap(String yamlFilePath) {
		return (Map<?, ?>) load(yamlFilePath);
	}
	
	public ArrayList<?> loadArray(String yamlFilePath) {
		return (ArrayList<?>) load(yamlFilePath);
	}
	
	/**
	 * Write the map to the specific path
	 * 
	 * @param map
	 * @param writePath
	 */
	public void writeMap(Map<?, ?> map, String writePath) {
		try {
			YamlWriter writer = new YamlWriter(new FileWriter(writePath));
			writer.write(map);
			writer.close();
		} catch (IOException | YamlException ex) {
			ex.printStackTrace();
		}
	}

	private Object load(String yamlFilePath) {
		try {
			YamlReader reader = new YamlReader(new FileReader(yamlFilePath));
			Object object = reader.read();
			return object;
		} catch (IOException | YamlException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
