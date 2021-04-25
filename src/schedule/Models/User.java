package schedule.Models;

public class User {
    private String userId;
    private String userName;
    private String userPassword;
    private String userActive;

    public User(String userId, String userName, String userPassword, String userActive) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userActive = userActive;
    }

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public String getUserActive() {
        return userActive;
    }

    public void setUserActive(String userActive) {
        this.userActive = userActive;
    }
}
