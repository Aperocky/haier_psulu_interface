package model.gamedata.game.param;

import java.util.Map;

import util.ResourceParser;
import util.YamlIO;

public class ParamIO extends YamlIO {

	private ResourceParser parser;

	public ParamIO() {
		parser = new ResourceParser("path");
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> loadOriginal() {
		return (Map<String, Object>) this.loadMap(parser.getString("original_param"));
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> loadTemp() {
		return (Map<String, Object>) this.loadMap(parser.getString("temp_param"));
	}

	public void writeTemp(Map<String, Object> map) {
		super.writeMap(map, parser.getString("temp_param"));
	}

}
