package frontend.model.budget;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.gamedata.game.gamestats.GameStats;
import util.Observer;

public class SurfacingBudget extends StackPane implements Observer<GameStats>{
	private Label surfacing;
	
	public SurfacingBudget(double width, double height) {
		this.setPrefSize(width, height);
		
		surfacing = new Label("Surfacing: ");
		
		this.getChildren().add(surfacing);
		StackPane.setAlignment(surfacing, Pos.CENTER_LEFT);
	}
	
	@Override
	public void update(GameStats arg) {
		int surfacingBgt = arg.getCurrentSurfacingBudget();
		if(surfacingBgt >= 0){
			surfacing.setText("Surfacing " + surfacingBgt);
		}
		else {
			surfacing.setText("Surfacing out");
			surfacing.setTextFill(Color.RED);
		}
	}

}
