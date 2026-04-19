package com.example.cocovoitte.RecyclerView;

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
public class TrajetRecyclerViewAdapter extends RecyclerView.Adapter<TrajetViewHolder> {
    private ArrayList<Trajet> lstTrajet;
    public TrajetRecyclerViewAdapter() {
        lstTrajet = new ArrayList<>();
    }

    public void setLstTrajet(ArrayList<Trajet> lesTrajets){
        lstTrajet = lesTrajets;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrajetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trajet_recycler_view_element, parent, false);
        return new TrajetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrajetViewHolder holder, int position) {
        Trajet unTrajet = lstTrajet.get(position);
        holder.setTxtTvArrivee(unTrajet.getLieuArrive());
        holder.setTxtTvDepart(unTrajet.getLieuDepart());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'à' HH:mm", Locale.FRANCE);
        String dateFormatee = sdf.format(unTrajet.getDateDebut());
        holder.setTxtTvHoraire(dateFormatee);
    }

    @Override
    public int getItemCount() {
        return lstTrajet.size();
    }
}
