package frontend.model.operation.setting;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import javafx.scene.control.TitledPane;
import model.gamedata.game.gamestats.GameStats;

public class GameSetting extends TitledPane{
	
	private GameStats gameStats;
	private JFXButton restartButton;
	
	public GameSetting(double width, double height, GameStats stats) {
		gameStats = stats;
		this.setPrefSize(100, 100);
		restartButton = new JFXButton("Restart");

		this.setText("Game");
		this.setContent(restartButton);
				
		initRestart();
	}
	
	private void initRestart() {
		restartButton.setOnAction(evt -> {
			gameStats.resetCurrentRiskBudget();
			gameStats.resetStartPosition();
			gameStats.setPlannedPath(new ArrayList<>());
		});
	}

}
