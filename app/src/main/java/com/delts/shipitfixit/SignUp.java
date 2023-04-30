package com.delts.shipitfixit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.delts.shipitfixit.database.UsersDatabaseHelper;
import com.delts.shipitfixit.databinding.ActivitySignUpBinding;
import com.delts.shipitfixit.models.User;

public class SignUp extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        binding.resgisterButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userName = binding.usernameFieldSignup.getText().toString();
                final String password = binding.passwordFieldSignup.getText().toString();

                binding.usernameFieldSignup.setError(userName.isEmpty() ? "Username is required" : null);
                binding.passwordFieldSignup.setError(password.isEmpty() ? "Password is required" : null);

                //Mag login din dapat dito
                if (!userName.isEmpty() || !password.isEmpty()) {
                    if (signUp(userName, password)) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private boolean signUp(String userName, String password) {
        UsersDatabaseHelper dbHelper = new UsersDatabaseHelper(getApplicationContext());
        final boolean response = dbHelper.insertAccount(userName, password);

        Toast.makeText(getApplicationContext(), response ? "Register successful" : "Failed to register", Toast.LENGTH_SHORT).show();
        return response;
    }
}