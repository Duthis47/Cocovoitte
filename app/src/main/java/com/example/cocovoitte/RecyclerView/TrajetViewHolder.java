package com.example.cocovoitte.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cocovoitte.R;

import org.w3c.dom.Text;

public class TrajetViewHolder extends RecyclerView.ViewHolder {

    private TextView tvHoraire;
    private TextView tvDepart;
    private TextView tvArrivee;
    private TextView tvNbPlaces;
    private TextView tvTarifs;
    private LinearLayout llUser;
    private Button btnReserver;

    public TrajetViewHolder(@NonNull View itemView) {
        super(itemView);
        tvHoraire = itemView.findViewById(R.id.tv_horaire);
        tvDepart = itemView.findViewById(R.id.tv_depart_value);
        tvArrivee = itemView.findViewById(R.id.tv_arrivee_value);
        tvNbPlaces = itemView.findViewById(R.id.tv_places);
        tvTarifs = itemView.findViewById(R.id.tv_prix);
        llUser = itemView.findViewById(R.id.ll_user);
        btnReserver = itemView.findViewById(R.id.btn_reserver_item);

        //J'enleve le surplus (pour pas avoir a faire 2 layouts)
        tvTarifs.setVisibility(View.GONE);
        tvNbPlaces.setVisibility(View.GONE);
        llUser.setVisibility(View.GONE);
        btnReserver.setVisibility(View.GONE);
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
}
