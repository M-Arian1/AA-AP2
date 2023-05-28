package controller;

import model.Avatar;
import model.Game;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AvatarFilesController {

    public static ArrayList<Avatar> avatarHandler() {
        ArrayList<Avatar> avatars = new ArrayList<>();
//        File folder = new File(Game.class.getResource("/images/avatars/").toExternalForm());
        File folder = new File("src/main/resources/images/avatars/");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                avatars.add(new Avatar(file.getName(), 50));
            }
        }
        return avatars;
    }
    public static Avatar getRandomAvatar() {
        ArrayList<Avatar> avatars = avatarHandler();
        return avatars.get((int) (Math.random() * avatars.size()));
    }

}
