package com.delts.shipitfixit.models;

public class User {
    private int id;
    private String userName;
    private String passWord;

    User() {
        this.id = 0;
        this.userName = "";
        this.passWord = "";
    }

    public User(int id, String userName, String passWord) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
    }

    public int getId() { return this.id; }
    public String getUserName() { return this.userName; }
    public String getPassWord() { return this.passWord; }
}
