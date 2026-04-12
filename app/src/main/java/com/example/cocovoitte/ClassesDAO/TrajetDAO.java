package com.example.cocovoitte.ClassesDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cocovoitte.Classes.AssocTrajetUtilisateur;
import com.example.cocovoitte.Classes.Trajet;

import java.util.List;

@Dao
public interface TrajetDAO {
    @Query("SELECT * FROM Trajet")
    LiveData<List<Trajet>> getAll();


    //On utilise ca avec idU = UtilisateurLocal.idU afin de recup mes trajets proposées
    @Query("SELECT * FROM Trajet WHERE idU = :idU")
    LiveData<List<Trajet>> getTrajetByIdU(int idU);

    @Query("SELECT * FROM Trajet " +
            "INNER JOIN Reserver ON Trajet.idT = Reserver.idT " +
            "INNER JOIN Utilisateur ON Reserver.idU = Utilisateur.idU " +
            "WHERE Trajet.idU = :idU AND Reserver.etatAcceptation = 0")
    LiveData<List<AssocTrajetUtilisateur>> getTrajetAValider(int idU);

    @Query("SELECT * FROM Trajet " +
            "INNER JOIN Reserver ON Trajet.idT = Reserver.idT " +
            "INNER JOIN Utilisateur ON Reserver.idU = Utilisateur.idU " +
            "WHERE Reserver.idU = :idU AND Reserver.etatAcceptation = 1")
    LiveData<List<AssocTrajetUtilisateur>> getTrajetAPrendre(int idU);
    @Insert
    long insert(Trajet objTrajet);
    @Update
    void update(Trajet objTrajet);
    @Delete
    void delete(Trajet objTrajet);
}
