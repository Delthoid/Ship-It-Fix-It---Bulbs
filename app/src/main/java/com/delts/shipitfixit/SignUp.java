package com.delts.shipitfixit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.delts.shipitfixit.database.UserInfoDBHelper;
import com.delts.shipitfixit.database.UsersDatabaseHelper;
import com.delts.shipitfixit.databinding.ActivitySignUpBinding;
import com.delts.shipitfixit.models.User;
import com.delts.shipitfixit.models.UserInfo;

import java.util.Calendar;

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
        binding.birthdayPickerButtonSignUp.setText(getCurrentDate());
        binding.birthdayPickerButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.genderPickerSpinnerSignUp.setAdapter(adapter);

        binding.resgisterButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userName = binding.usernameFieldSignup.getText().toString();
                final String password = binding.passwordFieldSignup.getText().toString();
                final String reEnterPassword = binding.reEnterPasswordFieldSignUp.getText().toString();
                final String firstname = binding.firstnameFieldSignUp.getText().toString();
                final String lastname = binding.lastnameFieldSignUp.getText().toString();
                final String birthday = binding.birthdayPickerButtonSignUp.getText().toString();
                final String address = binding.addressFieldSignUp.getText().toString();

                binding.usernameFieldSignup.setError(userName.isEmpty() ? "Username is required" : null);
                binding.passwordFieldSignup.setError(password.isEmpty() ? "Password is required" : null);
                binding.reEnterPasswordFieldSignUp.setError(reEnterPassword.isEmpty() ? "Re-entering password is required" : null);
                binding.firstnameFieldSignUp.setError(firstname.isEmpty() ? "Firstname is required" : null);
                binding.lastnameFieldSignUp.setError(lastname.isEmpty() ? "Lastname is required" : null);
                binding.addressFieldSignUp.setError(address.isEmpty() ? "Address is required" : null);
                //Mag login din dapat dito
                if (!userName.isEmpty() && !password.isEmpty() && !reEnterPassword.isEmpty() && !firstname.isEmpty()
                    && !lastname.isEmpty() && !address.isEmpty()) {
                    if(birthday.equals(getCurrentDate())){
                        Toast.makeText(getApplicationContext(), "Please set your birthday", Toast.LENGTH_SHORT).show();
                    } else{
                        if (signUp(userName, password)) {
                            UserInfoDBHelper userInfoDBHelper = new UserInfoDBHelper(getApplicationContext());
                            Intent intent = new Intent(getApplicationContext(), SignIn.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
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

    public void openDatePicker(){
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                binding.birthdayPickerButtonSignUp.setText(makeStringDate(month, dayOfMonth, year));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public String getCurrentDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return getMonthFormat(month + 1) + "-" + day + "-" + year;
    }
    public String makeStringDate(int month, int dayOfMonth, int year){
        return getMonthFormat(month + 1) + "-" + dayOfMonth + "-" + year;
    }

    public String getMonthFormat(int month){
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";
        return "JAN";
    }
}