package com.example.cocovoitte.Classes;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.util.UUID;

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
public class Reserver {
    private int idT;
    private int idU;
    private String etatAcceptation; //Valeur = En Attente, Accepté ou Confirmé
    private String uuid;

    public Reserver(int idT, int idU) {
        this.idT = idT;
        this.idU = idU;
        this.etatAcceptation = "En Attente";
        this.uuid = "";
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

    public String getEtatAcceptation() {
        return etatAcceptation;
    }

    public void setEtatAcceptation(String etatAcceptation) {
        this.etatAcceptation = etatAcceptation;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void genUuid(){
        setUuid(UUID.randomUUID().toString());
    }
}
