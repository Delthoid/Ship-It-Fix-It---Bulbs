package com.delts.shipitfixit.models;

public class User {
    int id;
    String userName;
    String passWord;

    User() {
        this.id = 0;
        this.userName = "";
        this.passWord = "";
    }

    User(int id, String userName, String passWord) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
    }
}
