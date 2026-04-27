package com.example.cocovoitte.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Trace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.cocovoitte.Classes.Trajet;
import com.example.cocovoitte.Classes.UtilisateurLocal;
import com.example.cocovoitte.R;
import com.example.cocovoitte.database.AppDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DriveOfferFragment extends Fragment {

    private AppDatabase db;
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat displayFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private SimpleDateFormat displayFormatHours = new SimpleDateFormat("hh mm", Locale.getDefault());
    private EditText et_lieuDepart, et_lieuArrivee, et_tarif;
    private TextView tv_choixDateDepart, tv_choixHeureDepart;
    private Spinner s_nbPassagers;
    private Spinner s_trajetRegulier;
    private Spinner s_frequence;
    private ToggleButton btn_lundi, btn_mardi, btn_mercredi, btn_jeudi, btn_vendredi, btn_samedi, btn_dimanche;

    private Button btn_publier;
    private LinearLayout btn_choixDateDepart, btn_choixHeureDepart;
    private CardView cvFreqH;
    private UtilisateurLocal user;

    private Date dateDepart;


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



        db = AppDatabase.getDatabase(getContext());
        et_lieuDepart = view.findViewById(R.id.et_lieuDepart);
        et_lieuArrivee = view.findViewById(R.id.et_lieuArrivee);

        tv_choixDateDepart = view.findViewById(R.id.tv_choixDateDepart);
        tv_choixHeureDepart = view.findViewById(R.id.tv_choixHeureDepart);
        et_tarif = view.findViewById(R.id.et_tarif);

        s_nbPassagers = view.findViewById(R.id.s_nbPassagers);
        s_trajetRegulier = view.findViewById(R.id.s_trajetRegulier);

        cvFreqH = view.findViewById(R.id.cv_freqH);
        btn_lundi = view.findViewById(R.id.btn_lundi);
        btn_mardi = view.findViewById(R.id.btn_mardi);
        btn_mercredi = view.findViewById(R.id.btn_mercredi);
        btn_jeudi = view.findViewById(R.id.btn_jeudi);
        btn_vendredi = view.findViewById(R.id.btn_vendredi);
        btn_samedi = view.findViewById(R.id.btn_samedi);
        btn_dimanche = view.findViewById(R.id.btn_dimanche);

        btn_publier = view.findViewById(R.id.btn_publier);

        btn_choixDateDepart = view.findViewById(R.id.btn_choixDateDepart);
        btn_choixHeureDepart = view.findViewById(R.id.btn_choixHeureDepart);

        dateDepart = new Date();

        db.utilisateurLocalDAO().getLocalUser().observe(getViewLifecycleOwner(), utilisateurLocal->{
            user = utilisateurLocal;
        });


        btn_lundi.setChecked(false);
        btn_mardi.setChecked(false);
        btn_mercredi.setChecked(false);
        btn_jeudi.setChecked(false);
        btn_vendredi.setChecked(false);
        btn_samedi.setChecked(false);
        btn_dimanche.setChecked(false);


        ArrayList nbPassager = new ArrayList<>();
        nbPassager.add(1);
        nbPassager.add(2);
        nbPassager.add(3);
        nbPassager.add(4);
        nbPassager.add(5);
        nbPassager.add(6);

        ArrayAdapter nbPassagerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
        for (Object elem : nbPassager){
            nbPassagerAdapter.add((int) elem);
        }
        s_nbPassagers.setAdapter(nbPassagerAdapter);

        ArrayList<String> estTrajetRegulier = new ArrayList<String>();
        estTrajetRegulier.add("Oui");
        estTrajetRegulier.add("Non");

        ArrayAdapter<String> estTrajetRegulierAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item);
        for (String elem : estTrajetRegulier){
            estTrajetRegulierAdapter.add(elem);
        }
        s_trajetRegulier.setAdapter(estTrajetRegulierAdapter);



        CompoundButton.OnCheckedChangeListener dayListener = new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonView.setBackgroundColor(0xFF3F51B5); // Bleu
                    buttonView.setTextColor(0xFFFFFFFF);      // Blanc
                    buttonView.setTypeface(null, Typeface.BOLD);
                } else {
                    buttonView.setBackgroundColor(0xFFE0E0E0); // Gris
                    buttonView.setTextColor(0xFF000000);      // Noir
                    buttonView.setTypeface(null, Typeface.NORMAL);
                }
            }
        };

        btn_lundi.setOnCheckedChangeListener(dayListener);
        btn_mardi.setOnCheckedChangeListener(dayListener);
        btn_mercredi.setOnCheckedChangeListener(dayListener);
        btn_jeudi.setOnCheckedChangeListener(dayListener);
        btn_vendredi.setOnCheckedChangeListener(dayListener);
        btn_samedi.setOnCheckedChangeListener(dayListener);
        btn_dimanche.setOnCheckedChangeListener(dayListener);


        s_trajetRegulier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Oui")) {
                    cvFreqH.setVisibility(View.VISIBLE);
                    /*btn_lundi.setVisibility(View.VISIBLE);
                    btn_mardi.setVisibility(View.VISIBLE);
                    btn_mercredi.setVisibility(View.VISIBLE);
                    btn_jeudi.setVisibility(View.VISIBLE);
                    btn_vendredi.setVisibility(View.VISIBLE);
                    btn_samedi.setVisibility(View.VISIBLE);
                    btn_dimanche.setVisibility(View.VISIBLE);*/
                } else {
                    cvFreqH.setVisibility(View.GONE);
                   /* btn_lundi.setVisibility(View.GONE);
                    btn_mardi.setVisibility(View.GONE);
                    btn_mercredi.setVisibility(View.GONE);
                    btn_jeudi.setVisibility(View.GONE);
                    btn_vendredi.setVisibility(View.GONE);
                    btn_samedi.setVisibility(View.GONE);
                    btn_dimanche.setVisibility(View.GONE);*/
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_choixDateDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        tv_choixDateDepart.setText(displayFormat.format(dateDepart));
                    }
                }, year, month , day);
                datePicker.show();
            }
        });

        btn_choixHeureDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePicker = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hours, int minutes) {

                        dateDepart.setHours(hours);
                        dateDepart.setMinutes(minutes);
                        tv_choixHeureDepart.setText(displayFormatHours.format(dateDepart));
                    }
                }, hour, minute, true);
                timePicker.show();
            }
        });







        btn_publier.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String lieuDepart = et_lieuDepart.getText().toString();
                String lieuArrivee = et_lieuArrivee.getText().toString();
                int nbPassager = (int) s_nbPassagers.getSelectedItem();
                float tarif = -1;
                try {
                    tarif = Float.parseFloat(et_tarif.getText().toString());
                } catch (NumberFormatException ignored) {}
                boolean estRegulier;
                ArrayList<Boolean> jours = new ArrayList<>();
                if (s_trajetRegulier.getSelectedItem().toString().equals("Oui")) {
                    jours = getSelectedDays();
                    estRegulier = true;
                } else {
                    estRegulier = false;
                }

                //TODO: verifier si la verification marche correctement (nottament si regulier est mis sur oui, il faut au moins un jour selectionné)
                if (lieuDepart.isEmpty()) {

                    Toast.makeText(getContext(), "Veuillez renseigner le lieu de depart", Toast.LENGTH_SHORT).show();
                } else if (lieuArrivee.isEmpty()) {
                    Toast.makeText(getContext(), "Veuillez renseigner le lieu de d'arrivée", Toast.LENGTH_SHORT).show();
                } else if (et_tarif.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Veuillez renseigner un tarif", Toast.LENGTH_SHORT).show();
                } else if (tarif == -1) {
                    Toast.makeText(getContext(), "Veuillez renseigner un tarif correct", Toast.LENGTH_SHORT).show();
                } else if (tv_choixDateDepart.getText().toString().equals("-/-/-")) {
                    Toast.makeText(getContext(), "Veuillez renseigner une date", Toast.LENGTH_SHORT).show();
                } else if (tv_choixHeureDepart.getText().toString().equals("- : -") ) {
                    Toast.makeText(getContext(), "Veuillez renseigner un horaire", Toast.LENGTH_SHORT).show();
                }else if (estRegulier  && jours.isEmpty()) {
                    Toast.makeText(getContext(), "Veuillez selectionner au moins un jour", Toast.LENGTH_SHORT).show();
                } else {
                    Trajet unTrajet = new Trajet(lieuDepart, lieuArrivee, dateDepart, 1, nbPassager, tarif, jours, estRegulier, user.getIdU());

                    AppDatabase.databaseWriteExecutor.execute(() -> {
                        db.trajetDAO().insert(unTrajet);
                    });

                    et_lieuDepart.setText("");
                    et_lieuArrivee.setText("");
                    et_tarif.setText("");
                    s_nbPassagers.setSelection(0);
                    s_trajetRegulier.setSelection(0);
                    btn_lundi.setChecked(false);
                    btn_mardi.setChecked(false);
                    btn_mercredi.setChecked(false);
                    btn_jeudi.setChecked(false);
                    btn_vendredi.setChecked(false);
                    btn_samedi.setChecked(false);
                    btn_dimanche.setChecked(false);
                    tv_choixDateDepart.setText("-/-/-");
                    tv_choixHeureDepart.setText("- : -");
                    Toast.makeText(getContext(), "trajet publié", Toast.LENGTH_SHORT).show();
                }
            }
        });






    }


    ArrayList<Boolean> getSelectedDays() {
        ArrayList<Boolean> selectedDays = new ArrayList<>();

        if(btn_lundi.isChecked()) { selectedDays.add(true); } else { selectedDays.add(false); }
        if(btn_mardi.isChecked()) { selectedDays.add(true); } else { selectedDays.add(false); }
        if(btn_mercredi.isChecked()) { selectedDays.add(true); } else { selectedDays.add(false); }
        if(btn_jeudi.isChecked()) { selectedDays.add(true); } else { selectedDays.add(false); }
        if(btn_vendredi.isChecked()) { selectedDays.add(true); } else { selectedDays.add(false); }
        if(btn_samedi.isChecked()) { selectedDays.add(true); } else { selectedDays.add(false); }
        if(btn_dimanche.isChecked()) { selectedDays.add(true); } else { selectedDays.add(false); }
        return selectedDays;
    }


}