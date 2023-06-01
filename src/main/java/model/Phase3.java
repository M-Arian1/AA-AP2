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
    private static boolean isPhase3Finished = false;

    public static void timeHandlerChangeVisibility(AnchorPane gamePane) {
        if (isPhase3Finished) return;

        for (Ball connectedBall : GameMenu.gameController.getConnectedBalls()) {
            if(!connectedBall.isPaused) {
                connectedBall.setVisible(true);
                if(!connectedBall.getBallText().getText().equals("0")) connectedBall.getBallText().setVisible(true);
                connectedBall.getBallStick().setVisible(true);
            }
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000),
                actionEvent -> Phase3.changeVisibility(gamePane)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    private static void changeVisibility(AnchorPane gamePane) {
        if (isPhase3Finished) return;

        for (Ball connectedBall : GameMenu.gameController.getConnectedBalls()) {
            if(!connectedBall.isPaused) {
                connectedBall.setVisible(false);
                if(!connectedBall.getBallText().getText().equals("0")) connectedBall.getBallText().setVisible(false);
                connectedBall.getBallStick().setVisible(false);
            }
        }


        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000),
                actionEvent -> Phase3.timeHandlerChangeVisibility(gamePane)));
        timeline.setCycleCount(1);
        timeline.play();
    }


    public static boolean isIsPhase3Finished() {
        return isPhase3Finished;
    }

    public static void setIsPhase3Finished(boolean isPhase3Finished) {
        Phase3.isPhase3Finished = isPhase3Finished;
    }
}
