package com.example.cocovoitte.Classes;

import androidx.room.Embedded;
import androidx.room.Relation;

public class AssocTrajetUtilisateur {
    @Embedded(prefix="resa_")
    Reserver laResa;

@Embedded(prefix="user_")
    Utilisateur leUser;

    @Embedded
    Trajet leTrajet;

    public AssocTrajetUtilisateur(Reserver laResa, Utilisateur leUser, Trajet leTrajet) {
        this.laResa = laResa;
        this.leUser = leUser;
        this.leTrajet = leTrajet;
    }

    public Reserver getLaResa() {
        return laResa;
    }

    public void setLaResa(Reserver laResa) {
        this.laResa = laResa;
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
}
