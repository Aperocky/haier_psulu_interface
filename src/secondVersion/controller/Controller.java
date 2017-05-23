package secondVersion.controller;

import secondVersion.frontend.general.EditorPanel;
import secondVersion.frontend.general.GamePanel;
import secondVersion.model.general.Game;

public class Controller {
	private Game game;
	private EditorPanel editorPanel;
	private GamePanel gamePanel;
	
	public Controller() {
		
		
		observeRiskAndHorizon();
	}
	
	
	public void launchEditor() {
		
	}
	
	public void launchGame() {
		
	}
	
	private void observeRiskAndHorizon() {
		game.observeRecedingHorizonProperty(gamePanel.getHorizonProperty());
		game.observeRiskBudgetProperty(gamePanel.getRiskProperty());
	}
	
	

}
