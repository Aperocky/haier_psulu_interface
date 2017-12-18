package frontend.model.budget;

import frontend.util.mixedprogressbar.MixedProgressBar;
import javafx.geometry.Pos;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
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
		
		// Progress Bar
		VBox vbox = new VBox();
		vbox.setSpacing(0);
		progressBar = new MixedProgressBar(width * 0.7d, HEIGHT, 2);
		progressBar.setColor(Color.web("#C6DAFC"), 0);
		progressBar.setColor(Color.web("#659BFB"), 1);
		NumberAxis axis = new NumberAxis(-80, 0, 10);
		axis.setMaxSize(width * 0.68d, HEIGHT);
		axis.setTranslateX(width * 0.01d);
		axis.setTranslateY(-5);
		axis.setTickMarkVisible(true);
		axis.setTickLabelFormatter(new StringConverter<Number>() {
	        @Override
	        public String toString(Number object) {
	            return (object.intValue() * -1) + "%";
	        }

	        @Override
	        public Number fromString(String string) {
	            return 0;
	        }
	    });
		vbox.getChildren().addAll(progressBar, axis);
		vbox.setTranslateY(20d);

		title = new Label("Risk Budget");
		percent = new Label("");

		hbox.getChildren().addAll(title, vbox, percent);

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
			percent.setText("" + (double) Math.round(currRatio * 100) / 1.25 + "%");
		else {
			percent.setText("out"); 
			percent.setTextFill(Color.RED);
		}
	}

}
