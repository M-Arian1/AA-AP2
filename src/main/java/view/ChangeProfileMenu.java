package view;

import controller.AvatarFilesController;
import controller.FilesController;
import controller.GameController;
import controller.RegisterController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Avatar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ChangeProfileMenu extends Application {
    public static Stage stage;
    public ScrollPane scrollPane;

    public TextField username;
    public TextField password;
    public TextField newPassword;
    public TextField newUsername;
    private static String avatarText="";
    public TextField confirmDelete;

    @Override
    public void start(Stage stage) throws Exception {
        ChangeProfileMenu.stage = stage;
        AnchorPane profilePane = FXMLLoader.load(
                new URL(MainMenu.class.getResource("/fxml/changeProfile.fxml").toExternalForm()));


        HBox avatarPane = new HBox();
        for (Avatar avatar : AvatarFilesController.avatarHandler()) {
            avatar.setOnMouseClicked(new EventHandler<>(){
                @Override
                public void handle(MouseEvent event) {
                    avatarText = avatar.getAvatarName();
                    avatar.setRadius(70);
                    avatarPane.getChildren().forEach(node -> {
                        if(!node.equals(avatar)){
                            ((Avatar)node).setRadius(50);
                        }
                    });
                }
            });
            avatarPane.getChildren().add(avatar);
        }
        avatarPane.setSpacing(10);
        scrollPane = new ScrollPane();
        scrollPane.setContent(avatarPane);
        scrollPane.setLayoutX(300.0);
        scrollPane.setLayoutY(317.0);
        scrollPane.setPrefHeight(170);
        scrollPane.setPrefWidth(400.0);

        Button RandomAvatarButton = new Button("Random Avatar");
        RandomAvatarButton.setLayoutX(300.0);
        RandomAvatarButton.setLayoutY(500.0);
        RandomAvatarButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                avatarText = AvatarFilesController.getRandomAvatar().getAvatarName();
                avatarPane.getChildren().forEach(node -> {
                    if(((Avatar)node).getAvatarName().equals(avatarText)){
                        ((Avatar)node).setRadius(70);
                    }
                    else{
                        ((Avatar)node).setRadius(50);
                    }
                });
            }
        });
        profilePane.getChildren().add(scrollPane);
        profilePane.getChildren().add(RandomAvatarButton);

        Scene scene = new Scene(profilePane);
        stage.setScene(scene);
        stage.show();
    }

    public void checkChangeUsername(MouseEvent mouseEvent) throws Exception {
        if(RegisterController.checkChangeUsername(username.getText(), password.getText(), newUsername.getText()).equals("ok")){
            FilesController.changeUserUsername(FilesController.getUserByUsername(username.getText()), newUsername.getText());
            MainMenu.gameController = new GameController(username.getText());
            new MainMenu().start(ChangeProfileMenu.stage);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(RegisterController.checkChangeUsername(username.getText(), password.getText(), newUsername.getText()));
            alert.showAndWait();
        }
    }


    public void checkChangePassword(MouseEvent mouseEvent) throws Exception {
        if(RegisterController.checkChangeUsername(username.getText(), password.getText(), newPassword.getText()).equals("ok")) {
            FilesController.changeUserPassword(FilesController.getUserByUsername(username.getText()), newPassword.getText());
            MainMenu.gameController = new GameController(username.getText());
            new MainMenu().start(ChangeProfileMenu.stage);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(RegisterController.checkChangePassword(username.getText(), password.getText(), newPassword.getText()));
            alert.showAndWait();
        }
    }

    public void checkChangeAvatar(MouseEvent mouseEvent) throws Exception {
        if(RegisterController.checkChangeAvatar(username.getText(), password.getText(), avatarText).equals("ok")) {
            FilesController.changeUserAvatar(FilesController.getUserByUsername(username.getText()), avatarText);
            MainMenu.gameController = new GameController(username.getText());
            new MainMenu().start(ChangeProfileMenu.stage);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(RegisterController.checkChangeAvatar(username.getText(), password.getText(), avatarText));
            alert.showAndWait();
        }
    }

    public void checkDeleteAccount(MouseEvent mouseEvent) throws Exception {
        if(RegisterController.checkDeleteAccount(username.getText(), password.getText(),confirmDelete.getText()).equals("ok")) {
            FilesController.deleteUser(FilesController.getUserByUsername(username.getText()));
            new LoginMenu().start(ChangeProfileMenu.stage);

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(RegisterController.checkDeleteAccount(username.getText(), password.getText(), confirmDelete.getText()));
            alert.showAndWait();
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(stage);
    }

    public void gotoLogin(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(stage);
    }
}
