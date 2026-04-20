package com.example.cocovoitte.ClassesDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cocovoitte.Classes.AssocTrajetReserverUtilisateur;
import com.example.cocovoitte.Classes.AssocTrajetUtilisateur;
import com.example.cocovoitte.Classes.Trajet;

import java.util.Date;
import java.util.List;

@Dao
public interface TrajetDAO {
    @Query("SELECT * FROM Trajet")
    LiveData<List<Trajet>> getAll();


    //On utilise ca avec idU = UtilisateurLocal.idU afin de recup mes trajets proposées
    @Query("SELECT * FROM Trajet WHERE idU = :idU")
    LiveData<List<Trajet>> getTrajetByIdU(int idU);

    // Pour les demandes de réservation à valider (Conducteur qui regarde)
    // Table Reserver (prefix resa_)
    // Table Utilisateur (prefix user_)
    // Table Trajet (sans prefixe)
    @Query("SELECT " +
            "Reserver.idT AS resa_idT, " +
            "Reserver.idU AS resa_idU, " +
            "Reserver.etatAcceptation AS resa_etatAcceptation, " +
            "Utilisateur.idU AS user_idU, " +
            "Utilisateur.nom AS user_nom, " +
            "Utilisateur.prenom AS user_prenom, " +
            "Utilisateur.mail AS user_mail, " +
            "Utilisateur.motDePasse AS user_motDePasse, " +
            "Utilisateur.note AS user_note, " +
            "Utilisateur.nbTrajetsProposes AS user_nbTrajetsProposes, " +
            "Utilisateur.nbTrajetsReserves AS user_nbTrajetsReserves, " +
            "Utilisateur.description AS user_description, " +
            "Utilisateur.photo AS user_photo, " +
            "Utilisateur.dateInscription AS user_dateInscription, " +
            "Utilisateur.preferences AS user_preferences, " +
            "Trajet.* " +
            "FROM Trajet " +
            "INNER JOIN Reserver ON Trajet.idT = Reserver.idT " +
            "INNER JOIN Utilisateur ON Reserver.idU = Utilisateur.idU " +
            "WHERE Trajet.idU = :idU AND Reserver.etatAcceptation = 0")
    LiveData<List<AssocTrajetReserverUtilisateur>> getTrajetAValider(int idU);

    //Pour les trajets acceptés (Passager qui regarde son planning)
    // Table Reserver (prefix resa_)
    // Table Utilisateur (prefix user_)
    // Table Trajet (sans prefixe)
    @Query("SELECT " +
            "Reserver.idT AS resa_idT, " +
            "Reserver.idU AS resa_idU, " +
            "Reserver.etatAcceptation AS resa_etatAcceptation, " +
            "Utilisateur.idU AS user_idU, " +
            "Utilisateur.nom AS user_nom, " +
            "Utilisateur.prenom AS user_prenom, " +
            "Utilisateur.mail AS user_mail, " +
            "Utilisateur.motDePasse AS user_motDePasse, " +
            "Utilisateur.note AS user_note, " +
            "Utilisateur.nbTrajetsProposes AS user_nbTrajetsProposes, " +
            "Utilisateur.nbTrajetsReserves AS user_nbTrajetsReserves, " +
            "Utilisateur.description AS user_description, " +
            "Utilisateur.photo AS user_photo, " +
            "Utilisateur.dateInscription AS user_dateInscription, " +
            "Utilisateur.preferences AS user_preferences, " +
            "Trajet.* " +
            "FROM Trajet " +
            "INNER JOIN Reserver ON Trajet.idT = Reserver.idT " +
            "INNER JOIN Utilisateur ON Reserver.idU = Utilisateur.idU " +
            "WHERE Reserver.idU = :idU ")
    LiveData<List<AssocTrajetReserverUtilisateur>> getTrajetAPrendre(int idU);


    //TODO: Rajouter un système pour empecher l'affichage des trajets deja reservés ou en attente (donc présence de (idU, idT) dans Reserver)
    @Query("SELECT " +
            "Utilisateur.idU AS user_idU, " +
            "Utilisateur.nom AS user_nom, " +
            "Utilisateur.prenom AS user_prenom, " +
            "Utilisateur.mail AS user_mail, " +
            "Utilisateur.motDePasse AS user_motDePasse, " +
            "Utilisateur.note AS user_note, " +
            "Utilisateur.nbTrajetsProposes AS user_nbTrajetsProposes, " +
            "Utilisateur.nbTrajetsReserves AS user_nbTrajetsReserves, " +
            "Utilisateur.description AS user_description, " +
            "Utilisateur.photo AS user_photo, " +
            "Utilisateur.dateInscription AS user_dateInscription, " +
            "Utilisateur.preferences AS user_preferences, " +
            "Trajet.* ,Trajet.idT as idTrajet, (SELECT COUNT(*) FROM Reserver WHERE Reserver.idT = Trajet.idT) as nbPlacePrise " +
            "FROM Trajet " +
            "INNER JOIN Utilisateur ON Trajet.idU = Utilisateur.idU " +
            "WHERE Trajet.dateDebut > :jourRecherche " +
            "AND Trajet.dateDebut < :jourSuivant " +
            "AND LOWER(Trajet.lieuArrive) = LOWER(:villeArrive) " +
            "AND LOWER(Trajet.lieuDepart) = LOWER(:villeDepart) " +
            "AND Trajet.nbPassagerP >= nbPlacePrise + :nbPassager " +
            "AND Trajet.idU != :idU " +
            "AND NOT EXISTS(SELECT * FROM Trajet INNER JOIN Reserver ON Trajet.idT = Reserver.idT WHERE Trajet.idU = :idU AND Trajet.idT = idTrajet)")

    //AND NOT EXIST(SELECT * FROM Trajet INNER JOIN Reserver ON Trajet.idT = Reserver.idT WHERE )
    LiveData<List<AssocTrajetUtilisateur>> getTrajetRecherche(String villeDepart, String villeArrive, int nbPassager, Date jourRecherche, Date jourSuivant, int idU);
    @Insert
    long insert(Trajet objTrajet);
    @Update
    void update(Trajet objTrajet);
    @Delete
    void delete(Trajet objTrajet);
}
