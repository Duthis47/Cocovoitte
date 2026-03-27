package com.example.cocovoitte.ClassesDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cocovoitte.Classes.Utilisateur;

import java.util.List;

@Dao
public interface UtilisateurDAO {
    @Query("SELECT * FROM Utilisateur")
    LiveData<List<Utilisateur>> getAll();

    @Insert
    void insert(Utilisateur objUtilisateur);

    @Update
    void update(Utilisateur objUtilisateur);

    @Delete
    void delete(Utilisateur objUtilisateur);
}
