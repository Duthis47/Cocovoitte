package com.example.cocovoitte.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cocovoitte.Classes.Utilisateur;
import com.example.cocovoitte.database.AppDatabase;
import com.example.cocovoitte.R;


public class SignInFragment extends Fragment {

    private AppDatabase db;
    private EditText et_firstName;
    private EditText et_lastName;
    private EditText et_email;
    private EditText et_password;
    private Button btn_submit;

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        db = AppDatabase.getDatabase(getActivity().getApplicationContext());
        et_firstName = view.findViewById(R.id.et_firstName);
        et_lastName = view.findViewById(R.id.et_lastName);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        btn_submit = view.findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = et_firstName.getText().toString();
                String lastName = et_lastName.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                Utilisateur nouvelUtilisateur = new Utilisateur(lastName, firstName, email, password);
                //db.databaseWriteExecutor.execute(() -> {
                //    db.UtilisateurDao().insert(nouvelUtilisateur);
                //});

            }
        });

    }
}