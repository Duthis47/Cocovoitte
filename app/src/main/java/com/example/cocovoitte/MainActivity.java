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

import com.example.cocovoitte.Fragment.ConnexionFragment;
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

        //Préparation du premier fragment si nouvel création
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }
        //Preparation du bottomNavView
        bottomNavView = findViewById(R.id.bottom_nav);
        bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //Ici on selectionne et on charge les bons fragments
                Fragment selectedFragment = null;
                //On recupere l'id du bouton cliquer
                int id = item.getItemId();

                //On verifie les id (impossible d'utiliser switch)
                if(id == R.id.menu_home) {
                    //On instancie les fragments
                    selectedFragment = new HomeFragment();
                }else if(id == R.id.menu_user){
                    selectedFragment = new ConnexionFragment();
                }else{

                }

                //On envoie le fragment correspondant
                if (selectedFragment != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                }
                return true;
            }
        });
    }
}