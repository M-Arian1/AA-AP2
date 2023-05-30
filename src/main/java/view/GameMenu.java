package view;

import controller.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GameMenu extends Application {
    public static GameController gameController ;
    public static ProgressBar progressBar;

    public static Label label;
    @FXML
    public static AnchorPane pausePane;
    public static final int mainCircleRadius = 40;
    public static final int invisibleCircleRadius = 150;

    public static final int ballRadius = SettingsController.getBallRadius();
    public static Stage stage;
    public static String username;
    public static int numberOfBalls = SettingsController.getMaxNumberOfBalls();
    public static int windSpeed = 0;
    public static double windSpeedRate = SettingsController.getWindSpeedRate();
    public static int iceModeCount = 0;
    public static int phase = 0;
    public static double angleSpeedInput = SettingsController.getAngleSpeedInput();
    public static Circle mainCircle = new Circle(400, 300, mainCircleRadius);

    public static Circle invisibleCircle = new Circle(400, 300, invisibleCircleRadius);
    public static Ball shootBall;
    public static boolean isBeingLoaded = false;

    @Override
    public void start(Stage stage) throws Exception {
        gameController = new GameController(username);
        GameMenu.stage = stage;
        AnchorPane gamePane = FXMLLoader.load(
                new URL(MainMenu.class.getResource("/fxml/gameMenu.fxml").toExternalForm()));
        pausePane = (AnchorPane)gamePane.getChildren().get(1);
        pausePane.setVisible(false);
        gameController.setGamePane(gamePane);
        progressBar = new ProgressBar(0);
        progressBar.setLayoutX(100);
        progressBar.setLayoutY(100);
        label = new Label();
        label.setLayoutX(100);
        label.setLayoutY(200);
        label.setText("WindSpeed: "+windSpeed);
        NewGameController.newGame(gamePane, SettingsController.getMapNumber());
        if(isBeingLoaded){
            NewGameController.loadGame(gamePane);
        }
        mainCircle = new Circle(400, 300, mainCircleRadius);
        gamePane.getChildren().add(mainCircle);
        gamePane.getChildren().add(progressBar);
        gamePane.getChildren().add(label);
        invisibleCircle = new Circle(400, 300, invisibleCircleRadius);
        invisibleCircle.setVisible(false);
        gamePane.getChildren().add(invisibleCircle);

        shootBall = createBall(gamePane);
        focusTimeLime(gamePane);

        Scene scene = new Scene(gamePane);
        stage.setScene(scene);
        shootBall.requestFocus();
        stage.show();
    }
    @FXML
    public void initialize() {
//        pausePane = (AnchorPane)gamePane.getChildren().get(1);
//        pausePane.setVisible(false);
    }
    private Ball createBall(AnchorPane gamePane) {
        Ball ball = new Ball(ballRadius, 400, 500, numberOfBalls);
        ball.getBallStick().setVisible(false);
        gamePane.getChildren().addAll(ball, ball.getBallText(), ball.getBallStick());

        ball.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                if (keyName.equals(SettingsController.getShootButton())) {
                    ball.nextBall();
                    shootBall(gamePane, ball.getCenterX(), ball.getCenterY(), ballRadius);
                    numberOfBalls--;
                    checkPhase(gamePane);
                    iceModeCount++;
                    progressBar.setProgress(iceModeCount/SettingsController.getIceModeNeededBalls());
                } else if (keyName.equals(SettingsController.getIceButton())) {
                    iceModeTimeline(gamePane);

                } else if (keyName.equals("Left")){
                    if(phase == 4 && ball.getCenterX() > 161){
                        ball.setCenterX(ball.getCenterX() - 10);
                        ball.getBallText().setX(ball.getBallText().getX() - 10);
                    }

                }else if (keyName.equals("Right")) {
                    if(phase == 4 && ball.getCenterX() < 639){
                        ball.setCenterX(ball.getCenterX() + 10);
                        ball.getBallText().setX(ball.getBallText().getX() + 10);
                    }
                }else if(keyName.equals("S")) {
                    try {
                        NewGameController.saveGame();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
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
            phase = 2;
            Phase2.setIsPhase2Finished(false);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300),
                    actionEvent -> timelineForPhase2(gamePane)));
            timeline.setCycleCount(1);
            timeline.play();

        }else if(numberOfBalls <= maxNumberOfBalls/2 && numberOfBalls > maxNumberOfBalls/4 && phase!=3) {
            phase = 3;
            Phase3.setIsPhase3Finished(false);
            Phase3.timeHandlerChangeVisibility(gamePane);

        }else if (numberOfBalls <= maxNumberOfBalls/4 && numberOfBalls > 0 && phase!=4) {
            phase = 4;
            Phase4.setIsPhase4Finished(false);
            Phase4.windEventHandler(gamePane);

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


    private void focusTimeLime(AnchorPane gamePane) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),
                actionEvent -> shootBall.requestFocus()));
        timeline.setCycleCount(-1);
        timeline.play();
    }
    private void iceModeTimeline(AnchorPane gamePane) {
        if(iceModeCount < 5) return;
        iceModeCount = 0;
        progressBar.setProgress(0);
        iceMode(gamePane);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000*SettingsController.getIceModeTimer()),
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
        gamePane.getChildren().addAll(shootedball, shootedball.getBallText(), shootedball.getBallStick());
        shootedball.getBallStick().setVisible(false);
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


    public void checkPause(MouseEvent mouseEvent) {
        pausePane.setVisible(!pausePane.isVisible());
        for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
            if (pausePane.isVisible()) {
                allAnimation.pause();
            } else {
                allAnimation.play();
            }
        }
        for (Ball ball : GameMenu.gameController.getConnectedBalls()) {
            if(pausePane.isVisible()) {
                ball.isPaused = true;
                ball.setVisible(false);
                ball.getBallStick().setVisible(false);
                ball.getBallText().setVisible(false);
            }
            else {
                ball.isPaused = false;
                ball.setVisible(true);
                ball.getBallStick().setVisible(true);
                ball.getBallText().setVisible(true);
            }
        }
        if (pausePane.isVisible()){
            shootBall.setVisible(false);
            label.setVisible(false);
            progressBar.setVisible(false);
            mainCircle.setVisible(false);
            pausePane.requestFocus();
        } else {
            shootBall.setVisible(true);
            label.setVisible(true);
            progressBar.setVisible(true);
            mainCircle.setVisible(true);
            shootBall.requestFocus();
        }


    }

    public void back(MouseEvent mouseEvent) throws Exception {

        for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
            allAnimation.stop();
        }
        GameMenu.gameController.getAllAnimations().clear();
        Phase2.setIsPhase2Finished(true);
        Phase3.setIsPhase3Finished(true);
        Phase4.setIsPhase4Finished(true);
        GameMenu.gameController.getConnectedBalls().clear();

        new MainMenu().start(stage);

    }

    public void checkSave(MouseEvent mouseEvent) throws IOException {
        NewGameController.saveGame();
    }

    public void checkRestart(MouseEvent mouseEvent) {
        checkPause(mouseEvent);
        NewGameController.newGame(gameController.getGamePane(),SettingsController.getMapNumber());

    }

    public void checkMuteMusic(MouseEvent mouseEvent) {
    }

    public void checkGuide(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guide");
        alert.setHeaderText("Controller Guide");
        alert.setContentText("Shoot Ball with:  " + SettingsController.getShootButton() + "\nIceMode with:     " + SettingsController.getIceButton());
        alert.showAndWait();
    }

    public void checkNextMusic(MouseEvent mouseEvent) {
    }
}
