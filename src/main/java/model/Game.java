package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.FilesController;
import controller.GameController;

import java.io.*;
import java.util.ArrayList;

public class Game {
    private final ArrayList<User> users;


    private User currentUser;


    public Game() throws IOException {
        users = FilesController.getUsers();
    }


}
