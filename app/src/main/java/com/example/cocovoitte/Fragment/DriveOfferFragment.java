package com.example.cocovoitte.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cocovoitte.R;

import java.util.ArrayList;
import java.util.List;


public class DriveOfferFragment extends Fragment {

    private EditText et_lieuDepart;
    private EditText et_lieuArrivee;
    private TextView tv_choixDateDepart;
    private TextView tv_choixHeureDepart;
    private Spinner s_nbPassagers;
    private Spinner s_trajetRegulier;
    private Spinner s_frequence;
    private Button btn_lundi, btn_mardi, btn_mercredi, btn_jeudi, btn_vendredi, btn_samedi, btn_dimanche;

    private Button btn_publier;


    public DriveOfferFragment() {
        // Required empty public constructor
    }

    public static DriveOfferFragment newInstance(String param1, String param2) {
        DriveOfferFragment fragment = new DriveOfferFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drive_offer, container, false);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {


        et_lieuDepart = view.findViewById(R.id.et_lieuDepart);
        et_lieuArrivee = view.findViewById(R.id.et_lieuArrivee);

        tv_choixDateDepart = view.findViewById(R.id.tv_choixDateDepart);
        tv_choixHeureDepart = view.findViewById(R.id.tv_choixHeureDepart);

        s_nbPassagers = view.findViewById(R.id.s_nbPassagers);
        s_trajetRegulier = view.findViewById(R.id.s_trajetRegulier);

        btn_lundi = view.findViewById(R.id.btn_lundi);
        btn_mardi = view.findViewById(R.id.btn_mardi);
        btn_mercredi = view.findViewById(R.id.btn_mercredi);
        btn_jeudi = view.findViewById(R.id.btn_jeudi);
        btn_vendredi = view.findViewById(R.id.btn_vendredi);
        btn_samedi = view.findViewById(R.id.btn_samedi);
        btn_dimanche = view.findViewById(R.id.btn_dimanche);

        btn_publier = view.findViewById(R.id.btn_publier);



        btn_publier.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {



            }
        });



        /*
        ArrayList<String> getSelectedDays() {
            List<String> selectedDays = new ArrayList<>();

            if (btn_lundi.isChecked()) selectedDays.add("Lundi");
            if (binding.btnMardi.isChecked()) selectedDays.add("Mardi");
            // ... etc pour tous les boutons

            return selectedDays;
        }

*/

    }

}