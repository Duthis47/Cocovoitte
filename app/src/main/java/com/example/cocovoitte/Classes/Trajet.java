package com.example.cocovoitte.Classes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Trajet {
    @PrimaryKey(autoGenerate = true)
    private int idT;
    private String lieuDepart;
    private String lieuArrive;
    private Date dateDebut;
    private float dureeTrajet;
    private int nbPassagerP;
    private Boolean estRegulier = false;
    private ArrayList<Boolean> jourFrequence = new ArrayList<>(List.of(false, false, false, false, false, false, false));

    public Trajet(int idT, String lieuDepart, String lieuArrive, Date dateDebut, float dureeTrajet, int nbPassagerP) {
        this.idT = idT;
        this.lieuDepart = lieuDepart;
        this.lieuArrive = lieuArrive;
        this.dateDebut = dateDebut;
        this.dureeTrajet = dureeTrajet;
        this.nbPassagerP = nbPassagerP;
    }

    public Trajet(int idT, String lieuDepart, String lieuArrive, Date dateDebut, float dureeTrajet, int nbPassagerP, ArrayList<Boolean> jourFrequence) {
        this.idT = idT;
        this.jourFrequence = jourFrequence;
        this.estRegulier = true;
        this.nbPassagerP = nbPassagerP;
        this.dureeTrajet = dureeTrajet;
        this.dateDebut = dateDebut;
        this.lieuArrive = lieuArrive;
        this.lieuDepart = lieuDepart;
    }

    public int getId() {
        return idT;
    }

    public void setId(int idT) {
        this.idT = idT;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getLieuArrive() {
        return lieuArrive;
    }

    public void setLieuArrive(String lieuArrive) {
        this.lieuArrive = lieuArrive;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public float getDureeTrajet() {
        return dureeTrajet;
    }

    public void setDureeTrajet(float dureeTrajet) {
        this.dureeTrajet = dureeTrajet;
    }

    public ArrayList<Boolean> getJourFrequence() {
        return jourFrequence;
    }

    public void setJourFrequence(ArrayList<Boolean> jourFrequence) {
        this.jourFrequence = jourFrequence;
    }

    public Boolean getEstRegulier() {
        return estRegulier;
    }

    public void setEstRegulier(Boolean estRegulier) {
        this.estRegulier = estRegulier;
    }

    public int getNbPassagerP() {
        return nbPassagerP;
    }

    public void setNbPassagerP(int nbPassagerP) {
        this.nbPassagerP = nbPassagerP;
    }
}
