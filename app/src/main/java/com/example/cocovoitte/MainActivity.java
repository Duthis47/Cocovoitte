package com.example.cocovoitte;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.cocovoitte.Classes.UtilisateurLocal;
import com.example.cocovoitte.Fragment.SettingsFragment;
import com.example.cocovoitte.Fragment.SignUpFragment;
import com.example.cocovoitte.Fragment.HomeFragment;
import com.example.cocovoitte.database.AppDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    UtilisateurLocal localUser = null;
    BottomNavigationView bottomNavView;
    AppDatabase db;
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

        db = AppDatabase.getDatabase(this);
        db.utilisateurLocalDAO().getLocalUser().observe(this, localUser -> {
            localUser = localUser;
        });

        //On verifie si on est connecté
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
                int idButton = item.getItemId();

                //On verifie les id (impossible d'utiliser switch)
                if (idButton == R.id.menu_home) {
                    selectedFragment = new HomeFragment();
                } else if (idButton == R.id.menu_rechercher){
                    Intent change = new Intent(getApplicationContext(), WelcomeActivity.class);
                    startActivity(change);
                } else if (idButton == R.id.menu_proposer) {
                    //
                }else if (idButton == R.id.menu_Profil) {
                    //
                }else if(idButton == R.id.menu_parametres){
                    selectedFragment = new SettingsFragment();
                }
                //On envoie le fragment correspondant
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
                return true;
            }
        });
    }
}