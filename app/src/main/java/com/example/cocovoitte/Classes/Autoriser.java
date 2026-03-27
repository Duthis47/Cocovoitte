package com.example.cocovoitte.Classes;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(primaryKeys = {"idU", "idA"},
foreignKeys = {
        @ForeignKey(
                entity = Utilisateur.class,
                parentColumns = "idU",
                childColumns = "idU"
        ),
        @ForeignKey(
                entity = Autorisation.class,
                parentColumns = "idA",
                childColumns = "idA"
        )
})
public class Autoriser {
    private int idU;
    private int idA;

    public Autoriser(int idU, int idA) {
        this.idU = idU;
        this.idA = idA;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }
}
