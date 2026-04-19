package com.example.cocovoitte.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocovoitte.Classes.AssocTrajetReserverUtilisateur;
import com.example.cocovoitte.Classes.AssocTrajetUtilisateur;
import com.example.cocovoitte.R;
import com.example.cocovoitte.RecyclerView.AssocTrajetUserRecyclerViewAdapter;
import com.example.cocovoitte.database.AppDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SearchFragment extends Fragment {
    //Recuperation des layouts
    private RecyclerView rvResultats;
    private FrameLayout containerSmallSearch;
    private View viewDimmer;
    private FrameLayout containerBigSearch;

    //Recuperation des éléments de la recherche
    private EditText etDepartSearch;
    private EditText etArriveSearch;
    private TextView tvDateSearch; //On va y mettre un DatePickerDialog
    private TextView tvNbPassager;
    private Button btnMoinsPassager;
    private Button btnPlusPassager;
    private Button btnSearchSubmit;
    private LinearLayout btnSelectDate;
    private int nbPassager = 1;
    private Calendar calendar = Calendar.getInstance();

    //Recuepration des éléments de la mini barre de recherche
    private TextView tvSummarySearch;
    private LinearLayout llSmallSearch;
    //Gestion de la BDD
    private AppDatabase db;
    private ArrayList<AssocTrajetUtilisateur> lesTrajetsAAffiches;
    private long msInADay =  24 * 60 * 60 * 1000;


    //Variable pour gestion Recherche
    private String villeDepart;
    private String villeArrive;
    private Date dateDepart;

    //Formateur de date
    private SimpleDateFormat displayFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());


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
        db = AppDatabase.getDatabase(view.getContext());
        rvResultats = view.findViewById(R.id.rv_results);
        containerSmallSearch = view.findViewById(R.id.container_small_search);
        containerBigSearch = view.findViewById(R.id.container_big_search);
        viewDimmer = view.findViewById(R.id.view_dimmer);

        //Gestion de la Recherche
        etDepartSearch = view.findViewById(R.id.et_departure_search);
        etArriveSearch = view.findViewById(R.id.et_arrival_search);
        tvDateSearch = view.findViewById(R.id.tv_date_search);
        tvNbPassager = view.findViewById(R.id.tv_nb_passagers_search);
        btnMoinsPassager = view.findViewById(R.id.btn_minus);
        btnPlusPassager = view.findViewById(R.id.btn_plus);
        btnSearchSubmit = view.findViewById(R.id.btn_search_submit);
        tvSummarySearch = view.findViewById(R.id.tv_search_summary);
        llSmallSearch = view.findViewById(R.id.btn_expand_search);
        btnSelectDate = view.findViewById(R.id.btn_select_date);
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
                villeDepart = etDepartSearch.getText().toString();
                villeArrive = etArriveSearch.getText().toString();
                if (villeDepart.isEmpty() || villeArrive.isEmpty() || dateDepart == null) {
                    //On affiche un pop up pour forcer le bon remplissage
                    Toast.makeText(getContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }
                rvResultats.setLayoutManager(new LinearLayoutManager(view.getContext()));
                AssocTrajetUserRecyclerViewAdapter adapter = new AssocTrajetUserRecyclerViewAdapter();
                rvResultats.setAdapter(adapter);
                Log.d("testBDDReq", villeDepart + "-" + villeArrive + "-" +nbPassager +"-"+dateDepart.getTime());
                Date dateSuivante = new Date(dateDepart.getTime() + msInADay);
                db.trajetDAO().getTrajetRecherche(villeDepart, villeArrive, nbPassager, dateDepart, dateSuivante).observe(getViewLifecycleOwner(), lstTrajet ->{
                    lesTrajetsAAffiches = new ArrayList<>(lstTrajet);
                    adapter.setLstAssocTrajetUser(lesTrajetsAAffiches);
                    adapter.notifyDataSetChanged();
                    Log.d("testBDDRes", lesTrajetsAAffiches.toString());
                });
                setMode(1);
            }
        });

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar temp = Calendar.getInstance();
                        temp.set(year, month, dayOfMonth, 0, 0, 0);
                        temp.set(Calendar.MILLISECOND, 0);

                        dateDepart = temp.getTime();
                        tvDateSearch.setText(displayFormat.format(dateDepart));
                    }
                }, year, month , day);
                datePicker.show();
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
        String dateDepartChaine = displayFormat.format(dateDepart);
        String txt = villeDepart + " → "+ villeArrive + " • " + dateDepartChaine +" • " + String.valueOf(nbPassager);
        tvSummarySearch.setText(txt);
    }

    //Utilisé pour remplir les paramètres par défaut
    public void setBigSearch(){
        etDepartSearch.setText(villeDepart);
        etArriveSearch.setText(villeArrive);

        if (dateDepart != null) {
            tvDateSearch.setText(displayFormat.format(dateDepart));
        }
        updateAffichNbPass();
    }
}
