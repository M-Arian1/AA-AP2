package model;


public class User {
    private String username;
    private String password;
    private String avatarText;

    private int score;



    public User(String username, String password, String avatarText) {
        this.username = username;
        this.password = password;
        this.avatarText = avatarText;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAvatar() {
        return avatarText;
    }

    public void setAvatar(String avatarText) {
        this.avatarText = avatarText;
    }
}
