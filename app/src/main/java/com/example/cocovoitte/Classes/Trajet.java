package com.example.cocovoitte.Classes;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity(foreignKeys = {
        @ForeignKey(entity = Utilisateur.class,
                childColumns = "idU",
                parentColumns = "idU"
        )
})
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
    private int idU;
    public Trajet(int idT, String lieuDepart, String lieuArrive, Date dateDebut, float dureeTrajet, int nbPassagerP, ArrayList<Boolean> jourFrequence, Boolean estRegulier, int idU) {
        this.idT = idT;
        this.jourFrequence = jourFrequence;
        this.estRegulier = estRegulier;
        this.nbPassagerP = nbPassagerP;
        this.dureeTrajet = dureeTrajet;
        this.dateDebut = dateDebut;
        this.lieuArrive = lieuArrive;
        this.lieuDepart = lieuDepart;
        this.idU = idU;
    }

    @Ignore
    public Trajet(String lieuDepart, String lieuArrive, Date dateDebut, float dureeTrajet, int nbPassagerP, ArrayList<Boolean> jourFrequence, Boolean estRegulier, int idU) {
        this.jourFrequence = jourFrequence;
        this.estRegulier = estRegulier;
        this.nbPassagerP = nbPassagerP;
        this.dureeTrajet = dureeTrajet;
        this.dateDebut = dateDebut;
        this.lieuArrive = lieuArrive;
        this.lieuDepart = lieuDepart;
        this.idU = idU;

    }

    public int getIdT() {
        return idT;
    }

    public void setIdT(int idT) {
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

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }
}
