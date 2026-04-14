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

import com.example.cocovoitte.MainActivity;
import com.example.cocovoitte.R;
import com.example.cocovoitte.WelcomeActivity;
import com.example.cocovoitte.database.AppDatabase;

public class SettingsFragment extends Fragment {

    private Button btn_disconnect;
    private AppDatabase db;

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