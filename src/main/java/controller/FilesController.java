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
    public static void saveBalls(ArrayList<Double> balls) throws IOException {
        File usersFile = new File("src/main/resources/data/Balls/Balls.json");
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(usersFile);
        fileWriter.write(gson.toJson(balls, new TypeToken<ArrayList<Double>>() {
        }.getType()));
        fileWriter.close();
    }
    public static void saveBallsNumbers(ArrayList<Integer> balls) throws IOException {
        File usersFile = new File("src/main/resources/data/Balls/Balls2.json");
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(usersFile);
        fileWriter.write(gson.toJson(balls, new TypeToken<ArrayList<Integer>>() {
        }.getType()));
        fileWriter.close();
    }
    public static void saveBallsIce(Integer Ice) throws IOException {
        File usersFile = new File("src/main/resources/data/Balls/Balls3.json");
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(usersFile);
        fileWriter.write(gson.toJson(Ice, new TypeToken<Integer>() {
        }.getType()));
        fileWriter.close();
    }
    public static void saveNumberOfBalls(Integer numberOfBalls) throws IOException {
        File usersFile = new File("src/main/resources/data/Balls/Balls4.json");
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(usersFile);
        fileWriter.write(gson.toJson(numberOfBalls, new TypeToken<Integer>() {
        }.getType()));
        fileWriter.close();

    }

    public static ArrayList<Double> getBalls() throws IOException {
        ArrayList<Double> balls = new ArrayList<>();
        File ballsFile = new File("src/main/resources/data/Balls/Balls.json");
        if (ballsFile.exists()) {
            BufferedReader fileReader = new BufferedReader(new FileReader(ballsFile));
            balls = new Gson().fromJson(fileReader, new TypeToken<ArrayList<Double>>() {
            }.getType());
            fileReader.close();
        } else {
            balls = new ArrayList<Double>();
            FileWriter fileWriter = new FileWriter(ballsFile);
            fileWriter.close();
        }
        return balls;
    }
    public static ArrayList<Integer> getBallsNumbers() throws IOException {
        ArrayList<Integer> balls = new ArrayList<>();
        File ballsFile = new File("src/main/resources/data/Balls/Balls2.json");
        if (ballsFile.exists()) {
            BufferedReader fileReader = new BufferedReader(new FileReader(ballsFile));
            balls = new Gson().fromJson(fileReader, new TypeToken<ArrayList<Integer>>() {
            }.getType());
            fileReader.close();
        } else {
            balls = new ArrayList<Integer>();
            FileWriter fileWriter = new FileWriter(ballsFile);
            fileWriter.close();
        }
        return balls;
    }
    public static Integer getBallsIce() throws IOException {
        Integer balls = 0;
        File ballsFile = new File("src/main/resources/data/Balls/Balls3.json");
        if (ballsFile.exists()) {
            BufferedReader fileReader = new BufferedReader(new FileReader(ballsFile));
            balls = new Gson().fromJson(fileReader, new TypeToken<Integer>() {
            }.getType());
            fileReader.close();
        } else {
            balls = 0;
            FileWriter fileWriter = new FileWriter(ballsFile);
            fileWriter.close();
        }
        return balls;
    }
    public static Integer getNumberOfBalls() throws IOException {
        Integer balls = 0;
        File ballsFile = new File("src/main/resources/data/Balls/Balls4.json");
        if (ballsFile.exists()) {
            BufferedReader fileReader = new BufferedReader(new FileReader(ballsFile));
            balls = new Gson().fromJson(fileReader, new TypeToken<Integer>() {
            }.getType());
            fileReader.close();
        } else {
            balls = 0;
            FileWriter fileWriter = new FileWriter(ballsFile);
            fileWriter.close();
        }
        return balls;
    }
    public static void saveSettingControllerOb(SettingsControllerOb settingControllerOb) throws IOException {
        File usersFile = new File("src/main/resources/data/Settings.json");
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(usersFile);
        fileWriter.write(gson.toJson(settingControllerOb, new TypeToken<SettingsControllerOb>() {
        }.getType()));
        fileWriter.close();
    }
    public static void setSettingsControllerOb() throws IOException {
        File settingsFile = new File("src/main/resources/data/Settings.json");
        if (settingsFile.exists()) {
            BufferedReader fileReader = new BufferedReader(new FileReader(settingsFile));
            SettingsControllerOb settingControllerOb = new Gson().fromJson(fileReader, new TypeToken<SettingsControllerOb>() {
            }.getType());
            fileReader.close();
            settingControllerOb.setStatic();
        } else {
            SettingsControllerOb settingControllerOb = new SettingsControllerOb();
            FileWriter fileWriter = new FileWriter(settingsFile);
            fileWriter.close();
        }

    }


}
