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

import com.example.cocovoitte.Classes.AssocTrajetReserverUtilisateur;
import com.example.cocovoitte.Classes.Trajet;
import com.example.cocovoitte.Classes.UtilisateurLocal;
import com.example.cocovoitte.R;
import com.example.cocovoitte.RecyclerView.AssocTrajetReserverUtilisateurRecyclerViewAdapter;
import com.example.cocovoitte.RecyclerView.TrajetRecyclerViewAdapter;
import com.example.cocovoitte.database.AppDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private TextView welcomeTxt;
    private AppDatabase db;
    private UtilisateurLocal localUser;
    private RecyclerView rvDriveProp;
    private ArrayList<Trajet> lesTrajetsProposes;
    private RecyclerView rvDriveT;
    private ArrayList<AssocTrajetReserverUtilisateur> lesTrajetsAPrendre;
    private RecyclerView rvDriveV;
    private ArrayList<AssocTrajetReserverUtilisateur> lesTrajetsValides;


    public HomeFragment() {
        // Required empty public constructor
    }

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

        //On prepare les trajets proposés (je suis conducteur et je vois les trajets que je vais faire)
        rvDriveProp = view.findViewById(R.id.rv_driveProp);
        rvDriveProp.setLayoutManager(new LinearLayoutManager(view.getContext()));
        TrajetRecyclerViewAdapter adapterR = new TrajetRecyclerViewAdapter();
        rvDriveProp.setAdapter(adapterR);

        //On prépare les trajets a prendre (je suis passager et je vois les trajets ou je serai passager)
        rvDriveT = view.findViewById(R.id.rv_driveT);
        rvDriveT.setLayoutManager(new LinearLayoutManager(view.getContext()));
        AssocTrajetReserverUtilisateurRecyclerViewAdapter adapterProp = new AssocTrajetReserverUtilisateurRecyclerViewAdapter(false);
        rvDriveT.setAdapter(adapterProp);

        //On prépare les demandes a valider (je suis conducteur et je veux accepter des passagers)
        rvDriveV = view.findViewById(R.id.rv_driveV);
        rvDriveV.setLayoutManager(new LinearLayoutManager(view.getContext()));
        AssocTrajetReserverUtilisateurRecyclerViewAdapter adapterV = new AssocTrajetReserverUtilisateurRecyclerViewAdapter(true);
        rvDriveV.setAdapter(adapterV);

        welcomeTxt = view.findViewById(R.id.tv_welcome);



        db.utilisateurLocalDAO().getLocalUser().observe(getViewLifecycleOwner(), userLocal -> {
            localUser = userLocal;
            String prenomUser = "";
            if (localUser != null){
                prenomUser= getString(R.string.tv_bienvenue) + " " +  localUser.getPrenom();
                db.trajetDAO().getTrajetByIdU(localUser.getIdU()).observe(getViewLifecycleOwner(), lesTrajetsProp -> {
                    lesTrajetsProposes =  new ArrayList<>(lesTrajetsProp);
                    adapterR.setLstTrajet(lesTrajetsProposes);
                    adapterR.notifyDataSetChanged();
                });
                db.trajetDAO().getTrajetAPrendre(localUser.getIdU()).observe(getViewLifecycleOwner(), lesTrajetsResa -> {
                    lesTrajetsAPrendre =  new ArrayList<>(lesTrajetsResa);
                    adapterProp.setLstTrajet(new ArrayList<>(lesTrajetsResa));
                    adapterProp.notifyDataSetChanged();
                });
                db.trajetDAO().getTrajetAValider(localUser.getIdU()).observe(getViewLifecycleOwner(), lesTrajetsVal -> {
                    lesTrajetsValides =  new ArrayList<>(lesTrajetsVal);
                    adapterV.setLstTrajet(new ArrayList<>(lesTrajetsVal));
                    adapterV.notifyDataSetChanged();
                });




            }else {
                prenomUser="Bienvenue Guest";
            }
            welcomeTxt.setText(prenomUser);
        });
    }

}