package frontend.model.operation.setting;

import javafx.scene.control.Accordion;
import model.gamedata.Environment;

public class Menu extends Accordion {

	private GameSetting gameSetting;

	public Menu(double width, double height) {
		this.setPrefSize(width, height);

	}
	
	public void fill(Environment environment) {
		gameSetting = new GameSetting(this.getPrefWidth(), 30d, environment.getGameStats());
		
		this.getPanes().addAll(gameSetting);
	}
	
	

}
