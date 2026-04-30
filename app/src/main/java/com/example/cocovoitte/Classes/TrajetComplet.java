package com.example.cocovoitte.Classes;

import androidx.room.Embedded;

public class TrajetComplet {
    @Embedded
    private Trajet leTrajet;
    private int nbPlaceValidee;

    public TrajetComplet(Trajet leTrajet, int nbPassager) {
        this.leTrajet = leTrajet;
        this.nbPlaceValidee = nbPassager;
    }
    public TrajetComplet() {
    }

    public Trajet getLeTrajet() {
        return leTrajet;
    }

    public void setLeTrajet(Trajet leTrajet) {
        this.leTrajet = leTrajet;
    }

    public int getNbPlaceValidee() {
        return nbPlaceValidee;
    }

    public void setNbPlaceValidee(int nbPlaceValidee) {
        this.nbPlaceValidee = nbPlaceValidee;
    }
}
