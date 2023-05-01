package com.delts.shipitfixit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.delts.shipitfixit.auth.Auth;
import com.delts.shipitfixit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Auth auth = new Auth(getApplicationContext());

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        binding.toolbar2.setNavigationIcon(R.drawable.ic_menu);
        binding.toolbar2.setTitle("");
        binding.toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavController navController = Navigation.findNavController(this, R.id.navigationController);
        NavigationUI.setupWithNavController(binding.navigationView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                binding.toolbar2.setTitle(navDestination.getLabel());
            }
        });
    }
    //Used to clear shared preference and logout the application
    public void Logout(MenuItem item){
        Auth auth = new Auth(getApplicationContext());
        auth.logout();
        Intent intent = new Intent(getApplicationContext(), SignIn.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Successfully logged out", Toast.LENGTH_SHORT).show();
    }
}