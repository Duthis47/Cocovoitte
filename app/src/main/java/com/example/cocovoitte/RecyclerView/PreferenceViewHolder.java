package com.example.cocovoitte.RecyclerView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocovoitte.R;

public class PreferenceViewHolder extends RecyclerView.ViewHolder {

    TextView tv_unePreference;
    ImageButton btn_supprUnePreference;

    public PreferenceViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_unePreference = itemView.findViewById(R.id.tv_unePreference);
        btn_supprUnePreference = itemView.findViewById(R.id.btn_supprUnePreference);
    }
}
