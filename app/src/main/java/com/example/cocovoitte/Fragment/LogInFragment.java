package com.example.cocovoitte.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cocovoitte.Classes.Utilisateur;
import com.example.cocovoitte.Classes.UtilisateurLocal;
import com.example.cocovoitte.MainActivity;
import com.example.cocovoitte.R;
import com.example.cocovoitte.database.AppDatabase;

public class LogInFragment extends Fragment {

    private AppDatabase db;
    private EditText et_email;
    private EditText et_password;
    private Button btn_submit;
    private TextView tv_error;

    public LogInFragment() {
        // Required empty public constructor
    }

    public static LogInFragment newInstance() {
        LogInFragment fragment = new LogInFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = AppDatabase.getDatabase(requireActivity().getApplicationContext());
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        btn_submit = view.findViewById(R.id.btn_submit);
        tv_error = view.findViewById(R.id.tv_erreur);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = et_password.getText().toString();
                String email = et_email.getText().toString();

                AppDatabase.databaseWriteExecutor.execute(()-> {

                    //On verifie les informations saisies
                    Utilisateur utilisateur = db.utilisateurDAO().getUtilisateurByMailAndPassword(email, password);
                    if (utilisateur != null) {
                        tv_error.setText("");
                        //si l'utilisateur existe, on se connecte en ajoutant l'utilisateur localement
                        db.utilisateurLocalDAO().insert(new UtilisateurLocal(utilisateur));

                        //Je rajoute dans un XML l'id de l'utilisateur local pour simplifier les prochains traitements
                        int idU = utilisateur.getIdU();
                        SharedPreferences prefs = getContext().getSharedPreferences("CocovoittePrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putLong("USER_ID", idU);
                        editor.apply();

                        requireActivity().runOnUiThread(() -> {
                            Intent unIntent = new Intent(requireActivity(), MainActivity.class);
                            //On supprime la pile des activités avant de rediriger vers la page etant donné qu'on ne pourra pas revenir en arriere
                            unIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(unIntent);
                        });
                    } else {
                        tv_error.setText("mail ou mot de passe incorrect");
                    }
                });
            }
        });




    }
}