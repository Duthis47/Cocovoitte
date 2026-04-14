package com.example.cocovoitte.ClassesDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cocovoitte.Classes.UtilisateurLocal;

import java.util.List;

@Dao
public interface UtilisateurLocalDAO {
    @Query("SELECT * FROM UtilisateurLocal LIMIT 1")
    LiveData<UtilisateurLocal> getLocalUser();

    @Query("DELETE FROM UtilisateurLocal")
    void deleteAll();

    @Insert
    void insert(UtilisateurLocal objUtilisateur);

    @Update
    void update(UtilisateurLocal objUtilisateur);

    @Delete
    void delete(UtilisateurLocal objUtilisateur);
}
