package view;

import controller.FilesController;
import controller.GameController;
import controller.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
import java.net.URL;


public class LoginMenu extends Application{



    public static Stage stage;
    public TextField username;
    public PasswordField password;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.stage = stage;
        AnchorPane loginPane = FXMLLoader.load(
                new URL(MainMenu.class.getResource("/fxml/loginMenu.fxml").toExternalForm()));

        Scene scene = new Scene(loginPane);
        stage.setScene(scene);
        stage.show();
    }
    public void openSignupMenu(MouseEvent mouseEvent) throws Exception {
        new SignupMenu().start(LoginMenu.stage);
    }

    public void checkLogin(MouseEvent mouseEvent) throws Exception {

        if(RegisterController.checkLogin(username.getText(), password.getText()).equals("ok")){
            MainMenu.gameController = new GameController(username.getText());
            MainMenu.username = username.getText();
            FilesController.setCurrentUser(FilesController.getUserByUsername(username.getText()));
            new MainMenu().start(LoginMenu.stage);
            MainMenu.username = username.getText();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login Error");
            alert.setContentText(RegisterController.checkLogin(username.getText(), password.getText()));
            alert.showAndWait();
        }
    }

    public void checkGuest(MouseEvent mouseEvent) throws Exception {
        MainMenu.gameController = new GameController("guest");
        MainMenu.username = "guest";
        FilesController.setCurrentUser(FilesController.getUserByUsername(username.getText()));
        new MainMenu().start(LoginMenu.stage);
    }

}
