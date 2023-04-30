package com.delts.shipitfixit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.delts.shipitfixit.auth.Auth;
import com.delts.shipitfixit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Check if there is logged in user
        checkAuth();
    }

    private void checkAuth() {
        Auth auth = new Auth(getApplicationContext());
        boolean hasLoggedIn = auth.checkLoggedUsers();

        //If hasLoggedIn is false, then open login screen
        if (hasLoggedIn) {
            Intent intent = new Intent(this, SignIn.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {

        }
    }
}