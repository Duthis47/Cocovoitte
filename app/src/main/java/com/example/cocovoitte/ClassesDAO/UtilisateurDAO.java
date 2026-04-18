package com.example.cocovoitte.ClassesDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cocovoitte.Classes.Utilisateur;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UtilisateurDAO {
    @Query("SELECT * FROM Utilisateur")
    LiveData<List<Utilisateur>> getAll();

    @Query("SELECT * FROM Utilisateur WHERE idU = :id")
    LiveData<Utilisateur> getUtilisateurById(int id);

    @Query("SELECT * FROM Utilisateur WHERE mail = :mail AND motDePasse= :password")
    Utilisateur getUtilisateurByMailAndPassword(String mail, String password);

    @Query("UPDATE Utilisateur SET description = :description WHERE idU = :id")
    void updateDescription(int id, String description);

    @Query("UPDATE Utilisateur SET preferences = :preferences WHERE idU = :id")
    void updatePreferences(int id, String preferences);

    @Insert
    long insert(Utilisateur objUtilisateur);

    @Update
    void update(Utilisateur objUtilisateur);

    @Delete
    void delete(Utilisateur objUtilisateur);
}
