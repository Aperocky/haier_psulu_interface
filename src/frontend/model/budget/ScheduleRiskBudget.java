package frontend.model.budget;

import frontend.util.mixedprogressbar.MixedProgressBar;
import javafx.geometry.Pos;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import model.gamedata.game.gamestats.GameStats;
import util.Observer;

public class ScheduleRiskBudget extends StackPane implements Observer<GameStats> {

	private static final double SPACING = 10d;
	private static final double HEIGHT = 25d;
	private HBox hbox;
	private MixedProgressBar progressBar;
	private Label title;
	private Label percent;

	public ScheduleRiskBudget(double width, double height) {
		this.setPrefSize(width, height);
		hbox = new HBox();
		hbox.setPrefSize(width, height);
		hbox.setSpacing(SPACING);
		hbox.setAlignment(Pos.CENTER);
		
		// Progress Bar
		VBox vbox = new VBox();
		vbox.setSpacing(0);
		vbox.setAlignment(Pos.CENTER);
		progressBar = new MixedProgressBar(width * 0.5d, HEIGHT, 1);
		progressBar.setColor(Color.web("#82CF72"), 0);
		NumberAxis axis = new NumberAxis(-100, 0, 20);
		axis.setMaxSize(width * 0.48d, HEIGHT);
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
		vbox.setTranslateY(15d);

		title = new Label("Schedule");
		percent = new Label("");
		
		hbox.getChildren().addAll(title, vbox, percent);

		this.getChildren().addAll(hbox);
	}

	@Override
	public void update(GameStats stats) {
		double current = 1 - stats.getCurrentScheduleRisk();
		System.out.println("ScheduleRiskBudget line 70: current schedule risk: " + current);
		progressBar.setProgress(current, 0);
		percent.setText("" + (double) Math.round(current * 100) + "%");
	}

}

