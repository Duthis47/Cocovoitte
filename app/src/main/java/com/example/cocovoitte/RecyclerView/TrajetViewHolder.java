package com.example.cocovoitte.RecyclerView;

import static android.provider.Settings.System.getString;

import android.content.Context;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocovoitte.Fragment.HomeFragment;
import com.example.cocovoitte.MainActivity;
import com.example.cocovoitte.R;
import com.google.android.material.button.MaterialButton;
import com.google.zxing.integration.android.IntentIntegrator;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

//View Holder pour les recycler view utilisant la classe Trajet
public class TrajetViewHolder extends RecyclerView.ViewHolder {
    private TextView tvHoraire;
    private TextView tvDepart;
    private TextView tvArrivee;
    private TextView tvNbPlaces;
    private TextView tvTarifs;
    private LinearLayout llUser;
    private Button btnReserver;
    private MaterialButton btnScanQR;

    public TrajetViewHolder(@NonNull View itemView, Fragment leFr) {
        super(itemView);
        tvHoraire = itemView.findViewById(R.id.tv_horaire);
        tvDepart = itemView.findViewById(R.id.tv_depart_value);
        tvArrivee = itemView.findViewById(R.id.tv_arrivee_value);
        tvNbPlaces = itemView.findViewById(R.id.tv_places);
        tvTarifs = itemView.findViewById(R.id.tv_prix);
        llUser = itemView.findViewById(R.id.ll_user);
        btnReserver = itemView.findViewById(R.id.btn_reserver_item);
        btnScanQR = itemView.findViewById(R.id.btn_scan_qrcode);

        //J'enleve le surplus (pour pas avoir a faire 2 layouts)
        tvTarifs.setVisibility(View.GONE);
        tvNbPlaces.setVisibility(View.GONE);
        llUser.setVisibility(View.GONE);
        btnReserver.setVisibility(View.GONE);

        btnScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Je lance le Scanner de QRCode
                Log.d("ok3", (v.getContext() instanceof MainActivity)+"");
                IntentIntegrator integrator = IntentIntegrator.forSupportFragment(leFr);
                integrator.setOrientationLocked(true);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("Scan QR Code");
                integrator.initiateScan();
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

    public void allPassengerHere(boolean yes){
        Context context = itemView.getContext();
        this.btnScanQR.setEnabled(!yes);
        if (yes) {
            this.btnScanQR.setText(context.getString(R.string.txt_everyoneHere));
            this.btnScanQR.setIconResource(R.drawable.outline_directions_car_24);
        }else {
            this.btnScanQR.setText(R.string.txt_scanQRCode);
            this.btnScanQR.setIconResource(R.drawable.outline_photo_camera_24);
        }
    }
}
