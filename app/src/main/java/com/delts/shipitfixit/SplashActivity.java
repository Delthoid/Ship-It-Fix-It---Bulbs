package com.delts.shipitfixit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.delts.shipitfixit.auth.Auth;
import com.delts.shipitfixit.database.UsersDatabaseHelper;
import com.delts.shipitfixit.models.User;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        startSplash();
    }

    public void startSplash() {
        Auth auth = new Auth(getApplicationContext());
        try {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    final UsersDatabaseHelper dbHlelper = new UsersDatabaseHelper(getApplicationContext());
                    final User user = auth.getSavedUser();

                    final boolean response = dbHlelper.checkLoginSuccess(user.getUserName(), user.getPassWord());

                    Intent intent = new Intent(getApplicationContext(), response ? MainActivity.class : SignIn.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }, SPLASH_TIME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}