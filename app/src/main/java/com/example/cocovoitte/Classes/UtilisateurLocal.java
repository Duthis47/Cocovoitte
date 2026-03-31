package com.example.cocovoitte.Classes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


//Unique classe qui serait sur le téléphone normallement
@Entity
public class UtilisateurLocal {
    @PrimaryKey(autoGenerate = true)
    private int idU;
    private String nom;
    private String prenom;
    private String mail;
    private String motDePasse;
    private float note;

    public UtilisateurLocal(int idU, String nom, String prenom, String mail, String motDePasse, float note) {
        this.idU = idU;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.note = note;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
}
