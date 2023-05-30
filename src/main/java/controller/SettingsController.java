package controller;

public class SettingsController {
    private static int mapNumber = 0;
    private static int maxNumberOfBalls = 10;
    private static int ballRadius = 5;
    private static double iceModeNeededBalls = 5.0;
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
}
