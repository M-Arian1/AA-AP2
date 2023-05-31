package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.DataBase;
import model.Level;
import model.SaveOb;
import model.User;
import view.MainMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FilesController {

    public static ArrayList<User> getUsers() throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        File usersFile = new File("src/main/resources/data/Users.json");
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
        File usersFile = new File("src/main/resources/data/Users.json");
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(usersFile);
        fileWriter.write(gson.toJson(users, new TypeToken<ArrayList<User>>() {
        }.getType()));
        fileWriter.close();
    }
    public static void rewriteUsers(ArrayList<User> users) throws IOException {
        File usersFile = new File("src/main/resources/data/Users.json");
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(usersFile);
        fileWriter.write(gson.toJson(users, new TypeToken<ArrayList<User>>() {
        }.getType()));
        fileWriter.close();
    }
    public static void deleteUser(User user) throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        users = FilesController.getUsers();
        if(users== null) users = new ArrayList<User>();
        for (User user1 : users) {
            if (user1.getUsername().equals(user.getUsername())) {
                users.remove(user1);
                break;
            }
        }
        File usersFile = new File("src/main/resources/data/Users.json");
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
    public static void changeUserUsername(User user, String newUsername) throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        users = FilesController.getUsers();
        if(users== null) users = new ArrayList<User>();
        for (User user1 : users) {
            if (user1.getUsername().equals(user.getUsername())) {
                user1.setUsername(newUsername);
                break;
            }
        }
        File usersFile = new File("src/main/resources/data/Users.json");
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(usersFile);
        fileWriter.write(gson.toJson(users, new TypeToken<ArrayList<User>>() {
        }.getType()));
        fileWriter.close();
    }

    public static void changeUserPassword(User user, String newPassword) throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        users = FilesController.getUsers();
        if(users== null) users = new ArrayList<User>();
        for (User user1 : users) {
            if (user1.getUsername().equals(user.getUsername())) {
                user1.setPassword(newPassword);
                break;
            }
        }
        File usersFile = new File("src/main/resources/data/Users.json");
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(usersFile);
        fileWriter.write(gson.toJson(users, new TypeToken<ArrayList<User>>() {
        }.getType()));
        fileWriter.close();
    }
    public static void changeUserAvatar(User user, String avatarText) throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        users = FilesController.getUsers();
        if(users== null) users = new ArrayList<User>();
        for (User user1 : users) {
            if (user1.getUsername().equals(user.getUsername())) {
                user1.setAvatar(avatarText);
                break;
            }
        }
        File usersFile = new File("src/main/resources/data/Users.json");
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(usersFile);
        fileWriter.write(gson.toJson(users, new TypeToken<ArrayList<User>>() {
        }.getType()));
        fileWriter.close();
    }
    public static void changeUserScore(User user, int score, Level level) throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        users = FilesController.getUsers();
        if(users== null) users = new ArrayList<User>();
        for (User user1 : users) {
            if (user1.getUsername().equals(user.getUsername())) {
                user1.setScore(level, score);
                break;
            }
        }
        File usersFile = new File("src/main/resources/data/Users.json");
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(usersFile);
        fileWriter.write(gson.toJson(users, new TypeToken<ArrayList<User>>() {
        }.getType()));
        fileWriter.close();
    }
    public static void changeUserTime(User user, int time, Level level) throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        users = FilesController.getUsers();
        if(users== null) users = new ArrayList<User>();
        for (User user1 : users) {
            if (user1.getUsername().equals(user.getUsername())) {
                user1.setTime(level, time);
                break;
            }
        }
        File usersFile = new File("src/main/resources/data/Users.json");
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(usersFile);
        fileWriter.write(gson.toJson(users, new TypeToken<ArrayList<User>>() {
        }.getType()));
        fileWriter.close();
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



    public static void setCurrentUser(User user) throws IOException {
        DataBase db = new DataBase();
        db.setCurrentUser(user);
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("src/main/resources/data/DataBase.json");
        fileWriter.write(gson.toJson(db));
        fileWriter.close();
    }
    public static String getCurrentUser() throws IOException {
        DataBase db = new DataBase();
        File dbFile = new File("src/main/resources/data/DataBase.json");
        if (dbFile.exists()) {
            BufferedReader fileReader = new BufferedReader(new FileReader(dbFile));
            db = new Gson().fromJson(fileReader, DataBase.class);
            fileReader.close();
            if(db.getCurrentUser() == null) return null;
            return db.getCurrentUser().getUsername();
        } else {
            db = new DataBase();
            FileWriter fileWriter = new FileWriter(dbFile);
            fileWriter.close();
            return null;
        }
    }
    public static void saveGame(HashMap<String, SaveOb> saveObs) throws IOException {
        File usersFile = new File("src/main/resources/data/SaveObs.json");
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(usersFile);
        fileWriter.write(gson.toJson(saveObs, new TypeToken<HashMap<String, SaveOb>>() {
        }.getType()));
        fileWriter.close();

    }
    public static HashMap<String, SaveOb> getSaveObs() throws IOException {
        File usersFile = new File("src/main/resources/data/SaveObs.json");
        if (usersFile.exists()) {
            BufferedReader fileReader = new BufferedReader(new FileReader(usersFile));
            HashMap<String, SaveOb> saveObs = new Gson().fromJson(fileReader, new TypeToken<HashMap<String, SaveOb>>() {
            }.getType());
            fileReader.close();
            return saveObs;
        } else {
            HashMap<String, SaveOb> saveObs = new HashMap<String, SaveOb>();
            FileWriter fileWriter = new FileWriter(usersFile);
            fileWriter.close();
            return saveObs;
        }

    }
    public static void saveOneGame(SaveOb saveOb) throws IOException {
        HashMap<String, SaveOb> saveObs = new HashMap<String, SaveOb>();
        saveObs = FilesController.getSaveObs();
        if(saveObs == null) saveObs = new HashMap<String, SaveOb>();
        saveObs.put(saveOb.getUsername(), saveOb);
        saveGame(saveObs);
    }
    public static SaveOb loadOneGame(String username) throws IOException {
        HashMap<String, SaveOb> saveObs = new HashMap<String, SaveOb>();
        saveObs = FilesController.getSaveObs();
        if(saveObs == null) return null;
        return saveObs.get(username);
    }

    public static void saveSettingControllerOb(SettingsControllerOb settingControllerOb) throws IOException {
        SaveOb saveOb = new SaveOb();
        saveOb = FilesController.loadOneGame(MainMenu.username);
        if(saveOb==null){
            saveOb = new SaveOb();
            saveOb.setUsername(MainMenu.username);
        }
        saveOb.setSettingsControllerOb(settingControllerOb);
        FilesController.saveOneGame(saveOb);

    }
    public static void loadSettingsControllerOb() throws IOException {
        if(loadOneGame(MainMenu.username)!=null){
            if(loadOneGame(MainMenu.username).getSettingsControllerOb()!=null){
                loadOneGame(MainMenu.username).getSettingsControllerOb().setStatic();
            }
        }

    }


}
