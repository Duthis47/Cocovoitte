package com.example.cocovoitte.Fragment;

import static java.lang.Long.parseLong;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocovoitte.Classes.AssocTrajetReserverUtilisateur;
import com.example.cocovoitte.Classes.Reserver;
import com.example.cocovoitte.Classes.Trajet;
import com.example.cocovoitte.Classes.UtilisateurLocal;
import com.example.cocovoitte.R;
import com.example.cocovoitte.RecyclerView.AssocTrajetReserverUtilisateurRecyclerViewAdapter;
import com.example.cocovoitte.RecyclerView.TrajetRecyclerViewAdapter;
import com.example.cocovoitte.database.AppDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Date;

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
        TrajetRecyclerViewAdapter adapterR = new TrajetRecyclerViewAdapter(this);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // Vérifie si le résultat n'est pas vide
        if (intentResult != null) {
            // Récupère le contenu du QR code
            String contents = intentResult.getContents();

            // Si le contenu existe
            if (contents != null) {
                // Gestion du contents
                Log.d("ok3",  contents);
                String[] splitContents = contents.split("/");
                String uuid = splitContents[0];
                String timestamp = splitContents[1];
                Long timestampUUID= Long.parseLong(timestamp);
                Long timestampActuel = System.currentTimeMillis();

                //On verifie que ca fait pas plus de 5 minutes
                if (timestampUUID + 300000 >= timestampActuel){
                    AppDatabase.databaseWriteExecutor.execute(() -> {
                        Reserver laResa = db.reserverDAO().getResaByUUID(uuid);

                        if (laResa != null){
                            //On met a jour la reservation
                            laResa.setEtatAcceptation("Confirmé");
                            db.reserverDAO().update(laResa);
                            getActivity().runOnUiThread(() -> {
                                Toast.makeText(getContext(), "Trajet validé !", Toast.LENGTH_SHORT).show();
                            });
                        }else {
                            getActivity().runOnUiThread(() -> {
                                Toast.makeText(getContext(), " Pas de resa correspondantes", Toast.LENGTH_SHORT).show();
                            });
                        }
                    });
                }else {
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), "Delai trop long, regenerez le QR COde", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        }

        //On est obligé d'appeler la fonction parente
        super.onActivityResult(requestCode, resultCode, data);
    }
}