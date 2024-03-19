import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
/**
 * <h1>Moving Circle</h1>
 * <p>This class creates a game where the goal is to click on the moving circle shape in order to gain points.
 * Clicking on a square takes away points. There is a count down for 1 minute.</p>
 * <p>Created: 03/18/2024</p>
 * 
 * @author Karaline Glazier
 */
public class MovingCircle extends Application {
	int points = 0;
	int highscore = 0;
	@Override
	/**
	 *  <p>This is the start method. It adds elements onto the stage so that the program can be ran using GUIs.
	 * It includes animations, shapes, buttons, and text inside the pane.
	 */
	public void start(Stage primaryStage) throws Exception {
		BorderPane pane = new BorderPane();
		Pane shapePane = new Pane();
		//Shapes start at a random point
		int x = (int)(Math.random() * 400);
		int y = (int)((Math.random() * 300) + 50);
		//Create start button, score, high score, and timer
		Button bt1 = new Button("Start");
		Text score = new Text("Score: " + points);
		Text highScore = new Text("High Score: " + highscore);
		Timer timer = new Timer();
		Text time = new Text("01:00");
		score.setFont(Font.font(20));
		highScore.setFont(Font.font(20));
		time.setFont(Font.font(20));
		Text over = new Text("Game Over");
		over.setFill(Color.RED);
		over.setFont(Font.font(50));
		//Create Shapes
		Circle circle = new Circle(x, y, 20);
		Rectangle square = new Rectangle(x, y, 50, 50);
		Rectangle square2 = new Rectangle(x, y, 50, 50);
		Rectangle square3 = new Rectangle(x, y, 50, 50);
		Rectangle square4 = new Rectangle(x, y, 50, 50);
		//Add color to Shapes
		square.setFill(Color.BLACK);
		square2.setFill(Color.BLACK);
		square3.setFill(Color.BLACK);
		square4.setFill(Color.BLACK);
		circle.setFill(Color.BLUE);
		
		//Add items to HBox and the pane
		HBox hBox = new HBox(15);
		hBox.getChildren().add(score);
		hBox.getChildren().add(time);
		hBox.getChildren().add(bt1);
		hBox.getChildren().add(highScore);
		pane.setTop(hBox);
		pane.setCenter(shapePane);
		
		
		
		
		//create event handler for circle animation
		EventHandler<ActionEvent> eventHandler = e -> {
				circle.setCenterX(Math.random() * 400);
				circle.setCenterY((Math.random() * 300) + 50);
		};
		//Circle animation that continuously moves to a random point on the screen every 1 second
		Timeline animation = new Timeline(
				new KeyFrame(Duration.millis(1000), eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.pause();

	//create event handler for square animation
	EventHandler<ActionEvent> eventHandler1 = e -> {
			square.setX(Math.random() * 400);
			square.setY((Math.random() * 300) + 50);
			square2.setY(Math.random() * 400);
			square2.setX((Math.random() * 300) + 50);
			square3.setY(Math.random() * 400);
			square3.setX((Math.random() * 300) + 50);
			square4.setY(Math.random() * 400);
			square4.setX((Math.random() * 300) + 50);
		};
		//Square animation that continuously moves to a random spot on the screen every 800 miliseconds 
		Timeline animation2 = new Timeline(
			new KeyFrame(Duration.millis(800), eventHandler1));
		animation2.setCycleCount(Timeline.INDEFINITE);
		animation2.pause();
		
		//create timer animation
		Timeline countdown = new Timeline(
				new KeyFrame(Duration.millis(1000), e -> {
					timer.decrease();
					time.setText(timer.toString());
				}));
			countdown.setCycleCount(60);
			countdown.pause();
		
			//Start button click 
			//figure out how to get restart button to work
		bt1.setOnMouseClicked( e -> {
			//Start Button 
			if (bt1.getText().equals("Start")) {
					shapePane.getChildren().addAll(square, square2, square3, square4);
					shapePane.getChildren().add(circle);
					animation2.play();
					animation.play();
					countdown.play();
					bt1.setText("End Game");
			}
			//End Game Button
			else if (bt1.getText().equals("End Game")){
				animation.pause();
				animation2.pause();
				countdown.pause();
				pane.setCenter(over);
				bt1.setText("ReStart");
				//create a new high score
				if(points > highscore) {
					highscore = points;
				}
				highScore.setText("High Score: " + highscore);
			}
			//If restart button clicked
			else if (bt1.getText().equals("ReStart")) {
				points = 0;
				score.setFill(Color.BLACK);
				score.setText("Score: " + points);
				timer.reset();
				time.setText("01:00");
				bt1.setText("Start");
				shapePane.getChildren().removeAll(circle, square, square2, square3, square4);
				pane.setCenter(shapePane);
			}
			
		});
		
		//count down animation to keep track of how much the variable "amount" equals every second
		Timeline countdown2 = new Timeline(
				new KeyFrame(Duration.millis(1000), e -> {
					// if variable "amount" is equal to 0 (if count down runs out), stop the animation
					if(timer.getAmount() == 0) {
						animation.pause();
						animation2.pause();
						countdown.pause();
						pane.setCenter(over);
						bt1.setText("ReStart");
						if(points > highscore) {
							highscore = points;
						}
						highScore.setText("High Score: " + highscore);
					}
				}));
		
			countdown2.setCycleCount(Timeline.INDEFINITE);
			countdown2.play();
		
			//circle clicked gain 10 points
			circle.setOnMouseClicked(e -> {
				if (countdown.getStatus() == Animation.Status.RUNNING) {
					points += 10;
					if(points < 200) {
						score.setFill(Color.RED);
					}
					else if (points >= 200 && points < 400) {
						score.setFill(Color.ORANGE);
					}
					else {
						score.setFill(Color.GREEN);
					}
				score.setText("Score: " + points);
				}
		});
			//square clicked lose 5 points 
			square.setOnMouseClicked(e -> {
				if (countdown.getStatus() == Animation.Status.RUNNING) {
					points += -5;
					if(points < 200) {
						score.setFill(Color.RED);
					}
					else if (points >= 200 && points < 400) {
						score.setFill(Color.ORANGE);
					}
					else {
						score.setFill(Color.GREEN);
					}
					score.setText("Score: " + points);
				}
			});
			
			square2.setOnMouseClicked(e -> {
				if (countdown.getStatus() == Animation.Status.RUNNING) {
					points += -5;
					if(points < 200) {
						score.setFill(Color.RED);
					}
					else if (points >= 200 && points < 400) {
						score.setFill(Color.ORANGE);
					}
					else {
						score.setFill(Color.GREEN);
					}
					score.setText("Score: " + points);
				}
			});
			
			square3.setOnMouseClicked(e -> {
				if (countdown.getStatus() == Animation.Status.RUNNING) {
					points += -5;
					if(points < 200) {
						score.setFill(Color.RED);
					}
					else if (points >= 200 && points < 400) {
						score.setFill(Color.ORANGE);
					}
					else {
						score.setFill(Color.GREEN);
					}
					score.setText("Score: " + points);
				}
			});
			
			square4.setOnMouseClicked(e -> {
				if (countdown.getStatus() == Animation.Status.RUNNING) {
					points += -5;
					if(points < 200) {
						score.setFill(Color.RED);
					}
					else if (points >= 200 && points < 400) {
						score.setFill(Color.ORANGE);
					}
					else {
						score.setFill(Color.GREEN);
					}
					score.setText("Score: " + points);
				}
			});
			
			
		
		//add nodes to the scene
		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setTitle("MovingCircle");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	/**
	 * This is the main method. It is used to launch the arguments of the class so that the program can be displayed.
	 *  
	 * @param args (String[]; this represents the arguments made in the class.)
	 */
	public static void main(String[] args) {
		launch(args);
	}

	
}