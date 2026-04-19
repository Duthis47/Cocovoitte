package com.example.cocovoitte.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocovoitte.Classes.AssocTrajetUtilisateur;
import com.example.cocovoitte.Classes.Trajet;
import com.example.cocovoitte.Classes.Utilisateur;
import com.example.cocovoitte.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AssocTrajetUserRecyclerViewAdapter extends RecyclerView.Adapter<AssocTrajetUserViewHolder> {
    private ArrayList<AssocTrajetUtilisateur> lstAssocTrajetUser;
    public AssocTrajetUserRecyclerViewAdapter() {
        lstAssocTrajetUser = new ArrayList<>();
    }

    public void setLstAssocTrajetUser(ArrayList<AssocTrajetUtilisateur> lesTrajets){
        lstAssocTrajetUser = lesTrajets;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AssocTrajetUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trajet_recycler_view_element, parent, false);
        return new AssocTrajetUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssocTrajetUserViewHolder holder, int position) {
        AssocTrajetUtilisateur uneAssoc = lstAssocTrajetUser.get(position);
        Trajet unTrajet = uneAssoc.getLeTrajet();
        Utilisateur unUser = uneAssoc.getLeUser();

        holder.setTxtTvArrivee(unTrajet.getLieuArrive());
        holder.setTxtTvDepart(unTrajet.getLieuDepart());
        holder.setTxtTvPrix(String.valueOf(unTrajet.getTarif()) + " €");
        holder.setTxtTvPlaces(String.valueOf(unTrajet.getNbPassagerP() - uneAssoc.getNbPlacePrise())+ " place(s) restante(s)");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'à' HH:mm", Locale.FRANCE);
        String dateFormatee = sdf.format(unTrajet.getDateDebut());
        holder.setTxtTvHoraire(dateFormatee);

        String nom = unUser.getPrenom() + " " + unUser.getNom();
        holder.setTxtTvNomConducteur(nom);

        holder.setIdUserLiee(unUser.getIdU());
        holder.setIdTrajetLiee(unTrajet.getIdT());
    }

    @Override
    public int getItemCount() {
        return lstAssocTrajetUser.size();
    }
}
