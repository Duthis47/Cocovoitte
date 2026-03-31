package com.example.cocovoitte.Classes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Autorisation {
    @PrimaryKey(autoGenerate = true)
    private int idA;
    private String libelleAutorisation;

    public Autorisation(int idA, String libelleAutorisation) {
        this.idA = idA;
        this.libelleAutorisation = libelleAutorisation;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public String getLibelleAutorisation() {
        return libelleAutorisation;
    }

    public void setLibelleAutorisation(String libelleAutorisation) {
        this.libelleAutorisation = libelleAutorisation;
    }
}
