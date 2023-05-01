package com.delts.shipitfixit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.delts.shipitfixit.database.UserInfoDBHelper;
import com.delts.shipitfixit.database.UsersDatabaseHelper;
import com.delts.shipitfixit.databinding.ActivitySignUpBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String gender;
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
                openDatePickerDialog();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.genderPickerSpinnerSignUp.setAdapter(adapter);
        binding.genderPickerSpinnerSignUp.setOnItemSelectedListener(this);

        binding.resgisterButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userName = binding.usernameFieldSignup.getText().toString();
                final String password = binding.passwordFieldSignup.getText().toString();
                final String reEnterPassword = binding.reEnterPasswordFieldSignUp.getText().toString();
                final String firstname = binding.firstnameFieldSignUp.getText().toString();
                final String lastname = binding.lastnameFieldSignUp.getText().toString();
                final String age = binding.ageFieldSignUp.getText().toString();
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
                    UserInfoDBHelper userInfoDBHelper = new UserInfoDBHelper(getApplicationContext());
                    UsersDatabaseHelper usersDBHelper = new UsersDatabaseHelper(getApplicationContext());
                    if(usersDBHelper.checkUsernameIfExist(userName)){
                       binding.usernameFieldSignup.setError("Username already exists");
                       Toast.makeText(getApplicationContext(), "Username already in use", Toast.LENGTH_SHORT).show();
                    } else {
                        if(birthday.equals(getCurrentDate())) {
                            Toast.makeText(getApplicationContext(), "Please set your birthday", Toast.LENGTH_SHORT).show();
                        } else if(Integer.parseInt(age) < 18) {
                            Toast.makeText(getApplicationContext(), "You must be at least 18 years old", Toast.LENGTH_SHORT).show();
                        } else if(gender.equals("Gender")){
                            Toast.makeText(getApplicationContext(), "Please set your gender", Toast.LENGTH_SHORT).show();
                        } else{
                            if(!password.equals(reEnterPassword)){
                                binding.reEnterPasswordFieldSignUp.setError("Passwords doesn't match");
                            } else{
                                //insertAccount
                                if (signUp(userName, password)) {
                                    //insertUserInfo
                                    userInfoDBHelper.insertUserInfo(userName, firstname, lastname, birthday, Integer.parseInt(age), gender, address);

                                    Intent intent = new Intent(getApplicationContext(), SignIn.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }
                            }
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

    public void openDatePickerDialog(){
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                binding.birthdayPickerButtonSignUp.setText(makeStringDate(month, dayOfMonth, year));
                binding.ageFieldSignUp.setText(Integer.toString(getComputedIntAge(makeStringDate(month, dayOfMonth, year))));
            }
        }, year, month, day);
        datePickerDialog.show();

    }

    public int getComputedIntAge(String birthday){
        SimpleDateFormat format = new SimpleDateFormat("MMM-d-yyyy");
        Date birth = null;
        try {
            birth = format.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar bDay = Calendar.getInstance();
        bDay.setTime(birth);

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - bDay.get(Calendar.YEAR);
        if(today.get(Calendar.MONTH) < bDay.get(Calendar.MONTH)){
            age--;
        } else if (today.get(Calendar.MONTH) == bDay.get(Calendar.MONTH)
                && today.get(Calendar.DAY_OF_MONTH) < bDay.get(Calendar.DAY_OF_MONTH)){
            age--;
        }
        return age;
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        gender = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}