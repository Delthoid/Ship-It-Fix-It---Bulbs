package com.delts.shipitfixit.auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.delts.shipitfixit.models.User;

public class Auth {
    private final Context context;
    private final SharedPreferences sharedPref;

    private static final String preferenceFileKey = "auth";

    public static User loggedUser;

    public Auth(Context context) {
        this.context = context;
        this.sharedPref = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
    }

    //Login
    public void saveUser(User user) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("userId", user.getId());
        editor.putString("userName", user.getUserName());
        editor.putString("password", user.getPassWord());
        editor.apply();
    }

    //Logout
    public void logout() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("userId");
        editor.remove("userName");
        editor.remove("password");
        editor.apply();
    }

    public void register(Context context) {

    }

    //Check if there is saved user id
    public User getSavedUser() {
        final int userId = sharedPref.getInt("userId", 0);
        final String userName = sharedPref.getString("userName", "");
        final String password = sharedPref.getString("password", "");

        return new User(userId, userName, password);
    }

    public void saveLoggedUser(User user) {
        loggedUser = user;
    }

    public User getLoggedUser() {
        return loggedUser;
    }
}
