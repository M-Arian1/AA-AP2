package controller;

public class SettingsController {
    private static int mapNumber = 0;
    private static int maxNumberOfBalls = 10;
    private static int ballRadius = 5;
    private static double iceModeNeededBalls = 5.0;
    private static double angleSpeedInput = 1.0;
    private static int iceModeTimer = 5;
    private static double windSpeedRate = 3;
    private static double maxWindSpeed = 15;
    private static boolean isMuted = false;
    private static boolean isBW = false;
    private static boolean isPersian = false;
    private static String shootButton = "Space";
    private static String iceButton = "Tab";

    private static int time = 67;

    public static int getMapNumber() {
        return mapNumber;
    }

    public static void setMapNumber(int mapNumber) {
        SettingsController.mapNumber = mapNumber;
    }

    public static int getBallRadius() {
        return ballRadius;
    }

    public static void setBallRadius(int ballRadius) {
        SettingsController.ballRadius = ballRadius;
    }

    public static int getMaxNumberOfBalls() {
        return maxNumberOfBalls;
    }

    public static void setMaxNumberOfBalls(int maxNumberOfBalls) {
        SettingsController.maxNumberOfBalls = maxNumberOfBalls;
    }

    public static double getIceModeNeededBalls() {
        return iceModeNeededBalls;
    }

    public static void setIceModeNeededBalls(double iceModeNeededBalls) {
        SettingsController.iceModeNeededBalls = iceModeNeededBalls;
    }

    public static double getAngleSpeedInput() {
        return angleSpeedInput;
    }

    public static void setAngleSpeedInput(double angleSpeedInput) {
        SettingsController.angleSpeedInput = angleSpeedInput;
    }

    public static int getIceModeTimer() {
        return iceModeTimer;
    }

    public static void setIceModeTimer(int iceModeTimer) {
        SettingsController.iceModeTimer = iceModeTimer;
    }

    public static double getWindSpeedRate() {
        return windSpeedRate;
    }

    public static void setWindSpeedRate(double windSpeedRate) {
        SettingsController.windSpeedRate = windSpeedRate;
    }

    public static double getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public static void setMaxWindSpeed(double maxWindSpeed) {
        SettingsController.maxWindSpeed = maxWindSpeed;
    }

    public static boolean isMuted() {
        return isMuted;
    }

    public static void setMuted(boolean muted) {
        isMuted = muted;
    }

    public static boolean isBW() {
        return isBW;
    }

    public static void setBW(boolean isBW) {
        SettingsController.isBW = isBW;
    }

    public static boolean isPersian() {
        return isPersian;
    }

    public static void setPersian(boolean isPersian) {
        SettingsController.isPersian = isPersian;
    }

    public static String getShootButton() {
        return shootButton;
    }

    public static void setShootButton(String shootButton) {
        SettingsController.shootButton = shootButton;
    }

    public static String getIceButton() {
        return iceButton;
    }

    public static void setIceButton(String iceButton) {
        SettingsController.iceButton = iceButton;
    }

    public static void setInstance(SettingsController settingController) {
        SettingsController.mapNumber = settingController.mapNumber;
        SettingsController.maxNumberOfBalls = settingController.maxNumberOfBalls;
        SettingsController.ballRadius = settingController.ballRadius;
        SettingsController.iceModeNeededBalls = settingController.iceModeNeededBalls;
        SettingsController.angleSpeedInput = settingController.angleSpeedInput;
        SettingsController.iceModeTimer = settingController.iceModeTimer;
        SettingsController.windSpeedRate = settingController.windSpeedRate;
        SettingsController.maxWindSpeed = settingController.maxWindSpeed;
        SettingsController.isMuted = settingController.isMuted;
        SettingsController.isBW = settingController.isBW;
        SettingsController.isPersian = settingController.isPersian;
        SettingsController.shootButton = settingController.shootButton;
        SettingsController.iceButton = settingController.iceButton;
    }

    public static int getTime() {
        return time;
    }

    public static void setTime(int time) {
        SettingsController.time = time;
    }
}
