package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;

import java.io.*;
import java.lang.reflect.Array;
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
    public static void rewriteUsers(ArrayList<User> users) throws IOException {
        File usersFile = new File("Users.json");
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
        File usersFile = new File("Users.json");
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
        File usersFile = new File("Users.json");
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
        File usersFile = new File("Users.json");
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
        FileWriter fileWriter = new FileWriter("DataBase.json");
        fileWriter.write(gson.toJson(db));
        fileWriter.close();
    }
    public static String getCurrentUser() throws IOException {
        DataBase db = new DataBase();
        File dbFile = new File("DataBase.json");
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

    public static ArrayList<ArrayList<Ball>> getAllDefaultBalls() throws IOException {
        ArrayList<ArrayList<Ball>> ballsSet = new ArrayList<ArrayList<Ball>>();
        File ballsFile = new File("Balls.json");
        if (ballsFile.exists()) {
            BufferedReader fileReader = new BufferedReader(new FileReader(ballsFile));
            ballsSet = new Gson().fromJson(fileReader, new TypeToken<ArrayList<Ball>>() {
            }.getType());
            fileReader.close();
        } else {
            ballsSet = new ArrayList<ArrayList<Ball>>();
            FileWriter fileWriter = new FileWriter(ballsFile);
            fileWriter.close();
        }
        return ballsSet;
    }
    public static ArrayList<Ball> getDefaultBalls(int mapNumber) throws IOException {
        ArrayList<ArrayList<Ball>> ballsSet = new ArrayList<ArrayList<Ball>>();
        File ballsFile = new File("Balls.json");
        if (ballsFile.exists()) {
            BufferedReader fileReader = new BufferedReader(new FileReader(ballsFile));
            ballsSet = new Gson().fromJson(fileReader, new TypeToken<ArrayList<Ball>>() {
            }.getType());
            fileReader.close();
        } else {
            ballsSet = new ArrayList<ArrayList<Ball>>();
            FileWriter fileWriter = new FileWriter(ballsFile);
            fileWriter.close();
        }
        return ballsSet.get(mapNumber);
    }
    public static void addDefaultBall(Ball ball, int mapNumber) throws IOException {
        ArrayList<ArrayList<Ball>> ballsSet = new ArrayList<ArrayList<Ball>>();
        ballsSet = FilesController.getAllDefaultBalls();
        if(ballsSet== null) ballsSet = new ArrayList<ArrayList<Ball>>();
        ballsSet.get(mapNumber).add(ball);
        File ballsFile = new File("Balls.json");
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(ballsFile);
        fileWriter.write(gson.toJson(ballsSet, new TypeToken<ArrayList<Ball>>() {
        }.getType()));
        fileWriter.close();
    }
    public static void addDefaultSetOfBalls(ArrayList <Ball> balls) throws IOException {
        ArrayList<ArrayList<Ball>> ballsSet = new ArrayList<ArrayList<Ball>>();
        ballsSet = FilesController.getAllDefaultBalls();
        if(ballsSet== null) ballsSet = new ArrayList<ArrayList<Ball>>();
        ballsSet.add(balls);
        File ballsFile = new File("Balls.json");
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(ballsFile);
        fileWriter.write(gson.toJson(ballsSet, new TypeToken<ArrayList<Ball>>() {
        }.getType()));
        fileWriter.close();

    }
    //    public static ArrayList<ArrayList<Ball>> getAllDefaultBalls() throws IOException {
//        ArrayList<ArrayList<Ball>> ballsSet = new ArrayList<ArrayList<Ball>>();
//        File ballsFile = new File("Balls.json");
//        if (ballsFile.exists()) {
//            BufferedReader fileReader = new BufferedReader(new FileReader(ballsFile));
//            ballsSet = new Gson().fromJson(fileReader, new TypeToken<ArrayList<User>>() {
//            }.getType());
//            fileReader.close();
//        } else {
//            ballsSet = new ArrayList<ArrayList<Ball>>();
//            FileWriter fileWriter = new FileWriter(ballsFile);
//            fileWriter.close();
//        }
//        return ballsSet;
//    }
//    public static ArrayList<Ball> getDefaultBalls(int mapNumber) throws IOException {
//        ArrayList<ArrayList<Ball>> ballsSet = new ArrayList<ArrayList<Ball>>();
//        File ballsFile = new File("Balls.json");
//        if (ballsFile.exists()) {
//            BufferedReader fileReader = new BufferedReader(new FileReader(ballsFile));
//            ballsSet = new Gson().fromJson(fileReader, new TypeToken<ArrayList<User>>() {
//            }.getType());
//            fileReader.close();
//        } else {
//            ballsSet = new ArrayList<ArrayList<Ball>>();
//            FileWriter fileWriter = new FileWriter(ballsFile);
//            fileWriter.close();
//        }
//        return ballsSet.get(mapNumber);
//    }
//    public static void addDefaultBall(Ball ball, int mapNumber) throws IOException {
//        ArrayList<ArrayList<Ball>> ballsSet = new ArrayList<ArrayList<Ball>>();
//        ballsSet = FilesController.getAllDefaultBalls();
//        if(ballsSet== null) ballsSet = new ArrayList<ArrayList<Ball>>();
//        ballsSet.get(mapNumber).add(ball);
//        File ballsFile = new File("Balls.json");
//        Gson gson = new Gson();
//        FileWriter fileWriter = new FileWriter(ballsFile);
//        fileWriter.write(gson.toJson(ballsSet, new TypeToken<ArrayList<Ball>>() {
//        }.getType()));
//        fileWriter.close();
//    }
//    public static void addDefaultSetOfBalls(ArrayList <Ball> balls) throws IOException {
//        ArrayList<ArrayList<Ball>> ballsSet = new ArrayList<ArrayList<Ball>>();
//        ballsSet = FilesController.getAllDefaultBalls();
//        if(ballsSet== null) ballsSet = new ArrayList<ArrayList<Ball>>();
//        ballsSet.add(balls);
//        File ballsFile = new File("Balls.json");
//        Gson gson = new Gson();
//        FileWriter fileWriter = new FileWriter(ballsFile);
//        fileWriter.write(gson.toJson(ballsSet, new TypeToken<ArrayList<Ball>>() {
//        }.getType()));
//        fileWriter.close();
//
//    }


//    public static ArrayList<ArrayList<Ball>> getAllDefaultBalls() throws IOException {
//        ArrayList<ArrayList<SaveableBall>> ballsSet = new ArrayList<ArrayList<SaveableBall>>();
//        File ballsFile = new File("Balls.json");
//        if (ballsFile.exists()) {
//            BufferedReader fileReader = new BufferedReader(new FileReader(ballsFile));
//            ballsSet = new Gson().fromJson(fileReader, new TypeToken<ArrayList<User>>() {
//            }.getType());
//            fileReader.close();
//        } else {
//            ballsSet = new ArrayList<ArrayList<SaveableBall>>();
//            FileWriter fileWriter = new FileWriter(ballsFile);
//            fileWriter.close();
//        }
//        ArrayList<ArrayList<Ball>> ConvertBallsSet = new ArrayList<ArrayList<Ball>>();
//        for (ArrayList<SaveableBall> saveableBalls : ballsSet) {
//            ArrayList<Ball> balls = new ArrayList<Ball>();
//            for (SaveableBall saveableBall : saveableBalls) {
//                balls.add(ConvertBall.ConvertSavableBallToBall(saveableBall));
//            }
//            ConvertBallsSet.add(balls);
//        }
//        return ConvertBallsSet;
//    }
//    public static ArrayList<Ball> getDefaultBalls(int mapNumber) throws IOException {
//        ArrayList<ArrayList<SaveableBall>> ballsSet = new ArrayList<ArrayList<SaveableBall>>();
//        File ballsFile = new File("Balls.json");
//        if (ballsFile.exists()) {
//            BufferedReader fileReader = new BufferedReader(new FileReader(ballsFile));
//            ballsSet = new Gson().fromJson(fileReader, new TypeToken<ArrayList<User>>() {
//            }.getType());
//            fileReader.close();
//        } else {
//            ballsSet = new ArrayList<ArrayList<SaveableBall>>();
//            FileWriter fileWriter = new FileWriter(ballsFile);
//            fileWriter.close();
//        }
//        ArrayList<Ball> balls = new ArrayList<Ball>();
//        for (SaveableBall saveableBall : ballsSet.get(mapNumber)) {
//            balls.add(ConvertBall.ConvertSavableBallToBall(saveableBall));
//        }
//        return balls;
//    }
//    public static void addDefaultBall(Ball ball, int mapNumber) throws IOException {
//        ArrayList<ArrayList<Ball>> ballsSet = new ArrayList<ArrayList<Ball>>();
//        ballsSet = FilesController.getAllDefaultBalls();
//        if(ballsSet== null) ballsSet = new ArrayList<ArrayList<Ball>>();
//        ballsSet.get(mapNumber).add(ball);
//        File ballsFile = new File("Balls.json");
//        Gson gson = new Gson();
//        FileWriter fileWriter = new FileWriter(ballsFile);
//        fileWriter.write(gson.toJson(ballsSet, new TypeToken<ArrayList<Ball>>() {
//        }.getType()));
//        fileWriter.close();
//    }
//    public static void addDefaultSetOfBalls(ArrayList <Ball> balls) throws IOException {
//        ArrayList<ArrayList<Ball>> ballsSet = new ArrayList<ArrayList<Ball>>();
//        ballsSet = FilesController.getAllDefaultBalls();
//        if(ballsSet== null) ballsSet = new ArrayList<ArrayList<Ball>>();
//        ballsSet.add(balls);
//        File ballsFile = new File("Balls.json");
//        Gson gson = new Gson();
//        FileWriter fileWriter = new FileWriter(ballsFile);
//        fileWriter.write(gson.toJson(ballsSet, new TypeToken<ArrayList<Ball>>() {
//        }.getType()));
//        fileWriter.close();
//
//    }
}
