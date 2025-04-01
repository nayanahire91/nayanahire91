package com.example.krushi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Activity 1");
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bottom_nevi), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        // ✅ Bottom Navigation View Setup
        bottomNavigationView = findViewById(R.id.bottom_nevi);

        // ✅ Default fragment लोड करतो (Home Fragment)
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        // ✅ Bottom Navigation Item Click Listener (Fragments Load करतो)
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.home) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.news) {
                selectedFragment = new NewsFragment();
            } else if (item.getItemId() == R.id.weather) {
                selectedFragment = new WeatherFragment();
            } else if (item.getItemId() == R.id.price) {
                selectedFragment = new PriceFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }

            return true;
        });
    }

    // ✅ Fragment Load करण्यासाठी Helper Method
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }


    public void openMain2(View view) {
        Toast.makeText(this, "Button Clicked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user:
                Toast.makeText(this, "User ", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cam:
                Toast.makeText(this, "Camera ", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.setting:
                Toast.makeText(this, "Settings ", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
