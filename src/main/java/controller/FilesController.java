package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.DataBase;
import model.User;

import java.io.*;
import java.util.ArrayList;

public class FilesController {

    public static ArrayList<User> getUsers() throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        File usersFile = new File("Users.json");
        if (usersFile.exists()) {
            BufferedReader fileReader = new BufferedReader(new FileReader(usersFile));
            users = new Gson().fromJson(fileReader, new TypeToken<ArrayList<User>>() {
            }.getType());
            fileReader.close();
        } else {
            users = new ArrayList<User>();
            FileWriter fileWriter = new FileWriter(usersFile);
            fileWriter.close();
        }
        return users;
    }
    public static void addUser(User user) throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        users = FilesController.getUsers();
        if(users== null) users = new ArrayList<User>();
        users.add(user);
        File usersFile = new File("Users.json");
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(usersFile);
        fileWriter.write(gson.toJson(users, new TypeToken<ArrayList<User>>() {
        }.getType()));
        fileWriter.close();
    }
    public static void addUserByString(String username, String password, String avatarText) throws IOException {
        User user = new User(username, password, avatarText);
        addUser(user);

    }

    public static User getUserByUsername(String username) throws IOException {
        ArrayList<User> users;
        users = FilesController.getUsers();
        if(users == null) return null;
        for (User user : users) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public void setCurrentUser(User user) throws IOException {
        DataBase db = new DataBase();
        db.setCurrentUser(user);
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("DataBase.json");
        fileWriter.write(gson.toJson(db));
        fileWriter.close();
    }
}
