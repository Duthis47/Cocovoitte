package com.example.cocovoitte.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocovoitte.Classes.UtilisateurLocal;
import com.example.cocovoitte.R;
import com.example.cocovoitte.database.AppDatabase;

import java.util.List;

public class ListePreferencesViewAdapter extends RecyclerView.Adapter<PreferenceViewHolder> {

    public AppDatabase db;
    public Context context;
    public List<String> preferences;
    public UtilisateurLocal user;

    public ListePreferencesViewAdapter(Context context, List<String> preferences, UtilisateurLocal userLocal) {
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
        holder.tv_unePreference.setText(preferences.get(position));
        holder.btn_supprUnePreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.remove(position);
                notifyItemRemoved(position);
                AppDatabase.databaseWriteExecutor.execute(()->{
                    db.utilisateurLocalDAO().updatePreferences(user.getIdU(),preferences);
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return preferences.size();
    }
}
