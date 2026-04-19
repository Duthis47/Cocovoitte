package com.example.cocovoitte.Classes;

import androidx.room.Embedded;

public class AssocTrajetUtilisateur {

    @Embedded(prefix="user_")
    Utilisateur leUser;

    @Embedded
    Trajet leTrajet;

    int nbPlacePrise;

    public AssocTrajetUtilisateur(Utilisateur leUser, Trajet leTrajet, int nbPlacePrise) {
        this.leUser = leUser;
        this.leTrajet = leTrajet;
        this.nbPlacePrise = nbPlacePrise;
    }

    public Utilisateur getLeUser() {
        return leUser;
    }

    public void setLeUser(Utilisateur leUser) {
        this.leUser = leUser;
    }

    public Trajet getLeTrajet() {
        return leTrajet;
    }

    public void setLeTrajet(Trajet leTrajet) {
        this.leTrajet = leTrajet;
    }

    public int getNbPlacePrise() {
        return nbPlacePrise;
    }

    public void setNbPlacePrise(int nbPlacePrise) {
        this.nbPlacePrise = nbPlacePrise;
    }
}
