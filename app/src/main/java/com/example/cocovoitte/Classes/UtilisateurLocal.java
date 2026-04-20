package com.example.cocovoitte.Classes;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;


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
    private int nbTrajetsProposes;
    private int nbTrajetsReserves;
    private String description;
    private String photo;
    private Date dateInscription;
    private List<String> preferences;

    public UtilisateurLocal(int idU, String nom, String prenom, String mail, String motDePasse, float note, int nbTrajetsProposes, int nbTrajetsReserves, String description, String photo, Date dateInscription, ArrayList<String> preferences) {
        this.idU = idU;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.note = note;
        this.nbTrajetsProposes = nbTrajetsProposes;
        this.nbTrajetsReserves = nbTrajetsReserves;
        this.description = description;
        this.photo = photo;
        this.dateInscription = dateInscription;
        this.preferences = preferences;
    }

    @Ignore
    public UtilisateurLocal(Utilisateur user) {
        this.idU = user.getIdU();
        this.nom = user.getNom();
        this.prenom = user.getPrenom();
        this.mail = user.getPrenom();
        this.motDePasse = user.getMotDePasse();
        this.note = user.getNote();
        this.nbTrajetsProposes = user.getNbTrajetsProposes();
        this.nbTrajetsReserves = user.getNbTrajetsReserves();
        this.description = user.getDescription();
        this.photo = user.getPhoto();
        this.dateInscription = user.getDateInscription();
        this.preferences = user.getPreferences();
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

    public int getNbTrajetsReserves() {
        return nbTrajetsReserves;
    }

    public int getNbTrajetsProposes() {
        return nbTrajetsProposes;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setNbTrajetsProposes(int nbTrajetsProposes) {
        this.nbTrajetsProposes = nbTrajetsProposes;
    }

    public void setNbTrajetsReserves(int nbTrajetsReserves) {
        this.nbTrajetsReserves = nbTrajetsReserves;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public void setPreferences(ArrayList<String> preferences) {
        this.preferences = preferences;
    }
}
