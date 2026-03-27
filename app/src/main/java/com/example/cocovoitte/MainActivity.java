package com.example.cocovoitte;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.cocovoitte.Fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Preparation du bottomNavView
        bottomNavView = findViewById(R.id.bottom_nav);
        bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;
                //On recupere l'id du bouton cliquer
                int id = item.getItemId();

                //On verifie les id (impossible d'utiliser switch)
                if(id == R.id.menu_home) {
                    //selectedFragment = new HomeFragment()
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                }else if(id == R.id.menu_rechercher){
                    Toast.makeText(MainActivity.this, "Rechercher", Toast.LENGTH_SHORT).show();
                }else{

                }
                return true;
            }
        });
    }
}