package frontend.model.budget;

import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.gamedata.game.GameStats;
import util.Observer;

public class RiskBudget extends Pane implements Observer<GameStats> {

	private static final double SPACING = 10d;
	private static final double HEIGHT = 25d;
	private HBox hbox;
	private ProgressBar progressBar;
	private Label title;
	private Label percent;

	public RiskBudget(double width, double height) {
		hbox = new HBox();
		hbox.setPrefSize(width, height);
		hbox.setSpacing(SPACING);
		hbox.setAlignment(Pos.CENTER);
		progressBar = new ProgressBar(1d);
		progressBar.setPrefSize(width * 0.7d, HEIGHT);
		progressBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		title = new Label("Risk");
		percent = new Label("");

		hbox.getChildren().addAll(title, progressBar, percent);
		this.getChildren().add(hbox);
	}

	@Override
	public void update(GameStats stats) {
		double total = stats.getTotalRiskBudget();
		double current = stats.getCurrentRiskBudget();
		double ratio = current / total;
		progressBar.setProgress(ratio);
		if (current > 0)
			percent.setText("" + Math.round(current * 100) + "%");
		else
			percent.setText("out");
	}

}
