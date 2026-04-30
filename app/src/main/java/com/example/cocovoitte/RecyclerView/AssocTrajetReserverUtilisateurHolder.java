package com.example.cocovoitte.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocovoitte.Classes.Reserver;
import com.example.cocovoitte.R;
import com.example.cocovoitte.database.AppDatabase;

import java.util.Date;
import java.util.UUID;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;


//View Holder pour les recycler view utilisant la classe d'assoc TrajetReserverUtilisateur
public class AssocTrajetReserverUtilisateurHolder extends RecyclerView.ViewHolder{

    private TextView tvHoraire;
    private TextView tvDepart;
    private TextView tvArrivee;
    private TextView tvNomUtilisateur;
    private TextView tvStatut;
    private Button btnRefuser;
    private Button btnAccepter;
    private Button btnGenQR;

    private AppDatabase db;
    private Reserver laResaLiee;
    public AssocTrajetReserverUtilisateurHolder(@NonNull View itemView) {
        super(itemView);
        tvHoraire = itemView.findViewById(R.id.tv_horaire_value);
        tvDepart = itemView.findViewById(R.id.tv_depart_value);
        tvArrivee = itemView.findViewById(R.id.tv_arrivee_value);
        tvNomUtilisateur = itemView.findViewById(R.id.tv_conduct_value);
        btnRefuser = itemView.findViewById(R.id.btn_demande_refuser);
        btnAccepter = itemView.findViewById(R.id.btn_demande_accepter);
        tvStatut = itemView.findViewById(R.id.tv_statut_label);
        btnGenQR = itemView.findViewById(R.id.btn_gen_qrcode);
        db = AppDatabase.getDatabase(itemView.getContext());

        //On supprime de la BDD si on clic sur le bouton refuser
        btnRefuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase.databaseWriteExecutor.execute(() -> {
                    db.reserverDAO().delete(laResaLiee);
                });
            }
        });

        //On change l'état de la reservation en cas d'acceptation
        btnAccepter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On accepte la réservation
                laResaLiee.setEtatAcceptation("Accepté");
                AppDatabase.databaseWriteExecutor.execute(() -> {
                            //On la sauvegarde
                            db.reserverDAO().update(laResaLiee);
                        });
            }
        });

        //CLic sur le bouton de génération de QRCode
        btnGenQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                LayoutInflater inflater = LayoutInflater.from(context);
                View popUpView = inflater.inflate(R.layout.popup_gen_qr, null);

                //On configure la fenêtre
                PopupWindow popupWindow = new PopupWindow(popUpView,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        true);

                //On génère le QRCode
                ImageView qrCodeImg = popUpView.findViewById(R.id.iv_qrcode_gen);

                // On calcule une taille (70% de la largeur de l'écran)
                int width = context.getResources().getDisplayMetrics().widthPixels;
                int qrSize = (int) (width * 0.7);

                try {
                    //On génère l'UUID de la réservation qu'on enregistre dans la BDD
                    laResaLiee.genUuid();
                    String uuid = laResaLiee.getUuid() + "/" + (new Date().getTime());
                    AppDatabase.databaseWriteExecutor.execute(()-> {
                        db.reserverDAO().update(laResaLiee);
                    });

                    //On encode le QRCode afin de l'afficher
                    QRGEncoder qrgEncoder = new QRGEncoder(uuid, null, QRGContents.Type.TEXT, qrSize);
                    qrgEncoder.setColorBlack(Color.WHITE);
                    qrgEncoder.setColorWhite(Color.BLACK);
                    qrCodeImg.setImageBitmap(qrgEncoder.getBitmap());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Fermer avec clic sur bouton
                popUpView.findViewById(R.id.btn_close_popup).setOnClickListener(view -> {
                    popupWindow.dismiss();
                });

                //Fermer avec clic sur le fond
                popUpView.findViewById(R.id.root_popup_qr).setOnClickListener(view -> {
                    popupWindow.dismiss();
                });
                popUpView.setBackgroundColor(Color.WHITE);
                //On affiche le popup du qrcode
                popupWindow.showAtLocation(v, android.view.Gravity.CENTER, 0, 0);
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

    //Fonctions permettant de différencier les 2 recycler view
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

    public void showQR(){
        this.btnGenQR.setVisibility(View.VISIBLE);
    }
    public void hideQR(){
        this.btnGenQR.setVisibility(View.GONE);
    }
    public Reserver getLaResaLiee() {
        return laResaLiee;
    }

    public void setLaResaLiee(Reserver laResaLiée) {
        this.laResaLiee = laResaLiée;
    }
}
