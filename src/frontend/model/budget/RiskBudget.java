package frontend.model.budget;

import frontend.util.mixedprogressbar.MixedProgressBar;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.gamedata.game.gamestats.GameStats;
import util.Observer;

public class RiskBudget extends StackPane implements Observer<GameStats> {

	private static final double SPACING = 10d;
	private static final double HEIGHT = 25d;
	private HBox hbox;
	private MixedProgressBar progressBar;
	private Label title;
	private Label percent;

	public RiskBudget(double width, double height) {
		this.setPrefSize(width, height);
		hbox = new HBox();
		hbox.setPrefSize(width, height);
		hbox.setSpacing(SPACING);
		hbox.setAlignment(Pos.CENTER);
		progressBar = new MixedProgressBar(width * 0.7d, HEIGHT, 2);
		progressBar.setColor(Color.web("#C6DAFC"), 0);
		progressBar.setColor(Color.web("#659BFB"), 1);

		title = new Label("Total Risk");
		percent = new Label("");

		hbox.getChildren().addAll(title, progressBar, percent);

		this.getChildren().addAll(hbox);
	}

	@Override
	public void update(GameStats stats) {
		double total = stats.getTotalRiskBudget();
		double current = stats.getCurrentRiskBudget();
		double expected = stats.getExpectedRiskBudget();
		double currRatio = current / total;
		double expectedRatio = expected / total;
		progressBar.setProgress(currRatio, 0);
		progressBar.setProgress(expectedRatio, 1);
		if (current > 0)
			percent.setText("" + (double) Math.round(currRatio * 100) / 10 + "%");
		else {
			percent.setText("out"); 
			percent.setTextFill(Color.RED);
		}
	}

}
