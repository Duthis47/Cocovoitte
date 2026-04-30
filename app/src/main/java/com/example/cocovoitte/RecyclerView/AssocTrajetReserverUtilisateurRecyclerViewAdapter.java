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

//Adapter liée au Holder de la classe d'assoc TrajetReserverUtilisateur
public class AssocTrajetReserverUtilisateurRecyclerViewAdapter extends RecyclerView.Adapter<AssocTrajetReserverUtilisateurHolder>{
    private ArrayList<AssocTrajetReserverUtilisateur> lstAssocTrajetUser;

    //Permet de différencier les 2 recycler view qui utilise cette classe
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
        //Pour chaque élément, on récupère le trajet, l'utilisateur et la reservation lié
        AssocTrajetReserverUtilisateur uneAssoc = lstAssocTrajetUser.get(position);
        Trajet unTrajet = uneAssoc.getLeTrajet();
        Utilisateur unUser = uneAssoc.getLeUser();
        Reserver laResa = uneAssoc.getLaResa();

        //On gere l'affichage
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'à' HH:mm", Locale.FRANCE);
        String dateFormatee = sdf.format(unTrajet.getDateDebut());
        holder.setTxtTvHoraire(dateFormatee);
        holder.setTxtTvArrivee(unTrajet.getLieuArrive());
        holder.setTxtTvDepart(unTrajet.getLieuDepart());
        holder.setTxtTvNomUtilisateur(unUser.getPrenom() + " " + unUser.getNom());
        holder.setLaResaLiee(laResa);
        holder.setTxtTvStatut(laResa.getEtatAcceptation());

        //On permet le clic sur le QRCode que si la résa à été accepté
        if (laResa.getEtatAcceptation().equals("Accepté")){
            holder.showQR();
        }else {
            holder.hideQR();
        }

        //On différencie selon le recyclerview
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
