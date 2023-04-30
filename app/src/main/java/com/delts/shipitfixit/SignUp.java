package com.delts.shipitfixit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.delts.shipitfixit.databinding.ActivitySignUpBinding;

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
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }
}