package com.example.cocovoitte.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cocovoitte.Classes.AssocTrajetUtilisateur;
import com.example.cocovoitte.Classes.Trajet;
import com.example.cocovoitte.Classes.UtilisateurLocal;
import com.example.cocovoitte.R;
import com.example.cocovoitte.RecyclerView.TrajetRecyclerViewAdapter;
import com.example.cocovoitte.database.AppDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // TODO: Rename and change types of parameters

    private TextView welcomeTxt;
    private AppDatabase db;
    private UtilisateurLocal localUser;
    private RecyclerView rvDriveProp;
    private ArrayList<Trajet> lesTrajetsProposes;
    private RecyclerView rvDriveT;
    private ArrayList<AssocTrajetUtilisateur> lesTrajetsAPrendre;

    private RecyclerView rvDriveV;
    private ArrayList<AssocTrajetUtilisateur> lesTrajetsValides;


    public HomeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        db = AppDatabase.getDatabase(view.getContext());

        //On prepare les trajets proposées (je suis conducteur et je vois les trajets que je vais faire)
        rvDriveProp = view.findViewById(R.id.rv_driveProp);
        rvDriveProp.setLayoutManager(new LinearLayoutManager(view.getContext()));
        TrajetRecyclerViewAdapter adapterR = new TrajetRecyclerViewAdapter(false);
        rvDriveProp.setAdapter(adapterR);

        //On prépare les trajets a prendre (je suis passager et je vois les trajets ou je serais passager)
        rvDriveT = view.findViewById(R.id.rv_driveT);

        //On prépare les demandes a validé (je suis conducteur et je veux accepter des passagers)
        rvDriveV = view.findViewById(R.id.rv_driveV);

        db.utilisateurLocalDAO().getLocalUser().observe(getViewLifecycleOwner(), userLocal -> {
            localUser = userLocal;
            String prenomUser = "";
            if (localUser != null){
                prenomUser="Bienvenue " +  localUser.getPrenom();
                db.trajetDAO().getTrajetByIdU(localUser.getIdU()).observe(getViewLifecycleOwner(), lesTrajetsProp -> {
                    lesTrajetsProposes =  new ArrayList<>(lesTrajetsProp);
                    adapterR.setLstTrajet(lesTrajetsProposes);
                    adapterR.notifyDataSetChanged();
                });
                db.trajetDAO().getTrajetAPrendre(localUser.getIdU()).observe(getViewLifecycleOwner(), lesTrajetsResa -> {
                    lesTrajetsAPrendre =  new ArrayList<>(lesTrajetsResa);
                });
                db.trajetDAO().getTrajetAValider(localUser.getIdU()).observe(getViewLifecycleOwner(), lesTrajetsVal -> {
                    lesTrajetsValides =  new ArrayList<>(lesTrajetsVal);
                });




            }else {
                prenomUser="Bienvenue Guest";
            }
            welcomeTxt = view.findViewById(R.id.tv_welcome);
            welcomeTxt.setText(prenomUser);
        });
    }
}