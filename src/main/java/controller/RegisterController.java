package controller;

import model.User;

import java.io.*;
import java.util.ArrayList;

public class RegisterController {
    public static String checkLogin(String username, String password) throws IOException {

        if(username.length()==0 || password.length()==0) return "a field is empty!";

        if(FilesController.getUserByUsername(username)==null) return "Username doesn't exist!";

        if(!FilesController.getUserByUsername(username).getPassword().equals(password)) return "Password is incorrect!";

        return "ok";
    }

    public static String checkSignup(String username, String password, String avatarText) throws IOException {
        if(username.length()==0 || password.length()==0 || avatarText.length()==0) return "a field is empty!";

        if(FilesController.getUserByUsername(username)!=null) return "Username already exists!";

        if(password.length()<4) return "password is too short!";

        if(password.length()>12) return "password is too long!";

        return "ok";
    }

    public static String checkChangeUsername(String username, String password, String newUsername) throws IOException {
        if(username.length()==0 || password.length()==0 || newUsername.length()==0) return "a field is empty!";
        if(username.equals("guest")) return "guest can't change username!";
        if(FilesController.getUserByUsername(username)==null) return "Username doesn't exist!";

        if(!FilesController.getUserByUsername(username).getPassword().equals(password)) return "Password is incorrect!";

        if(FilesController.getUserByUsername(newUsername)!=null) return "Username already exists!";

        return "ok";
    }
    public static String checkChangePassword(String username, String password, String newPassword) throws IOException {
        if(username.length()==0 || password.length()==0 || newPassword.length()==0) return "a field is empty!";
        if(username.equals("guest")) return "guest can't change password!";
        if(FilesController.getUserByUsername(username)==null) return "Username doesn't exist!";

        if(!FilesController.getUserByUsername(username).getPassword().equals(password)) return "Password is incorrect!";

        if(newPassword.length()<4) return "password is too short!";

        if(newPassword.length()>12) return "password is too long!";

        return "ok";
    }

    public static String checkChangeAvatar(String username, String password, String avatarText) throws IOException {
        if(username.length()==0 || password.length()==0 || avatarText.length()==0) return "a field is empty!";
        if(username.equals("guest")) return "guest can't change avatar!";
        if(FilesController.getUserByUsername(username)==null) return "Username doesn't exist!";

        if(!FilesController.getUserByUsername(username).getPassword().equals(password)) return "Password is incorrect!";

        return "ok";
    }

    public static String checkDeleteAccount(String username, String password, String confirmDelete) throws IOException {
        if(username.length()==0 || password.length()==0 || confirmDelete.length()==0) return "a field is empty!";
        if(username.equals("guest")) return "guest can't delete account!";
        if(FilesController.getUserByUsername(username)==null) return "Username doesn't exist!";

        if(!FilesController.getUserByUsername(username).getPassword().equals(password)) return "Password is incorrect!";

        if(!confirmDelete.equals("CONFIRM")) return "you didn't type \"CONFIRM\" correctly!";

        return "ok";

    }
}
