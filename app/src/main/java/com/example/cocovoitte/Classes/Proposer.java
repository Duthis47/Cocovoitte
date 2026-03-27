package com.example.cocovoitte.Classes;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(primaryKeys = {"idT", "idU"},
        foreignKeys = {
                @ForeignKey(
                        entity = Trajet.class,
                        parentColumns = "idT",
                        childColumns = "idT"
                ),
                @ForeignKey(
                        entity = Utilisateur.class,
                        parentColumns = "idU",
                        childColumns = "idU"
                )
        })
public class Proposer {
    private int idT;
    private int idU;

    public Proposer(int idT, int idU) {
        this.idT = idT;
        this.idU = idU;
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
}
