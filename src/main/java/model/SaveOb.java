package model;

import controller.SettingsControllerOb;

import java.util.ArrayList;

public class SaveOb {
    private String username;
    private ArrayList<Double> balls;
    private ArrayList<Integer> ballsNumbers;
    private Integer ballsIce;
    private Integer numberOfBalls;
    private Double angleSpeedInput;
    private Integer score;
    private Integer time;
    private Level level;

    private SettingsControllerOb settingsControllerOb;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Double> getBalls() {
        return balls;
    }

    public void setBalls(ArrayList<Double> balls) {
        this.balls = balls;
    }

    public ArrayList<Integer> getBallsNumbers() {
        return ballsNumbers;
    }

    public void setBallsNumbers(ArrayList<Integer> ballsNumbers) {
        this.ballsNumbers = ballsNumbers;
    }

    public Integer getBallsIce() {
        return ballsIce;
    }

    public void setBallsIce(Integer ballsIce) {
        this.ballsIce = ballsIce;
    }

    public Integer getNumberOfBalls() {
        return numberOfBalls;
    }

    public void setNumberOfBalls(Integer numberOfBalls) {
        this.numberOfBalls = numberOfBalls;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Double getAngleSpeedInput() {
        return angleSpeedInput;
    }

    public void setAngleSpeedInput(Double angleSpeedInput) {
        this.angleSpeedInput = angleSpeedInput;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public SettingsControllerOb getSettingsControllerOb() {
        return settingsControllerOb;
    }

    public void setSettingsControllerOb(SettingsControllerOb settingsControllerOb) {
        this.settingsControllerOb = settingsControllerOb;
    }
}
