package controller;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import model.Ball;
import view.GameMenu;
import view.RotationAnimation2;

import java.io.IOException;
import java.util.ArrayList;

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
        Circle invisibleCircle = new Circle(400, 300, GameMenu.invisibleCircleRadius);
        ArrayList<Double> map = mapList.get(mapNumber);

        gamePane.getChildren().removeAll(GameMenu.gameController.getConnectedBalls());
        gamePane.getChildren().removeAll(GameMenu.gameController.getConnectedBallsTexts());
        gamePane.getChildren().removeAll(GameMenu.gameController.getConnectedBallsLines());
        GameMenu.gameController.setConnectedBallsbyAngle(map);
        GameMenu.gameController.getAllAnimations().clear();
        gamePane.getChildren().addAll(GameMenu.gameController.getConnectedBalls());
        gamePane.getChildren().addAll(GameMenu.gameController.getConnectedBallsTexts());
        gamePane.getChildren().addAll(GameMenu.gameController.getConnectedBallsLines());
        for (Ball connectedBall : GameMenu.gameController.getConnectedBalls()) {
            connectedBall.setIsNumVisible(false);
            RotationAnimation2 rotationAnimation2 = new RotationAnimation2(gamePane, connectedBall, invisibleCircle, 1, connectedBall.getAngle());
            rotationAnimation2.play();
            GameMenu.gameController.getAllAnimations().add(rotationAnimation2);
        }
    }
    public static void loadGame(AnchorPane gamePane) throws IOException {
        Circle invisibleCircle = new Circle(400, 300, GameMenu.invisibleCircleRadius);
        GameMenu.iceModeCount = FilesController.getBallsIce();
        GameMenu.progressBar.setProgress(GameMenu.iceModeCount / SettingsController.getIceModeNeededBalls());
        ArrayList<Double> map = FilesController.getBalls();
        ArrayList<Integer> ballsnumbers = FilesController.getBallsNumbers();
        gamePane.getChildren().removeAll(GameMenu.gameController.getConnectedBalls());
        gamePane.getChildren().removeAll(GameMenu.gameController.getConnectedBallsTexts());
        gamePane.getChildren().removeAll(GameMenu.gameController.getConnectedBallsLines());
        GameMenu.gameController.setConnectedBallsbyAngle(map, ballsnumbers);
        GameMenu.gameController.getAllAnimations().clear();
        gamePane.getChildren().addAll(GameMenu.gameController.getConnectedBalls());
        gamePane.getChildren().addAll(GameMenu.gameController.getConnectedBallsTexts());
        gamePane.getChildren().addAll(GameMenu.gameController.getConnectedBallsLines());
        for (Ball connectedBall : GameMenu.gameController.getConnectedBalls()) {
            RotationAnimation2 rotationAnimation2 = new RotationAnimation2(gamePane, connectedBall, invisibleCircle, 1, connectedBall.getAngle());
            rotationAnimation2.play();
            GameMenu.gameController.getAllAnimations().add(rotationAnimation2);
        }
        GameMenu.numberOfBalls = SettingsController.getMaxNumberOfBalls() - GameMenu.gameController.getConnectedBalls().size();

    }
    public static void saveGame() throws IOException {
        FilesController.saveBalls(GameMenu.gameController.getConnectedBallsAngles());
        FilesController.saveBallsNumbers(GameMenu.gameController.getConnectedBallsMapNumbers());
        FilesController.saveBallsIce(GameMenu.iceModeCount);
    }
}