package com.example.cocovoitte.RecyclerView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cocovoitte.R;

import org.w3c.dom.Text;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class TrajetViewHolder extends RecyclerView.ViewHolder {

    private TextView tvHoraire;
    private TextView tvDepart;
    private TextView tvArrivee;
    private TextView tvNbPlaces;
    private TextView tvTarifs;
    private LinearLayout llUser;
    private Button btnReserver;
    private Button btnGenQR;

    public TrajetViewHolder(@NonNull View itemView) {
        super(itemView);
        tvHoraire = itemView.findViewById(R.id.tv_horaire);
        tvDepart = itemView.findViewById(R.id.tv_depart_value);
        tvArrivee = itemView.findViewById(R.id.tv_arrivee_value);
        tvNbPlaces = itemView.findViewById(R.id.tv_places);
        tvTarifs = itemView.findViewById(R.id.tv_prix);
        llUser = itemView.findViewById(R.id.ll_user);
        btnReserver = itemView.findViewById(R.id.btn_reserver_item);
        btnGenQR = itemView.findViewById(R.id.btn_gen_qrcode);

        //J'enleve le surplus (pour pas avoir a faire 2 layouts)
        tvTarifs.setVisibility(View.GONE);
        tvNbPlaces.setVisibility(View.GONE);
        llUser.setVisibility(View.GONE);
        btnReserver.setVisibility(View.GONE);

        btnGenQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                LayoutInflater inflater = LayoutInflater.from(context);
                View popUpView = inflater.inflate(R.layout.popup_gen_qr, null);

                // 1. Configurer la fenêtre (MATCH_PARENT pour l'assombrissement)
                PopupWindow popupWindow = new PopupWindow(popUpView,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        true);

                // 2. Générer le QR Code
                ImageView qrCodeImg = popUpView.findViewById(R.id.iv_qrcode_gen);
                // On calcule une taille (ex: 80% de la largeur de l'écran)
                int width = context.getResources().getDisplayMetrics().widthPixels;
                int qrSize = (int) (width * 0.7);

                try {
                    QRGEncoder qrgEncoder = new QRGEncoder("DONNEES_A_CODER", null, QRGContents.Type.TEXT, qrSize);
                    qrCodeImg.setImageBitmap(qrgEncoder.getBitmap());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // 3. Gérer le bouton Fermer
                popUpView.findViewById(R.id.btn_close_popup).setOnClickListener(view -> {
                    popupWindow.dismiss();
                });

                // 4. Optionnel : Fermer si on clique sur le fond noir
                popUpView.findViewById(R.id.root_popup_qr).setOnClickListener(view -> {
                    popupWindow.dismiss();
                });

                // 5. Afficher la popup
                popupWindow.showAtLocation(v, android.view.Gravity.CENTER, 0, 0);
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
}
