package com.example.cocovoitte.ClassesDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cocovoitte.Classes.Autorisation;

import java.util.List;

@Dao
public interface AutorisationDAO {
    @Query("SELECT * FROM Autorisation")
    LiveData<List<Autorisation>> getAll();
    @Insert
    void insert(Autorisation objAutorisation);
    @Update
    void update(Autorisation objAutorisation);
    @Delete
    void delete(Autorisation objAutorisation);
}
