package com.delts.shipitfixit.auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.delts.shipitfixit.models.User;

public class Auth {
    private final Context context;

    public Auth(Context context) {
        this.context = context;
    }

    private static final String preferenceFileKey = "auth";

    //Login
    public void login(User user) {
        SharedPreferences sharedPref = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
    }

    //Logout
    public void logout() {

    }

    public void register(Context context) {

    }

    //Check if there is saved user id
    public void checkLoggedUsers() {

    }
}
