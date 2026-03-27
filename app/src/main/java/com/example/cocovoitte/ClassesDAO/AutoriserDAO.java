package com.example.cocovoitte.ClassesDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cocovoitte.Classes.Autoriser;

import java.util.List;

@Dao
public interface AutoriserDAO {
    @Query("SELECT * FROM Autoriser")
    LiveData<List<Autoriser>> getAll();
    @Insert
    void insert(Autoriser objAutoriser);
    @Update
    void update(Autoriser objAutoriser);
    @Delete
    void delete(Autoriser objAutoriser);
}
