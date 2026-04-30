package com.example.cocovoitte.RecyclerView;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocovoitte.Classes.UtilisateurLocal;
import com.example.cocovoitte.R;
import com.example.cocovoitte.database.AppDatabase;
import com.example.cocovoitte.database.Converters;

import java.util.ArrayList;
import java.util.List;

//Adapter liée au Holder de la classe Preference
public class ListePreferencesViewAdapter extends RecyclerView.Adapter<PreferenceViewHolder> {

    public AppDatabase db;
    public Context context;
    public ArrayList<String> preferences;
    public UtilisateurLocal user;

    public ListePreferencesViewAdapter(Context context, ArrayList<String> preferences, UtilisateurLocal userLocal) {
        this.context = context;
        this.preferences = preferences;
        db = AppDatabase.getDatabase(context);
        user = userLocal;
    }

    @NonNull
    @Override
    public PreferenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PreferenceViewHolder(LayoutInflater.from(context).inflate(R.layout.preferences_recycler_view_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PreferenceViewHolder holder, int position) {
        //On gere chaque préférence une par une
        holder.tv_unePreference.setText(preferences.get(position));
        holder.btn_supprUnePreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //On remove de la liste et on prévient les adapter
                preferences.remove(position);
                notifyItemRemoved(position);
                AppDatabase.databaseWriteExecutor.execute(()->{
                    String prefString = Converters.fromStringList(preferences);
                    db.utilisateurLocalDAO().updatePreferences(user.getIdU(),prefString);
                });
                Toast.makeText(context, "preference supprimée", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return preferences.size();
    }
}
