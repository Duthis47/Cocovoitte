package com.example.cocovoitte.RecyclerView;

import com.example.cocovoitte.Classes.Trajet;
import com.example.cocovoitte.Classes.Utilisateur;
import com.example.cocovoitte.Classes.AssocTrajetUtilisateur;


import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocovoitte.Classes.Trajet;
import com.example.cocovoitte.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
public class AssocTrajetUtilisateurRecyclerViewAdapter extends RecyclerView.Adapter<AssocTrajetUtilisateurHolder>{
    private ArrayList<AssocTrajetUtilisateur> lstAssocTrajetUser;
    private boolean isMine;

    public AssocTrajetUtilisateurRecyclerViewAdapter(boolean mine) {
        lstAssocTrajetUser = new ArrayList<>();
        isMine = mine;
    }

    public void setLstTrajet(ArrayList<AssocTrajetUtilisateur> lesAssocs){
        lstAssocTrajetUser = lesAssocs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AssocTrajetUtilisateurHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_view_trajet_element, parent, false);
        return new AssocTrajetUtilisateurHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssocTrajetUtilisateurHolder holder, int position) {
        AssocTrajetUtilisateur uneAssoc = lstAssocTrajetUser.get(position);
        Trajet unTrajet = uneAssoc.getLeTrajet();
        Utilisateur unUser = uneAssoc.getLeUser();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'à' HH:mm", Locale.FRANCE);
        String dateFormatee = sdf.format(unTrajet.getDateDebut());
        holder.setTxtTvHoraire(dateFormatee);
        holder.setTxtTvArrivee(unTrajet.getLieuArrive());
        holder.setTxtTvDepart(unTrajet.getLieuDepart());
        holder.setTxtTvNomUtilisateur(unUser.getPrenom() + unUser.getNom());

        if (isMine){
            holder.isMine();
        }else {
            holder.notIsMine();
        }
    }

    @Override
    public int getItemCount() {
        return lstAssocTrajetUser.size();
    }
}
