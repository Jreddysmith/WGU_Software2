package schedule.Models;

public class User {
    private int userId;
    private String userName;
    private String userPassword;
    private int userActive;

    public User(int userId, String userName, String userPassword, int userActive) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userActive = userActive;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserActive() {
        return userActive;
    }

    public void setUserActive(int userActive) {
        this.userActive = userActive;
    }
}
