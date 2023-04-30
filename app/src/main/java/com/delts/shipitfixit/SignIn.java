package com.delts.shipitfixit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.delts.shipitfixit.auth.Auth;
import com.delts.shipitfixit.database.UsersDatabaseHelper;
import com.delts.shipitfixit.databinding.ActivitySignInBinding;
import com.delts.shipitfixit.models.User;


public class SignIn extends AppCompatActivity {

    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userName = binding.usernameField.getText().toString();
                final String password = binding.passwordField.getText().toString();

                binding.usernameField.setError(userName.isEmpty() ? "Username cannot be empty" : null);
                binding.passwordField.setError(password.isEmpty() ? "Password cannot be empty" : null);

                if (!password.isEmpty() || !userName.isEmpty()) {
                    if (signIn(userName, password)) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
            }
        });

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private boolean signIn(String userName, String password) {
        Auth auth = new Auth(getApplicationContext());
        UsersDatabaseHelper dbHelper = new UsersDatabaseHelper(getApplicationContext());
        final boolean response = dbHelper.checkLoginSuccess(userName, password);

        Toast.makeText(getApplicationContext(), response ? "Login successful" : "User doesn't exist", Toast.LENGTH_SHORT).show();

        if (response) {
            auth.saveUser(new User(0, userName, password));
        }

        return response;
    }
}