package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.*;
import view.GameMenu;
import view.RotationAnimation2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static view.GameMenu.timelineForPhase2;

public class NewGameController {
    private static ArrayList<ArrayList<Double>> mapList = new ArrayList<>();
    static {
        mapList = new ArrayList<>();
        ArrayList<Double> map0 = new ArrayList<>(){{
            add(0 * 2 * Math.PI/5);
            add(1 * 2 * Math.PI/5);
            add(2 * 2 * Math.PI/5);
            add(3 * 2 * Math.PI/5);
            add(4 * 2 *Math.PI/5);
        }};
        ArrayList<Double> map1 = new ArrayList<>(){{
            add(0.0);
            add(Math.PI / 2);
            add(4 * Math.PI / 3);
            add(5 * Math.PI / 3);
            add(Math.PI);
        }};
        ArrayList<Double> map2 = new ArrayList<>(){{
            add(0.0);
            add(Math.PI / 3);
            add(Math.PI);
            add(4 * Math.PI / 3);
            add(8 * Math.PI / 3);
        }};
        ArrayList<Double> map3 = new ArrayList<>(){{
            add(0.0);
            add(Math.PI / 3);
            add(2 * Math.PI/3);
            add(3 * Math.PI / 3);
            add(5 * Math.PI / 3);
            add(10 * Math.PI / 3);
        }};
        ArrayList<Double> map4 = new ArrayList<>(){{
            add(0.0);
            add(Math.PI / 2);
            add(Math.PI);
            add(5 * Math.PI / 3);
            add(3 * Math.PI / 2);
        }};
        mapList.add(map0);
        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);
        mapList.add(map4);
    }


    public static void newGame(AnchorPane gamePane, int mapNumber) {
        Circle invisibleCircle = GameMenu.invisibleCircle;
        ArrayList<Double> map = mapList.get(mapNumber);
        GameMenu.iceModeCount = 0;
        GameMenu.numberOfBalls = SettingsController.getMaxNumberOfBalls();
        GameMenu.windSpeed = 0;
        GameMenu.windSpeedRate = SettingsController.getWindSpeedRate();
        GameMenu.phase = 0;
        GameMenu.angleSpeedInput = SettingsController.getAngleSpeedInput();
        GameMenu.isBeingLoaded = false;


        GameMenu.numberOfBalls = SettingsController.getMaxNumberOfBalls();
        GameMenu.progressBar.setProgress(0);

        gamePane.getChildren().removeAll(GameMenu.gameController.getConnectedBalls());
        gamePane.getChildren().removeAll(GameMenu.gameController.getConnectedBallsTexts());
        gamePane.getChildren().removeAll(GameMenu.gameController.getConnectedBallsLines());
        GameMenu.gameController.setConnectedBallsbyAngle(map);
        for (Animation allAnimation : GameMenu.gameController.getAllAnimations()) {
            allAnimation.stop();
        }
        GameMenu.gameController.getAllAnimations().clear();
        gamePane.getChildren().addAll(GameMenu.gameController.getConnectedBalls());
        gamePane.getChildren().addAll(GameMenu.gameController.getConnectedBallsTexts());
        gamePane.getChildren().addAll(GameMenu.gameController.getConnectedBallsLines());
        for (Ball connectedBall : GameMenu.gameController.getConnectedBalls()) {
            connectedBall.setIsNumVisible(false);
            RotationAnimation2 rotationAnimation2 = new RotationAnimation2(gamePane, connectedBall, invisibleCircle, SettingsController.getAngleSpeedInput(), connectedBall.getAngle());
            rotationAnimation2.play();
            GameMenu.gameController.getAllAnimations().add(rotationAnimation2);
        }
    }
    public static void loadGame(AnchorPane gamePane) throws IOException {
        Circle invisibleCircle = new Circle(400, 300, GameMenu.invisibleCircleRadius);
        SaveOb saveOb = FilesController.loadOneGame(GameMenu.username);
        GameMenu.iceModeCount = saveOb.getBallsIce();
        GameMenu.numberOfBalls = saveOb.getNumberOfBalls();
        GameMenu.angleSpeedInput = saveOb.getAngleSpeedInput();

        int maxNumberOfBalls = SettingsController.getMaxNumberOfBalls();
        if (GameMenu.numberOfBalls <= maxNumberOfBalls) {

            GameMenu.phase = 1;

        }if(GameMenu.numberOfBalls <= maxNumberOfBalls*3/4) {

            GameMenu.phase = 2;
            Phase2.setIsPhase2Finished(false);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300),
                    actionEvent -> timelineForPhase2(gamePane)));
            timeline.setCycleCount(1);
            timeline.play();

        }if(GameMenu.numberOfBalls <= maxNumberOfBalls/2) {
            GameMenu.phase = 3;

            Phase3.setIsPhase3Finished(false);
            Phase3.timeHandlerChangeVisibility(gamePane);

        }if (GameMenu.numberOfBalls <= maxNumberOfBalls/4) {
            GameMenu.phase = 4;
            Phase4.setIsPhase4Finished(false);
            Phase4.windEventHandler(gamePane);
        }
        GameMenu.score = saveOb.getScore();
        GameMenu.time = saveOb.getTime();
        SettingsController.setLevel(saveOb.getLevel());

        GameMenu.progressBar.setProgress(GameMenu.iceModeCount / SettingsController.getIceModeNeededBalls());
        GameMenu.remainedBallsLabel.setText("Remained Balls: " + GameMenu.numberOfBalls);
        GameMenu.scoreLabel.setText("Score: " + GameMenu.score);
        GameMenu.windLabel.setText("Wind Speed: " + GameMenu.windSpeed);

        ArrayList<Double> map = saveOb.getBalls();
        ArrayList<Integer> ballsnumbers = saveOb.getBallsNumbers();

        saveOb.getSettingsControllerOb().setStatic();

        gamePane.getChildren().removeAll(GameMenu.gameController.getConnectedBalls());
        gamePane.getChildren().removeAll(GameMenu.gameController.getConnectedBallsTexts());
        gamePane.getChildren().removeAll(GameMenu.gameController.getConnectedBallsLines());
        GameMenu.gameController.setConnectedBallsbyAngle(map, ballsnumbers);
        GameMenu.gameController.getAllAnimations().clear();
        gamePane.getChildren().addAll(GameMenu.gameController.getConnectedBalls());
        gamePane.getChildren().addAll(GameMenu.gameController.getConnectedBallsTexts());
        gamePane.getChildren().addAll(GameMenu.gameController.getConnectedBallsLines());
        for (Ball connectedBall : GameMenu.gameController.getConnectedBalls()) {
            RotationAnimation2 rotationAnimation2 = new RotationAnimation2(gamePane, connectedBall, invisibleCircle, GameMenu.angleSpeedInput, connectedBall.getAngle());
            rotationAnimation2.play();
            GameMenu.gameController.getAllAnimations().add(rotationAnimation2);
        }


    }
    public static void saveGame() throws IOException {
        SaveOb saveOb = new SaveOb();
        saveOb.setUsername(GameMenu.username);
        ArrayList<Double> ballsAngle = new ArrayList<>();
        for (Ball connectedBall : GameMenu.gameController.getConnectedBalls()) {
            ballsAngle.add(connectedBall.getAngle());
        }
        saveOb.setBalls(ballsAngle);

        ArrayList<Integer> ballsNumbers = new ArrayList<>();
        for (Ball connectedBall : GameMenu.gameController.getConnectedBalls()) {
            ballsNumbers.add(connectedBall.getNumber());
        }
        saveOb.setBallsNumbers(ballsNumbers);

        saveOb.setNumberOfBalls(GameMenu.numberOfBalls);
        saveOb.setBallsIce(GameMenu.iceModeCount);
        saveOb.setScore(GameMenu.score);
        saveOb.setTime(GameMenu.time);
        saveOb.setAngleSpeedInput(GameMenu.angleSpeedInput);
        saveOb.setLevel(SettingsController.getLevel());
        SettingsControllerOb settingsControllerOb = new SettingsControllerOb();
        settingsControllerOb.setOb();
        saveOb.setSettingsControllerOb(settingsControllerOb);
        FilesController.saveOneGame(saveOb);
    }
}