package model;

import controller.SettingsController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import view.GameMenu;
import view.RotationAnimation2;

public class Phase3 {


    public static void timeHandlerChangeVisibility(AnchorPane gamePane) {
        for (Ball connectedBall : GameMenu.gameController.getConnectedBalls()) {
            connectedBall.setVisible(true);
            connectedBall.getBallText().setVisible(true);
            connectedBall.getBallStick().setVisible(true);
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000),
                actionEvent -> Phase3.changeVisibility(gamePane)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    private static void changeVisibility(AnchorPane gamePane) {
        for (Ball connectedBall : GameMenu.gameController.getConnectedBalls()) {
            connectedBall.setVisible(false);
            connectedBall.getBallText().setVisible(false);
            connectedBall.getBallStick().setVisible(false);
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000),
                actionEvent -> Phase3.timeHandlerChangeVisibility(gamePane)));
        timeline.setCycleCount(1);
        timeline.play();
    }


}
