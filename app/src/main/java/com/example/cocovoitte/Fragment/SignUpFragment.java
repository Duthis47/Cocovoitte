package com.example.cocovoitte.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cocovoitte.Classes.Utilisateur;
import com.example.cocovoitte.Classes.UtilisateurLocal;
import com.example.cocovoitte.MainActivity;
import com.example.cocovoitte.database.AppDatabase;
import com.example.cocovoitte.R;


import java.util.Objects;


public class SignUpFragment extends Fragment {

    private AppDatabase db;
    private EditText et_firstName;
    private EditText et_lastName;
    private EditText et_email;
    private EditText et_password;
    private Button btn_submit;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        db = AppDatabase.getDatabase(requireActivity().getApplicationContext());
        et_firstName = view.findViewById(R.id.et_firstName);
        et_lastName = view.findViewById(R.id.et_lastName);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        btn_submit = view.findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //on recupere les informations saisies
                String firstName = et_firstName.getText().toString();
                String lastName = et_lastName.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                //on enregistre l'utilisateur dans la bd
                Utilisateur nouvelUtilisateur = new Utilisateur(lastName, firstName, email, password);
                UtilisateurLocal nouvelUtilisateurLocal = new UtilisateurLocal(nouvelUtilisateur);
                AppDatabase.databaseWriteExecutor.execute(() -> {
                    db.utilisateurDAO().insert(nouvelUtilisateur);
                    // On enregistre l'utilisateur localement pour qu'il reste connecté
                    db.utilisateurLocalDAO().insert(nouvelUtilisateurLocal);
                    // On redirige vers la page d'accueil
                    requireActivity().runOnUiThread(() -> {
                        Intent unIntent = new Intent(requireActivity(), MainActivity.class);
                        //On supprime la pile des activités avant de rediriger vers la page etant donné qu'on ne pourra pas revenir en arriere
                        unIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(unIntent);
                    });
                });
            }
        });

    }
}