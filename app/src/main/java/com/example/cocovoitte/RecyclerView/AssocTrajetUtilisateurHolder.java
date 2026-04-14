package com.example.cocovoitte.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cocovoitte.R;
public class AssocTrajetUtilisateurHolder extends RecyclerView.ViewHolder{

    private TextView tvHoraire;
    private TextView tvDepart;
    private TextView tvArrivee;
    private TextView tvNomUtilisateur;
    private Button btnRefuser;
    private Button btnAccepter;

    public AssocTrajetUtilisateurHolder(@NonNull View itemView) {
        super(itemView);
        tvHoraire = itemView.findViewById(R.id.tv_horaire);
        tvDepart = itemView.findViewById(R.id.tv_depart_value);
        tvArrivee = itemView.findViewById(R.id.tv_arrivee_value);
        tvNomUtilisateur = itemView.findViewById(R.id.tv_conduct_value);

        btnRefuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRefuser.setText("Ca fonctionne");
            }
        });

        btnAccepter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAccepter.setText("Ca OKOKOK");
            }
        });
    }

    public void setTxtTvHoraire(String txt) {
        this.tvHoraire.setText(txt) ;
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
    }
    public void notIsMine(){
        this.btnRefuser.setVisibility(View.INVISIBLE);
        this.btnAccepter.setVisibility(View.INVISIBLE);
    }
}
