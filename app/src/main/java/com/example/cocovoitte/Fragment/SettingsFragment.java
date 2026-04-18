package com.example.cocovoitte.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cocovoitte.Classes.UtilisateurLocal;
import com.example.cocovoitte.MainActivity;
import com.example.cocovoitte.R;
import com.example.cocovoitte.WelcomeActivity;
import com.example.cocovoitte.database.AppDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class SettingsFragment extends Fragment {

    private AppDatabase db;
    private Button btn_disconnect;
    private EditText et_description;
    private Button btn_changeDescription;
    private Button btn_changeProfilePicture; //JSP
    private EditText et_preferences;
    private Button btn_addPreference;
    private UtilisateurLocal user;
    private LinearLayout ll_preferences;

    public SettingsFragment() {
        // Required empty public constructor
    }
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
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
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = AppDatabase.getDatabase(requireActivity().getApplicationContext());
        btn_disconnect = view.findViewById(R.id.btn_disconnect);
        et_description = view.findViewById(R.id.et_description);
        btn_changeDescription = view.findViewById(R.id.btn_changeDescription);
        et_preferences = view.findViewById(R.id.et_addPreference);
        btn_addPreference = view.findViewById(R.id.btn_addPreference);
        ll_preferences = view.findViewById(R.id.ll_preferences);


        db.utilisateurLocalDAO().getLocalUser().observe(getViewLifecycleOwner(),userLocal-> {
            user = userLocal;
            //affiche la description
            et_description.setText(user.getDescription());

            //affiche les preferences
            if (user.getPreferences() != null && !user.getPreferences().isEmpty()) {
                ll_preferences.removeAllViews();
                String[] preferences = user.getPreferences().split(";");
                et_description.setText(user.getPreferences());
                for (String elem : preferences) {
                    LinearLayout unLayoutPref = new LinearLayout(getContext());
                    TextView unePref = new TextView(getContext());
                    unePref.setText(elem);
                    Button unBtnSuppr = new Button(getContext());

                    unLayoutPref.addView(unePref);

                    ll_preferences.addView(unLayoutPref);
                }
            }
        });

        //met à jour la description
        btn_changeDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase.databaseWriteExecutor.execute(()->{
                    int id = user.getIdU();
                    db.utilisateurDAO().updateDescription(id, et_description.getText().toString());
                    db.utilisateurLocalDAO().updateDescription(id, et_description.getText().toString());
                });
            }
        });

        //TOUJOURS DES PROBLEMES AVEC LE STRING PREFERENCES ET LE NULL
        //on ajoute une preference
        btn_addPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!et_preferences.getText().toString().isEmpty()){
                    AppDatabase.databaseWriteExecutor.execute(()->{
                        int id = user.getIdU();
                        String preferences = user.getPreferences();
                        preferences = preferences + ";" + et_preferences.getText().toString();
                        et_preferences.setText("");
                        db.utilisateurLocalDAO().updatePreferences(id, preferences);
                        db.utilisateurDAO().updatePreferences(id, preferences);
                    });
                }
            }
        });

        //deconnecte l'utlisateur en le supprimant localement
        btn_disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppDatabase.databaseWriteExecutor.execute(()->{
                    //suppression de l'utilisateur localement
                    db.utilisateurLocalDAO().deleteAll();

                    //redirection vers la page de bienvenue
                    requireActivity().runOnUiThread(() -> {
                        Intent unIntent = new Intent(requireActivity(), WelcomeActivity.class);
                        //On supprime la pile des activités avant de rediriger vers la page etant donné qu'on ne pourra pas revenir en arriere
                        unIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(unIntent);
                    });
                });
            }
        });

    }
}