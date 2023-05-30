package view;

import controller.GameController;
import controller.NewGameController;
import controller.SettingsController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Ball;
import model.Phase2;
import model.Phase3;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GameMenu extends Application {
    public static GameController gameController ;
    public static ProgressBar progressBar;
    public static final int mainCircleRadius = 40;
    public static final int invisibleCircleRadius = 150;

    public static final int ballRadius = SettingsController.getBallRadius();
    public static Stage stage;
    public static String username;
    public static int numberOfBalls = SettingsController.getMaxNumberOfBalls();
    public static int iceModeCount = 0;
    public int phase = 0;
    public static double angleSpeedInput = 1;
    public static Circle mainCircle = new Circle(400, 300, mainCircleRadius);

    public static Circle invisibleCircle = new Circle(400, 300, invisibleCircleRadius);

    @Override
    public void start(Stage stage) throws Exception {
        gameController = new GameController(username);
        GameMenu.stage = stage;
        AnchorPane gamePane = FXMLLoader.load(
                new URL(MainMenu.class.getResource("/fxml/gameMenu.fxml").toExternalForm()));
        progressBar = new ProgressBar(0);
        progressBar.setLayoutX(100);
        progressBar.setLayoutY(100);
        NewGameController.newGame(gamePane, SettingsController.getMapNumber());
//        Circle mainCircle = new Circle(400, 300, mainCircleRadius);
        mainCircle = new Circle(400, 300, mainCircleRadius);
        gamePane.getChildren().add(mainCircle);
        gamePane.getChildren().add(progressBar);
//        Circle invisibleCircle = new Circle(400, 300, invisibleCircleRadius);
        invisibleCircle = new Circle(400, 300, invisibleCircleRadius);
        invisibleCircle.setVisible(false);
        gamePane.getChildren().add(invisibleCircle);

        Ball shootBall = createBall(gamePane);


        Scene scene = new Scene(gamePane);
        stage.setScene(scene);
        shootBall.requestFocus();
        stage.show();
    }

    private Ball createBall(AnchorPane gamePane) {
        Ball ball = new Ball(ballRadius, 400, 500, numberOfBalls);

        gamePane.getChildren().addAll(ball, ball.getBallText());

        ball.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                if (keyEvent.getCode().equals(KeyCode.SPACE)) {
                    ball.nextBall();
                    shootBall(gamePane, ball.getCenterX(), ball.getCenterY(), ballRadius);
                    numberOfBalls--;
                    checkPhase(gamePane);
                    iceModeCount++;
                    progressBar.setProgress(iceModeCount/SettingsController.getIceModeNeededBalls());
                } else if (keyName.equals("Tab")) {
                    iceModeTimeline(gamePane);

                } else if (keyName.equals("Left")){


                }else if (keyName.equals("Right")) {

                }
            }
        });
        return ball;
    }

    private void checkPhase(AnchorPane gamePane) {
        int maxNumberOfBalls = SettingsController.getMaxNumberOfBalls();
        if (numberOfBalls <= maxNumberOfBalls && numberOfBalls > maxNumberOfBalls*3/4 && phase!=1) {
            phase = 1;

        }else if(numberOfBalls <= maxNumberOfBalls*3/4 && numberOfBalls > maxNumberOfBalls/2 && phase!=2) {

            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300),
                    actionEvent -> timelineForPhase2(gamePane)));
            timeline.setCycleCount(1);
            timeline.play();

        }else if(numberOfBalls <= maxNumberOfBalls/2 && numberOfBalls > maxNumberOfBalls/4 && phase!=3) {
            Phase3.timeHandlerChangeVisibility(gamePane);

        }else if (numberOfBalls <= maxNumberOfBalls/4 && numberOfBalls > 0 && phase!=4) {

        }else if(numberOfBalls == 0) {
            //TODO
        }
    }

    private void timelineForPhase2(AnchorPane gamePane) {
        changeRotateSpeed(gamePane);
        Phase2.timeLineHandlerChangeRotateSpeed(gamePane);
        Phase2.timeHandlerChangeRadius(gamePane);
    }

    private void changeRotateSpeed(AnchorPane gamePane) {
        for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
            if (allAnimation instanceof RotationAnimation2) {
                ((RotationAnimation2) allAnimation).setAngleSpeed(-((RotationAnimation2) allAnimation).getAngleSpeed());
            }
        }
        angleSpeedInput = -angleSpeedInput;
    }



    private void iceModeTimeline(AnchorPane gamePane) {
        if(iceModeCount < 5) return;
        iceModeCount = 0;
        progressBar.setProgress(0);
        iceMode(gamePane);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3000),
                actionEvent -> revertIceMode(gamePane)));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void iceMode(AnchorPane gamePane) {
        for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
            if (allAnimation instanceof RotationAnimation2) {
                ((RotationAnimation2) allAnimation).setAngleSpeed(((RotationAnimation2) allAnimation).getAngleSpeed()/2);
            }
        }
        angleSpeedInput = angleSpeedInput/2;
    }
    private void revertIceMode(AnchorPane gamePane) {
        for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
            if (allAnimation instanceof RotationAnimation2) {
                ((RotationAnimation2) allAnimation).setAngleSpeed(((RotationAnimation2) allAnimation).getAngleSpeed()*2);
            }
        }
        angleSpeedInput = angleSpeedInput*2;
    }

    private void shootBall(AnchorPane gamePane, double X, double Y, int radius) {
        Ball shootedball = new Ball(radius, X, Y, numberOfBalls);
        gamePane.getChildren().addAll(shootedball, shootedball.getBallText());
        ShootAnimation shootAnimation = new ShootAnimation(gamePane, shootedball);
        gameController.addAllAnimation(shootAnimation);
        shootAnimation.play();
    }

    public static boolean checkCollision(AnchorPane gamePane) {
        Boolean isLost = false;
        ArrayList<Ball> balls = GameMenu.gameController.getConnectedBalls();
        for(Ball ball1 : balls) {
            for(Ball ball2 : balls){
                if(!ball1.equals(ball2)) {
                    if (Math.pow(ball1.getCenterX() - ball2.getCenterX(), 2) + Math.pow(ball1.getCenterY() - ball2.getCenterY(), 2) < Math.pow(2*ball1.getRadius(), 2)) {
                        isLost = true;
                        break;
                    }
                }
            }
        }
        return isLost;

    }


}
