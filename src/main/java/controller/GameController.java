package controller;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Ball;
import model.DataBase;
import model.Stick;
import view.GameMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class GameController {

    private String username;
    private AnchorPane gamePane;
    private boolean isLost = false;
    private ArrayList<Ball> connectedBalls = new ArrayList<>();
    private ArrayList<ArrayList<Ball>> defaultConnectedBallsList = new ArrayList<>();
    private ArrayList<Animation> allAnimations = new ArrayList<>();
    public GameController(String username) {
        this.username = username;
        this.connectedBalls = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }


    public ArrayList<Ball> getConnectedBalls() {
        return connectedBalls;
    }
    public ArrayList<Text> getConnectedBallsTexts() {
        ArrayList<Text> texts = new ArrayList<>();
        for (Ball ball : connectedBalls) {
            texts.add(ball.getBallText());
        }
        return texts;
    }
    public ArrayList<Stick> getConnectedBallsLines() {
        ArrayList<Stick> sticks = new ArrayList<>();
        for (Ball ball : connectedBalls) {
            sticks.add(ball.getBallStick());
        }
        return sticks;
    }
    public void setConnectedBallsbyAngle(ArrayList<Double> angles) {
        ArrayList<Ball> balls = new ArrayList<>();
        for (Double angle : angles) {
            Ball ball = new Ball(SettingsController.getBallRadius(),400+Math.cos(angle)*150,300+Math.sin(angle)*150, 0);
            balls.add(ball);
        }
        this.connectedBalls = balls;
    }
    public void setConnectedBallsbyAngle(ArrayList<Double> angles, ArrayList<Integer> mapNumbers) {
        ArrayList<Ball> balls = new ArrayList<>();
        for (Double angle : angles) {
            Ball ball = new Ball(SettingsController.getBallRadius(),400+Math.cos(angle)*150,300+Math.sin(angle)*150, mapNumbers.get(angles.indexOf(angle)));
            if(ball.getBallText().getText().equals("0"))
                ball.getBallText().setVisible(false);
            balls.add(ball);

        }
        this.connectedBalls = balls;
    }
    public ArrayList<Double> getConnectedBallsAngles() {
        ArrayList<Double> angles = new ArrayList<>();
        for (Ball ball : connectedBalls) {
            angles.add(ball.getAngle());
        }
        return angles;
    }
    public ArrayList<Integer> getConnectedBallsMapNumbers() {
        ArrayList<Integer> mapNumbers = new ArrayList<>();
        for (Ball ball : connectedBalls) {
            mapNumbers.add(ball.getNumber());
        }
        return mapNumbers;
    }
    public void setConnectedBalls(ArrayList<Ball> balls) {
        this.connectedBalls = balls;
    }
    public void setDefaultConnectedBalls(int mapNumber) throws IOException {

    }
    public void addSetOfDefaultConnectedBalls(ArrayList<Ball> balls) {
        defaultConnectedBallsList.add(balls);
    }
    public void addConnectedBall(Ball ball) {

    }
    public void removeConnectedBall(Ball ball){
        this.connectedBalls.remove(ball);
    }
    public ArrayList<Animation> getAllAnimations() {
        return allAnimations;
    }

    public void setAllAnimations(ArrayList<Animation> allAnimations) {
        this.allAnimations = allAnimations;
    }
    public void addAllAnimation(Animation animation) {
        this.allAnimations.add(animation);
    }
    public void removeAllAnimation(Animation animation){
        this.allAnimations.remove(animation);
    }

    public AnchorPane getGamePane() {
        return gamePane;
    }

    public void setGamePane(AnchorPane gamePane) {
        this.gamePane = gamePane;
    }

    public boolean isLost() {
        return isLost;
    }

    public void setLost(boolean lost) {
        isLost = lost;
    }


//    public ArrayList<Timeline> getAllTimeLines() {
//        return allTimeLines;
//    }
//
//    public void setAllTimeLines(ArrayList<Timeline> allTimeLines) {
//        this.allTimeLines = allTimeLines;
//    }
}
