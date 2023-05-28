package model;

public class DataBase {
    private User currentUser;

    public DataBase() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}