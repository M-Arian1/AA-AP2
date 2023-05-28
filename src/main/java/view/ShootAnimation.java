package view;

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
        System.out.println("YOOOOOO");
        if(checkCollision(Anchorpane,ball)) {
            ball.setCenterY(ball.getCenterY() - 1);
            ball.getBallText().setLayoutY(ball.getBallText().getLayoutY() - 1);
        }else{
            //TODO: give ball rotation
        }
    }

    private Boolean checkCollision(AnchorPane gamePane, Ball ball) {
        Boolean isLost = false;
        ArrayList<Ball> balls = GameMenu.gameController.getConnectedBalls();
        for (Ball ball1 : balls) {
            if (ball.getBoundsInParent().intersects(ball1.getBoundsInParent())) {
                isLost = true;
                break;
            }
        }
        System.out.println(isLost);
//        if (isLost) {
//            for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
//                allAnimation.stop();
//            }
//            return false;
//            //TODO: show lost page
//        }else{
//            if(Math.pow(ball.getCenterY()-300,2) + Math.pow(ball.getCenterY()-300,2) < Math.pow(GameMenu.invisibleCircleRadius,2)) {
//                GameMenu.gameController.removeConnectedBall(ball);
//                GameMenu.gameController.removeAllAnimation(this);
//                this.stop();
//                return false;
//            }
//        }
        return true;
    }
}
