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


public class Runn extends Application{

    public static Stage stage;

    public static void main(String[] args) throws Exception {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        //TODO : filechoose panel
        Runn.stage = stage;
        AnchorPane runnpane = FXMLLoader.load(
                new URL(MainMenu.class.getResource("/fxml/loginMenu.fxml").toExternalForm()));
        Scene scene = new Scene(runnpane);
        stage.setScene(scene);
        stage.show();
        if(FilesController.getCurrentUser()!=null){
            MainMenu.gameController = new GameController(FilesController.getCurrentUser());
            MainMenu.username = FilesController.getCurrentUser();
            new MainMenu().start(Runn.stage);
        }else{
            new LoginMenu().start(Runn.stage);
        }
    }
}
