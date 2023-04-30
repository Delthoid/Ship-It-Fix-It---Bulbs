package com.delts.shipitfixit.auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.delts.shipitfixit.models.User;

public class Auth {
    private final Context context;
    private final SharedPreferences sharedPref;

    private static final String preferenceFileKey = "auth";

    public Auth(Context context) {
        this.context = context;
        this.sharedPref = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
    }

    //Login
    public void login(User user) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("userId", user.getId());
        editor.apply();
    }

    //Logout
    public void logout() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("userId");
        editor.apply();
    }

    public void register(Context context) {

    }

    //Check if there is saved user id
    public boolean checkLoggedUser() {
        final int userId = sharedPref.getInt("userId", 0);
        return userId != 0;
    }
}
