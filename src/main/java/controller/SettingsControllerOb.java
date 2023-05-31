package controller;

public class SettingsControllerOb {
    private int mapNumber = 0;
    private int maxNumberOfBalls = 10;
    private int ballRadius = 5;
    private double iceModeNeededBalls = 5.0;
    private double angleSpeedInput = 1.0;
    private int iceModeTimer = 5;
    private double windSpeedRate = 3;
    private double maxWindSpeed = 15;
    private boolean isMuted = false;
    private boolean isBW = false;
    private boolean isPersian = false;
    private String shootButton = "Space";
    private String iceButton = "Tab";


    public int getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }

    public int getMaxNumberOfBalls() {
        return maxNumberOfBalls;
    }

    public void setMaxNumberOfBalls(int maxNumberOfBalls) {
        this.maxNumberOfBalls = maxNumberOfBalls;
    }

    public int getBallRadius() {
        return ballRadius;
    }

    public void setBallRadius(int ballRadius) {
        this.ballRadius = ballRadius;
    }

    public double getIceModeNeededBalls() {
        return iceModeNeededBalls;
    }

    public void setIceModeNeededBalls(double iceModeNeededBalls) {
        this.iceModeNeededBalls = iceModeNeededBalls;
    }

    public double getAngleSpeedInput() {
        return angleSpeedInput;
    }

    public void setAngleSpeedInput(double angleSpeedInput) {
        this.angleSpeedInput = angleSpeedInput;
    }

    public int getIceModeTimer() {
        return iceModeTimer;
    }

    public void setIceModeTimer(int iceModeTimer) {
        this.iceModeTimer = iceModeTimer;
    }

    public double getWindSpeedRate() {
        return windSpeedRate;
    }

    public void setWindSpeedRate(double windSpeedRate) {
        this.windSpeedRate = windSpeedRate;
    }

    public double getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public void setMaxWindSpeed(double maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
    }

    public boolean isBW() {
        return isBW;
    }

    public void setBW(boolean BW) {
        isBW = BW;
    }

    public boolean isPersian() {
        return isPersian;
    }

    public void setPersian(boolean persian) {
        isPersian = persian;
    }

    public String getShootButton() {
        return shootButton;
    }

    public void setShootButton(String shootButton) {
        this.shootButton = shootButton;
    }

    public String getIceButton() {
        return iceButton;
    }

    public void setIceButton(String iceButton) {
        this.iceButton = iceButton;
    }

    public void setOb(){
        setMapNumber(SettingsController.getMapNumber());
        setMaxNumberOfBalls(SettingsController.getMaxNumberOfBalls());
        setBallRadius(SettingsController.getBallRadius());
        setIceModeNeededBalls(SettingsController.getIceModeNeededBalls());
        setAngleSpeedInput(SettingsController.getAngleSpeedInput());
        setIceModeTimer(SettingsController.getIceModeTimer());
        setWindSpeedRate(SettingsController.getWindSpeedRate());
        setMaxWindSpeed(SettingsController.getMaxWindSpeed());
        setMuted(SettingsController.isMuted());
        setBW(SettingsController.isBW());
        setPersian(SettingsController.isPersian());
        setShootButton(SettingsController.getShootButton());
        setIceButton(SettingsController.getIceButton());
    }
    public void setStatic(){
        SettingsController.setMapNumber(getMapNumber());
        SettingsController.setMaxNumberOfBalls(getMaxNumberOfBalls());
        SettingsController.setBallRadius(getBallRadius());
        SettingsController.setIceModeNeededBalls(getIceModeNeededBalls());
        SettingsController.setAngleSpeedInput(getAngleSpeedInput());
        SettingsController.setIceModeTimer(getIceModeTimer());
        SettingsController.setWindSpeedRate(getWindSpeedRate());
        SettingsController.setMaxWindSpeed(getMaxWindSpeed());
        SettingsController.setMuted(isMuted());
        SettingsController.setBW(isBW());
        SettingsController.setPersian(isPersian());
        SettingsController.setShootButton(getShootButton());
        SettingsController.setIceButton(getIceButton());
    }
}
