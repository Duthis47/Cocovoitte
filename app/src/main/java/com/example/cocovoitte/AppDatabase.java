package com.example.cocovoitte;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.cocovoitte.Classes.Autorisation;
import com.example.cocovoitte.Classes.Autoriser;
import com.example.cocovoitte.Classes.Proposer;
import com.example.cocovoitte.Classes.Reserver;
import com.example.cocovoitte.Classes.Trajet;
import com.example.cocovoitte.Classes.Utilisateur;
import com.example.cocovoitte.ClassesDAO.AutorisationDAO;
import com.example.cocovoitte.ClassesDAO.AutoriserDAO;
import com.example.cocovoitte.ClassesDAO.ProposerDAO;
import com.example.cocovoitte.ClassesDAO.ReserverDAO;
import com.example.cocovoitte.ClassesDAO.TrajetDAO;
import com.example.cocovoitte.ClassesDAO.UtilisateurDAO;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Autorisation.class, Autoriser.class, Proposer.class, Reserver.class, Trajet.class, Utilisateur.class},
        version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AutorisationDAO autorisationDAO();
    public abstract AutoriserDAO autoriserDAO();
    public abstract ProposerDAO proposerDAO();
    public abstract ReserverDAO reserverDAO();
    public abstract TrajetDAO trajetDAO();
    public abstract UtilisateurDAO utilisateurDAO();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        // on ne crée pas le singleton si il existe deja
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class, "base-nom"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new
            RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    INSTANCE.databaseWriteExecutor.execute(() -> {
                        //Instanciation au début du programme
                    });
                }
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                }
            };
}
