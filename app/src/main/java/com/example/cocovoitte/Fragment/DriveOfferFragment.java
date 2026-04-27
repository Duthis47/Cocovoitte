package com.example.cocovoitte.Fragment;

import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
import android.widget.ToggleButton;

import com.example.cocovoitte.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DriveOfferFragment extends Fragment {

    private Calendar calendar = Calendar.getInstance();
    private EditText et_lieuDepart;
    private EditText et_lieuArrivee;
    private TextView tv_choixDateDepart;
    private TextView tv_choixHeureDepart;
    private Spinner s_nbPassagers;
    private Spinner s_trajetRegulier;
    private Spinner s_frequence;
    private ToggleButton btn_lundi, btn_mardi, btn_mercredi, btn_jeudi, btn_vendredi, btn_samedi, btn_dimanche;

    private Button btn_publier;
    private LinearLayout btn_choixDateDepart;


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

        btn_choixDateDepart = view.findViewById(R.id.btn_choixDateDepart);

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
                    btn_lundi.setVisibility(View.VISIBLE);
                    btn_mardi.setVisibility(View.VISIBLE);
                    btn_mercredi.setVisibility(View.VISIBLE);
                    btn_jeudi.setVisibility(View.VISIBLE);
                    btn_vendredi.setVisibility(View.VISIBLE);
                    btn_samedi.setVisibility(View.VISIBLE);
                    btn_dimanche.setVisibility(View.VISIBLE);
                } else {
                    btn_lundi.setVisibility(View.GONE);
                    btn_mardi.setVisibility(View.GONE);
                    btn_mercredi.setVisibility(View.GONE);
                    btn_jeudi.setVisibility(View.GONE);
                    btn_vendredi.setVisibility(View.GONE);
                    btn_samedi.setVisibility(View.GONE);
                    btn_dimanche.setVisibility(View.GONE);
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
                        tv_choixDateDepart.setText(dateDepart.toString());
                    }
                }, year, month , day);
                datePicker.show();
            }
        });







        btn_publier.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String lieuDepart = et_lieuDepart.getText().toString();
                String lieuArrivee = et_lieuArrivee.getText().toString();



            }
        });






    }


    ArrayList<String> getSelectedDays() {
        ArrayList<String> selectedDays = new ArrayList<>();

        if (btn_lundi.isChecked()) selectedDays.add("Lundi");
        if (btn_mardi.isChecked()) selectedDays.add("Mardi");
        if (btn_mercredi.isChecked()) selectedDays.add("Mercredi");
        if (btn_jeudi.isChecked()) selectedDays.add("Jeudi");
        if (btn_vendredi.isChecked()) selectedDays.add("Vendredi");
        if (btn_samedi.isChecked()) selectedDays.add("Samedi");
        if (btn_dimanche.isChecked()) selectedDays.add("Dimanche");
        return selectedDays;
    }


}