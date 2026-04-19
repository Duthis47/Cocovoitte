package com.example.cocovoitte.RecyclerView;

import com.example.cocovoitte.Classes.Reserver;
import com.example.cocovoitte.Classes.Trajet;
import com.example.cocovoitte.Classes.Utilisateur;
import com.example.cocovoitte.Classes.AssocTrajetReserverUtilisateur;


import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocovoitte.R;

import java.text.SimpleDateFormat;
import java.util.Locale;
public class AssocTrajetReserverUtilisateurRecyclerViewAdapter extends RecyclerView.Adapter<AssocTrajetReserverUtilisateurHolder>{
    private ArrayList<AssocTrajetReserverUtilisateur> lstAssocTrajetUser;
    private boolean isMine;

    public AssocTrajetReserverUtilisateurRecyclerViewAdapter(boolean mine) {
        lstAssocTrajetUser = new ArrayList<>();
        isMine = mine;
    }

    public void setLstTrajet(ArrayList<AssocTrajetReserverUtilisateur> lesAssocs){
        lstAssocTrajetUser = lesAssocs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AssocTrajetReserverUtilisateurHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_view_trajet_element, parent, false);
        return new AssocTrajetReserverUtilisateurHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssocTrajetReserverUtilisateurHolder holder, int position) {
        AssocTrajetReserverUtilisateur uneAssoc = lstAssocTrajetUser.get(position);
        Trajet unTrajet = uneAssoc.getLeTrajet();
        Utilisateur unUser = uneAssoc.getLeUser();
        Reserver laResa = uneAssoc.getLaResa();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'à' HH:mm", Locale.FRANCE);
        String dateFormatee = sdf.format(unTrajet.getDateDebut());
        holder.setTxtTvHoraire(dateFormatee);
        holder.setTxtTvArrivee(unTrajet.getLieuArrive());
        holder.setTxtTvDepart(unTrajet.getLieuDepart());
        holder.setTxtTvNomUtilisateur(unUser.getPrenom() + " " + unUser.getNom());
        holder.setLaResaLiee(laResa);

        if (laResa.isEtatAcceptation()){
            holder.setTxtTvStatut("Accepté");
        }else {
            holder.setTxtTvStatut("En attente");
        }
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
