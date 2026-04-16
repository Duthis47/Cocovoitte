package com.example.cocovoitte.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocovoitte.Classes.Reserver;
import com.example.cocovoitte.R;
import com.example.cocovoitte.database.AppDatabase;

import org.w3c.dom.Text;

public class AssocTrajetUtilisateurHolder extends RecyclerView.ViewHolder{

    private TextView tvHoraire;
    private TextView tvDepart;
    private TextView tvArrivee;
    private TextView tvNomUtilisateur;
    private TextView tvStatut;
    private Button btnRefuser;
    private Button btnAccepter;

    private AppDatabase db;
    private Reserver laResaLiee;
    public AssocTrajetUtilisateurHolder(@NonNull View itemView) {
        super(itemView);
        tvHoraire = itemView.findViewById(R.id.tv_horaire_value);
        tvDepart = itemView.findViewById(R.id.tv_depart_value);
        tvArrivee = itemView.findViewById(R.id.tv_arrivee_value);
        tvNomUtilisateur = itemView.findViewById(R.id.tv_conduct_value);
        btnRefuser = itemView.findViewById(R.id.btn_demande_refuser);
        btnAccepter = itemView.findViewById(R.id.btn_demande_accepter);
        tvStatut = itemView.findViewById(R.id.tv_statut_label);
        db = AppDatabase.getDatabase(itemView.getContext());

        btnRefuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Trouver un moyen de récupérer l'objet lié
                AppDatabase.databaseWriteExecutor.execute(() -> {

                    db.reserverDAO().delete(laResaLiee);
                });
            }
        });

        btnAccepter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On accepte la réservation
                laResaLiee.setEtatAcceptation(true);
                AppDatabase.databaseWriteExecutor.execute(() -> {
                            //On la sauvegarde
                            db.reserverDAO().update(laResaLiee);
                        });
            }
        });
    }

    public void setTxtTvHoraire(String txt) {
        this.tvHoraire.setText(txt) ;
    }
    public void setTxtTvStatut(String txt) {
        this.tvStatut.setText(txt) ;
    }
    public void setTxtTvDepart(String txt) {
        this.tvDepart.setText(txt) ;
    }
    public void setTxtTvArrivee(String txt) {
        this.tvArrivee.setText(txt) ;
    }
    public void setTxtTvNomUtilisateur(String txt) {
        this.tvNomUtilisateur.setText(txt) ;
    }


    public void isMine(){
        this.btnRefuser.setVisibility(View.VISIBLE);
        this.btnAccepter.setVisibility(View.VISIBLE);
        this.tvStatut.setVisibility(View.GONE);
    }
    public void notIsMine(){
        this.btnRefuser.setVisibility(View.GONE);
        this.btnAccepter.setVisibility(View.GONE);
        this.tvStatut.setVisibility(View.VISIBLE);
    }

    public Reserver getLaResaLiee() {
        return laResaLiee;
    }

    public void setLaResaLiee(Reserver laResaLiée) {
        this.laResaLiee = laResaLiée;
    }
}
