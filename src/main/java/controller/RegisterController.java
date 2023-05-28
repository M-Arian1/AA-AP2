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

    public static String checkSignup(String username, String password, String avatarText) {
        if(username.length()==0 || password.length()==0 || avatarText.length()==0) return "a field is empty!";

        if(password.length()<4) return "password is too short!";

        if(password.length()>12) return "password is too long!";

        return "ok";
    }
}
