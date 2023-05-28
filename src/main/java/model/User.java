package model;


import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private String avatarText;

    private HashMap<Level,Integer> scores = new HashMap<>(){{
        put(Level.EASY,0); //easy
        put(Level.MEDIUM,0); //medium
        put(Level.HARD,0); //hard
    }};
    private HashMap<Level,Integer> times = new HashMap<>(){{
        put(Level.EASY,0); //easy
        put(Level.MEDIUM,0); //medium
        put(Level.HARD,0); //hard
    }};



    public User(String username, String password, String avatarText) {
        this.username = username;
        this.password = password;
        this.avatarText = avatarText;
        this.scores.put(Level.EASY,0);
        this.scores.put(Level.MEDIUM,0);
        this.scores.put(Level.HARD,0);
        this.times.put(Level.EASY,0);
        this.times.put(Level.MEDIUM,0);
        this.times.put(Level.HARD,0);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore(Level level) {
        if(this.scores == null) {
            return 0;
        }
        return this.scores.get(level);
    }

    public void setScore(Level level,int score) {
        if(this.scores == null) {
            this.scores = new HashMap<>();
        }
        this.scores.put(level,score);
    }
    public int getTime(Level level) {
        if(this.times == null) {
            return 0;
        }
        return this.times.get(level);
    }

    public void setTime(Level level,int time) {
        if(this.times == null) {
            this.times = new HashMap<>();
        }
        this.times.put(level,time);
    }

    public String getAvatar() {
        return avatarText;
    }

    public void setAvatar(String avatarText) {
        this.avatarText = avatarText;
    }
}
