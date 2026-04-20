package com.example.cocovoitte.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.cocovoitte.Classes.UtilisateurLocal;
import com.example.cocovoitte.R;
import com.example.cocovoitte.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private AppDatabase db;
    private ImageView iv_profilePicture;
    private TextView tv_username;
    private TextView tv_description;
    private TextView tv_inscriptionDate;
    private TextView tv_countProposedDrive;
    private TextView tv_countBookedDrive;
    private RatingBar rb_rate;
    private TextView tv_reviewsCount;
    private Button btn_seeReviews;
    private LinearLayout ll_preferences;


    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = AppDatabase.getDatabase(requireActivity().getApplicationContext());
        iv_profilePicture = view.findViewById(R.id.iv_profilePicture);
        tv_username = view.findViewById(R.id.tv_username);
        tv_description = view.findViewById(R.id.tv_description);
        tv_inscriptionDate = view.findViewById(R.id.tv_inscriptionDate);
        tv_countProposedDrive = view.findViewById(R.id.tv_countProposedDrive);
        tv_countBookedDrive = view.findViewById(R.id.tv_countBookedDrive);
        rb_rate = view.findViewById(R.id.rb_rate);
        tv_reviewsCount = view.findViewById(R.id.tv_reviewsCount);
        btn_seeReviews = view.findViewById(R.id.btn_seeReviews);
        ll_preferences = view.findViewById(R.id.ll_preferences);




        //on recupere les informations de l'utilisateur
        db.utilisateurLocalDAO().getLocalUser().observe(getViewLifecycleOwner(), user -> {

            iv_profilePicture.setImageResource(R.drawable.ic_launcher_background); //pour le moment tant qu'on sait pas faire photos
            tv_username.setText(String.format("%s %s", user.getPrenom(), user.getNom()));
            tv_description.setText(String.format("%s", user.getDescription()));
            tv_inscriptionDate.setText(String.format("%s", user.getDateInscription()));
            tv_countProposedDrive.setText(String.format("%s", user.getNbTrajetsProposes()));
            tv_countBookedDrive.setText(String.format("%s", user.getNbTrajetsReserves()));
            rb_rate.setRating(user.getNote());
            tv_reviewsCount.setText(String.format("%s %s", "10", getString(R.string.tv_reviewsCount))); //faire requete pour le nombre d'avis

            ll_preferences.removeAllViews();

            if (user.getPreferences() != null && !user.getPreferences().isEmpty()){
                List<String> preferences = user.getPreferences();
                for (String preference : preferences){
                    TextView unePref = new TextView(getContext());
                    unePref.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    unePref.setText(preference);
                    ll_preferences.addView(unePref);
                }
            }
        });

        btn_seeReviews.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //redirection vers la page des avis
            }
        });




    }
}