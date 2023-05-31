package view;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Level;

import java.io.IOException;
import java.net.URL;

public class SettingsMenu extends Application {
    public static Stage stage;
    @FXML
    public Label numberOfBalls;
    @FXML
    public Label mapNumber;
    @FXML
    public TextField ShootButton;
    @FXML
    public TextField IceButton;
    @FXML
    public Label muteLabel;
    @FXML
    public Label BWLabel;
    @FXML
    public Label langLabel;


    @Override
    public void start(Stage stage) throws Exception {
        loadSettings();
        SettingsMenu.stage = stage;
        AnchorPane settings = FXMLLoader.load(
                new URL(MainMenu.class.getResource("/fxml/settingsMenu.fxml").toExternalForm()));



        Scene scene = new Scene(settings);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void initialize() {
        numberOfBalls.setText(SettingsController.getMaxNumberOfBalls()+"");
        mapNumber.setText(SettingsController.getMapNumber()+"");
        ShootButton.setText(SettingsController.getShootButton());
        IceButton.setText(SettingsController.getIceButton());
        muteLabel.setText(SettingsController.isMuted()?"ON":"OFF");
        BWLabel.setText(SettingsController.isBW()?"ON":"OFF");
        langLabel.setText(SettingsController.isPersian()?"Persian":"English");

        ShootButton.textProperty().addListener((observable, oldText, newText)->{
            SettingsController.setShootButton(newText);
        });
        IceButton.textProperty().addListener((observable, oldText, newText)->{
                SettingsController.setIceButton(newText);
          });
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        loadSettings();
        new MainMenu().start(stage);
    }


    public void checkEasy(MouseEvent mouseEvent) {
        SettingsController.setLevel(Level.EASY);
        SettingsController.setAngleSpeedInput(0.7);
        SettingsController.setWindSpeedRate(1);
        SettingsController.setIceModeTimer(7);
    }

    public void checkMedium(MouseEvent mouseEvent) {
        SettingsController.setLevel(Level.MEDIUM);
        SettingsController.setAngleSpeedInput(1);
        SettingsController.setWindSpeedRate(3);
        SettingsController.setIceModeTimer(5);
    }

    public void checkHard(MouseEvent mouseEvent) {
        SettingsController.setLevel(Level.HARD);
        SettingsController.setAngleSpeedInput(1.5);
        SettingsController.setWindSpeedRate(5);
        SettingsController.setIceModeTimer(3);
    }

    public void checkDecreaseBalls(MouseEvent mouseEvent) {
        if(SettingsController.getMaxNumberOfBalls()==0) return;
        SettingsController.setMaxNumberOfBalls(SettingsController.getMaxNumberOfBalls()-1);
        numberOfBalls.setText(SettingsController.getMaxNumberOfBalls()+"");
    }

    public void checkIncreaseBalls(MouseEvent mouseEvent) {
        if(SettingsController.getMaxNumberOfBalls()==80) return;
        SettingsController.setMaxNumberOfBalls(SettingsController.getMaxNumberOfBalls()+1);
        numberOfBalls.setText(SettingsController.getMaxNumberOfBalls()+"");
    }

    public void checkDecreaseMapNumber(MouseEvent mouseEvent) {
        if(SettingsController.getMapNumber()==0) return;
        SettingsController.setMapNumber(SettingsController.getMapNumber()-1);
        mapNumber.setText(SettingsController.getMapNumber()+"");
    }

    public void checkIncreaseMapNumber(MouseEvent mouseEvent) {
        if(SettingsController.getMapNumber()==4) return;
        SettingsController.setMapNumber(SettingsController.getMapNumber()+1);
        mapNumber.setText(SettingsController.getMapNumber()+"");
    }

    public void checkChangeMuteGame(MouseEvent mouseEvent) {
        SettingsController.setMuted(!SettingsController.isMuted());
        muteLabel.setText(SettingsController.isMuted()?"ON":"OFF");
    }

    public void checkChangeBW(MouseEvent mouseEvent) {
        SettingsController.setBW(!SettingsController.isBW());
        BWLabel.setText(SettingsController.isBW()?"ON":"OFF");
    }

    public void checkLang(MouseEvent mouseEvent) {
        SettingsController.setPersian(!SettingsController.isPersian());
        langLabel.setText(SettingsController.isPersian()?"Persian":"English");
    }

    public void saveSettings(MouseEvent mouseEvent) throws IOException {
        SettingsController settingController = new SettingsController();
        SettingsControllerOb settingsControllerOb = new SettingsControllerOb();
        settingsControllerOb.setOb();
        FilesController.saveSettingControllerOb(settingsControllerOb);
    }
    public void loadSettings() throws IOException {
        FilesController.loadSettingsControllerOb();
    }
}
