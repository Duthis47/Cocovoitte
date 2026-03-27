package com.example.cocovoitte.ClassesDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cocovoitte.Classes.Trajet;

import java.util.List;

@Dao
public interface TrajetDAO {
    @Query("SELECT * FROM Trajet")
    LiveData<List<Trajet>> getAll();
    @Insert
    void insert(Trajet objTrajet);
    @Update
    void update(Trajet objTrajet);
    @Delete
    void delete(Trajet objTrajet);
}
