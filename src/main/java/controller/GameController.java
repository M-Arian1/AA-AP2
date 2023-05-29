package controller;

import javafx.animation.Animation;
import model.Ball;

import java.io.IOException;
import java.util.ArrayList;

public class GameController {

    private String username;
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

}
