package EIST24L03PB03;

public class User {
    private String userName;
    private String password;

    private int userID;

    private static int nextId = 1;
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.userID = getNextId();
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private static synchronized int getNextId() {
        return nextId++;
    }


}

