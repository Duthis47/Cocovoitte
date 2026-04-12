package com.example.cocovoitte.RecyclerView;

import android.view.View;
import android.widget.Button;
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


    public TrajetViewHolder(@NonNull View itemView) {
        super(itemView);
        tvHoraire = itemView.findViewById(R.id.tv_horaire);
        tvDepart = itemView.findViewById(R.id.tv_depart_value);
        tvArrivee = itemView.findViewById(R.id.tv_arrivee_value);
        tvNbPlaces = itemView.findViewById(R.id.tv_places);
        tvTarifs = itemView.findViewById(R.id.tv_prix);
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
    public void InSearch(){
        this.tvTarifs.setVisibility(View.VISIBLE);
        this.tvNbPlaces.setVisibility(View.VISIBLE);
    }
    public void notInSearch(){
        this.tvTarifs.setVisibility(View.INVISIBLE);
        this.tvNbPlaces.setVisibility(View.INVISIBLE);
    }
}
