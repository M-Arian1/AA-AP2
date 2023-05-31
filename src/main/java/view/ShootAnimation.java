package view;

import controller.SettingsController;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Ball;
import model.Game;

import java.util.ArrayList;

public class ShootAnimation extends Transition {

    private Ball ball;
    private AnchorPane Anchorpane;


    public ShootAnimation(AnchorPane anchorPane, Ball ball) {
        this.ball = ball;
        this.Anchorpane = anchorPane;

        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {

        if(checkCollision(Anchorpane,ball)) {
            ball.setCenterY(ball.getCenterY() - 5*Math.cos(Math.toRadians(GameMenu.windSpeed)));
            ball.setCenterX(ball.getCenterX() + 5*Math.sin(Math.toRadians(GameMenu.windSpeed)));
            ball.getBallText().setY(ball.getBallText().getY() - 5*Math.cos(Math.toRadians(GameMenu.windSpeed)));
            ball.getBallText().setX(ball.getBallText().getX() + 5*Math.sin(Math.toRadians(GameMenu.windSpeed)));
        }else{
            this.stop();
        }
    }

    private Boolean checkCollision(AnchorPane gamePane, Ball ball) {
        Boolean isLost = false;
        ArrayList<Ball> balls = GameMenu.gameController.getConnectedBalls();
        for (Ball ball1 : balls) {
//            if (ball.getBoundsInParent().intersects(ball1.getBoundsInParent())) {
            if(!ball.equals(ball1)) {
                if (Math.pow(ball.getCenterX() - ball1.getCenterX(), 2) + Math.pow(ball.getCenterY() - ball1.getCenterY(), 2) < Math.pow(2* SettingsController.getBallRadius(), 2)) {
                    isLost = true;
                    break;
                }
            }
        }
        if(GameMenu.phase == 4){
            if(ball.getCenterX() > 800 || ball.getCenterX() < 0 || ball.getCenterY() > 600 || ball.getCenterY() < 0){
                isLost = true;
            }
        }


        if (isLost) {
            GameMenu.lostTheGame();
            return false;
            //please don't f up
        }
        else{
            if(Math.pow(ball.getCenterY()-300,2) + Math.pow(ball.getCenterX()-400,2) < Math.pow(GameMenu.invisibleCircleRadius,2)) {
                this.stop();
                GameMenu.iceModeCount++;
                GameMenu.progressBar.setProgress(GameMenu.iceModeCount/SettingsController.getIceModeNeededBalls());
                GameMenu.score += 5;
                GameMenu.scoreLabel.setText("Score: " + GameMenu.score);
                GameMenu.gameController.addConnectedBall(ball);

                GameMenu.gameController.removeAllAnimation(this);

                RotationAnimation2 rotationAnimation = new RotationAnimation2(gamePane, ball, GameMenu.invisibleCircle, GameMenu.angleSpeedInput ,Math.atan2(ball.getCenterY() - GameMenu.invisibleCircle.getCenterY(), ball.getCenterX() - GameMenu.invisibleCircle.getCenterX()));
                rotationAnimation.setDelay(Duration.millis(0));
                GameMenu.gameController.addAllAnimation(rotationAnimation);

                rotationAnimation.play();
                GameMenu.checkPhase(gamePane);
                return false;
            }
        }
        return true;
    }
}
