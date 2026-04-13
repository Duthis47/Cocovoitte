package com.example.cocovoitte;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cocovoitte.Fragment.LogInFragment;
import com.example.cocovoitte.Fragment.SignUpFragment;

public class WelcomeActivity extends AppCompatActivity {

    private Button btn_login_fragment;
    private Button btn_signUp_fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_login_fragment = findViewById(R.id.btn_logIn);
        btn_signUp_fragment = findViewById(R.id.btn_signUp);

        //affichage du fragment LogInFragment lors de l'appui sur le bouton se connecter
        btn_login_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //on change l'aspect des deux boutons
                btn_login_fragment.setTypeface(null, Typeface.BOLD);
                btn_signUp_fragment.setTypeface(null, Typeface.NORMAL);
                //on charge le fragment
                LogInFragment fragment = LogInFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment)
                        .commit();
            }
        });

        //affichage du fragment SignUpFragment lors de l'appui sur le bouton s'inscrire
        btn_signUp_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //on change l'aspect des deux boutons
                btn_login_fragment.setTypeface(null, Typeface.NORMAL);
                btn_signUp_fragment.setTypeface(null, Typeface.BOLD);
                //on charge le fragment
                SignUpFragment fragment = SignUpFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment)
                        .commit();
            }
        });


    }


}