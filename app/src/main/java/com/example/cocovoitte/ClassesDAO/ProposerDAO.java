package com.example.cocovoitte.ClassesDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cocovoitte.Classes.Proposer;

import java.util.List;

@Dao
public interface ProposerDAO {
    @Query("SELECT * FROM Proposer")
    LiveData<List<Proposer>> getAll();
    @Insert
    void insert(Proposer objProposer);
    @Update
    void update(Proposer objProposer);
    @Delete
    void delete(Proposer objProposer);
}
