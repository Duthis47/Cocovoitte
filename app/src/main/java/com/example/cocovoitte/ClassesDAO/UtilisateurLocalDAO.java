package com.example.cocovoitte.ClassesDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cocovoitte.Classes.UtilisateurLocal;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UtilisateurLocalDAO {
    @Query("SELECT * FROM UtilisateurLocal LIMIT 1")
    LiveData<UtilisateurLocal> getLocalUser();

    @Query("DELETE FROM UtilisateurLocal")
    void deleteAll();

    @Query("UPDATE UtilisateurLocal SET description = :description WHERE idU = :id")
    void updateDescription(int id, String description);

    @Query("SELECT preferences FROM UtilisateurLocal WHERE idU = :id")
    List<String> getPreferences(int id);

    @Query("UPDATE UtilisateurLocal SET preferences = :preferences WHERE idU = :id")
    void updatePreferences(int id, List<String> preferences);


    @Insert
    void insert(UtilisateurLocal objUtilisateur);

    @Update
    void update(UtilisateurLocal objUtilisateur);

    @Delete
    void delete(UtilisateurLocal objUtilisateur);
}
