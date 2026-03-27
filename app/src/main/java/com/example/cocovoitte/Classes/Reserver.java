package com.example.cocovoitte.Classes;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(primaryKeys = {"idT", "idU"},
foreignKeys = {
        @ForeignKey(
                entity = Proposer.class,
                parentColumns = "idT",
                childColumns = "idT"
        ),
        @ForeignKey(
                entity = Utilisateur.class,
                parentColumns = "idU",
                childColumns = "idU"
        )
})
public class Reserver {
    private int idT;
    private int idU;
    private boolean etatAcceptation;

    public Reserver(int idT, int idU) {
        this.idT = idT;
        this.idU = idU;
        this.etatAcceptation = false;
    }

    public int getIdT() {
        return idT;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public boolean isEtatAcceptation() {
        return etatAcceptation;
    }

    public void setEtatAcceptation(boolean etatAcceptation) {
        this.etatAcceptation = etatAcceptation;
    }
}
