package com.example.cocovoitte.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocovoitte.Classes.Trajet;
import com.example.cocovoitte.Classes.Utilisateur;
import com.example.cocovoitte.R;

public class AssocTrajetUserViewHolder extends RecyclerView.ViewHolder {

    private TextView tvHoraire;
    private TextView tvDepart;
    private TextView tvArrivee;
    private TextView tvNbPlaces;
    private TextView tvTarifs;

    //Préparé pour l'ajout lors de la version WebService (ajout de Glide a voir si temps mais necessiterait BDD externe)
    private ImageView ivPhotoConducteur;
    private TextView tvNomConducteur;

    private LinearLayout llUser;

    private Button btnReserver;

    private int idUserLiee;

    private int idTrajetLiee;

    public AssocTrajetUserViewHolder(@NonNull View itemView) {
        super(itemView);
        tvHoraire = itemView.findViewById(R.id.tv_horaire);
        tvDepart = itemView.findViewById(R.id.tv_depart_value);
        tvArrivee = itemView.findViewById(R.id.tv_arrivee_value);
        tvNbPlaces = itemView.findViewById(R.id.tv_places);
        tvTarifs = itemView.findViewById(R.id.tv_prix);
        ivPhotoConducteur = itemView.findViewById(R.id.iv_driver_photo);
        tvNomConducteur = itemView.findViewById(R.id.tv_driver_name);
        llUser = itemView.findViewById(R.id.ll_user);
        btnReserver = itemView.findViewById(R.id.btn_reserver_item);

        btnReserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On redirige vers la page de détail trajet ( a faire)
            }
        });
        llUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Redirection vers la page détail d'un utilisateur (a faire)
            }
        });
    }

    public void setTxtTvHoraire(String txt) {
        this.tvHoraire.setText(txt) ;
    }

    public void setTxtTvArrivee(String txt) {
        this.tvArrivee.setText(txt);
    }

    public void setTxtTvDepart(String txt) {
        this.tvDepart.setText(txt);
    }
    public void setTxtTvPrix(String txt) {
        this.tvTarifs.setText(txt);
    }
    public void setTxtTvPlaces(String txt) {
        this.tvNbPlaces.setText(txt);
    }
    public void setTxtTvNomConducteur(String txt) {
        this.tvNomConducteur.setText(txt);
    }

    //Pas fonctionnel et ne serait pas implémenter comme ca de toute facon
    public void setImageIvPhotoConducteur(int cheminVersPhoto) {
        this.ivPhotoConducteur.setImageResource(cheminVersPhoto);
    }

    public int getIdUserLiee() {
        return idUserLiee;
    }

    public void setIdUserLiee(int idUserLiee) {
        this.idUserLiee = idUserLiee;
    }

    public int getIdTrajetLiee() {
        return idTrajetLiee;
    }

    public void setIdTrajetLiee(int idTrajetLiee) {
        this.idTrajetLiee = idTrajetLiee;
    }
}
