# ClickTheCircleGame

## Synopsis
This program is a simple game that can be played for fun. This game can be challenging to get a high score on because it takes a lot of focus and good fine-motor skills to do so. This program works by clicking the start button on the top of the screen. Once the start button is clicked, the timer starts and the animations of the moving shapes begins. The goal is to click the circle in order to gain points. If the number of points are low, the score will be colored red. If the points are better, the score will be orange-colored. If the points are high, the score number will turn green. The game ends by either pressing end game or when the timer runs out. The game can be restarted afterwards. The high score is also displayed at the top of the screen. 

## Motivation 
This program was created for a class project. I wanted to create a game that would show off the skills that I have learned so far in my class. I wanted to make a game that had simple rules, but could still be challenging and/or fun to play. 

## How To Use
The files needed in order for this program to run are the MovingCircle.java file and the Timer.java file. The file that runs the program is the MovingCircle.java file. Once the program is running, use the computer mouse to navigate the program. 
![Screenshot 2024-03-18 224609](https://github.com/KaralineGlazier/ClickTheCircleGame/assets/148881979/735fa8c8-59f5-4611-a099-dc3fddf789ac)

## Code Example
This code snippet is from the MovingCircle.java file in the start method. It changes the score when a certain shape is pressed. It also changes the score color if the score reaches a certain amount. 
```
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
```
