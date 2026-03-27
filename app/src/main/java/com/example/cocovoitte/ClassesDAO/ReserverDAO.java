package com.example.cocovoitte.ClassesDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cocovoitte.Classes.Reserver;

import java.util.List;

@Dao
public interface ReserverDAO {
    @Query("SELECT * FROM Reserver")
    LiveData<List<Reserver>> getAll();
    @Insert
    void insert(Reserver objReserver);
    @Update
    void update(Reserver objReserver);
    @Delete
    void delete(Reserver objReserver);
}
