package model.gamedata.game.param;

import java.util.Map;

import util.YamlIO;

public class ParamIO extends YamlIO{
	public String ORIGINAL_PARAMETER_PATH = "/Users/Feng/Documents/workspace/haier_psulu_interface/src/psulu/config/param.yaml";
	public String TEMP_PARAMETER_PATH = "/Users/Feng/Documents/workspace/haier_psulu_interface/src/psulu/config/tmp/tmpParam.yaml";

	@SuppressWarnings("unchecked")
	public Map<String, Object> loadOriginal() {
		return (Map<String, Object>)this.loadMap(ORIGINAL_PARAMETER_PATH);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> loadTemp() {
		return (Map<String, Object>)this.loadMap(TEMP_PARAMETER_PATH);
	}
	
	public void writeTemp(Map<String, Object> map) {
		super.writeMap(map, TEMP_PARAMETER_PATH);
	}
	
	

}
