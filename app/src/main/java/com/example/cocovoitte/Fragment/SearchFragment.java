package com.example.cocovoitte.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocovoitte.Classes.AssocTrajetUtilisateur;
import com.example.cocovoitte.R;
import com.example.cocovoitte.database.AppDatabase;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    //Recuperation des layouts
    private RecyclerView rvResultats;
    private FrameLayout containerSmallSearch;
    private View viewDimmer;
    private FrameLayout containerBigSearch;

    //Recuperation des éléments de la recherche
    private TextView tvDepartSearch;
    private TextView tvArriveSearch;
    private TextView tvDateSearch; //On va y mettre un DatePickerDialog
    private TextView tvNbPassager;
    private Button btnMoinsPassager;
    private Button btnPlusPassager;
    private Button btnSearchSubmit;
    private int nbPassager = 1;

    //Recuepration des éléments de la mini barre de recherche
    private TextView tvSummarySearch;
    private LinearLayout llSmallSearch;
    //Gestion de la BDD
    private AppDatabase db;
    private ArrayList<AssocTrajetUtilisateur> lesTrajetsAAffiches;

    //Variable pour gestion Recherche
    private String villeDepart;
    private String villeArrive;
    private String dateDepart;

    public SearchFragment() {

    }
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvResultats = view.findViewById(R.id.rv_results);
        containerSmallSearch = view.findViewById(R.id.container_small_search);
        containerBigSearch = view.findViewById(R.id.container_big_search);
        viewDimmer = view.findViewById(R.id.view_dimmer);

        //Gestion de la Recherche
        tvDepartSearch = view.findViewById(R.id.tv_departure_search);
        tvArriveSearch = view.findViewById(R.id.tv_arrival_search);
        tvDateSearch = view.findViewById(R.id.tv_date_search);
        tvNbPassager = view.findViewById(R.id.tv_nb_passagers_search);
        btnMoinsPassager = view.findViewById(R.id.btn_minus);
        btnPlusPassager = view.findViewById(R.id.btn_plus);
        btnSearchSubmit = view.findViewById(R.id.btn_search_submit);
        tvSummarySearch = view.findViewById(R.id.tv_search_summary);
        llSmallSearch = view.findViewById(R.id.btn_expand_search);

        btnMoinsPassager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbPassager -= 1;
                updateAffichNbPass();
            }
        });
        btnPlusPassager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbPassager += 1;
                updateAffichNbPass();
            }
        });

        btnSearchSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMode(1);
            }
        });

        //Clic sur searchBar
        llSmallSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMode(2);
            }
        });

        //Clic en dehors de la zone de recherche
        viewDimmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMode(1);
            }
        });

        //On initialise l'affichage
        setMode(0);
    }
    //Gestion des différents affichages
    //0 : Initialisation (fond blanc + rechercher)
    //1 : Passage au Search Bar + Recycler VIew
    //2 : Affichage Voile Noir + rechercher
    public void setMode(int mode){
        switch (mode){
            case 0:
                rvResultats.setVisibility(View.GONE);
                containerSmallSearch.setVisibility(View.GONE);
                containerBigSearch.setVisibility(View.VISIBLE);
                viewDimmer.setVisibility(View.GONE);
                break;
            case 1:
                rvResultats.setVisibility(View.VISIBLE);
                containerSmallSearch.setVisibility(View.VISIBLE);
                containerBigSearch.setVisibility(View.GONE);
                viewDimmer.setVisibility(View.GONE);
                setSmallSearch();
                break;
            case 2 :
                rvResultats.setVisibility(View.VISIBLE);
                containerSmallSearch.setVisibility(View.VISIBLE);
                containerBigSearch.setVisibility(View.VISIBLE);
                viewDimmer.setVisibility(View.VISIBLE);
                setBigSearch();
                break;
        }
    }
    public void updateAffichNbPass(){
        tvNbPassager.setText(String.valueOf(nbPassager));

        btnMoinsPassager.setEnabled(nbPassager > 1);
        btnPlusPassager.setEnabled(nbPassager < 8);
    }

    //Utilisé pour un belle affichage
    public void setSmallSearch(){
        String txt = villeDepart + "→"+ villeArrive + "•" + dateDepart +"•" + String.valueOf(nbPassager);
        tvSummarySearch.setText(txt);
    }

    //Utilisé pour remplir les paramètres par défaut
    public void setBigSearch(){
        tvDepartSearch.setText(villeDepart);
        tvArriveSearch.setText(villeArrive);
        tvDateSearch.setText("");
        updateAffichNbPass();
    }
}
