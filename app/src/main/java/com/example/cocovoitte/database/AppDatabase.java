package com.example.cocovoitte.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.cocovoitte.Classes.Autorisation;
import com.example.cocovoitte.Classes.Autoriser;
import com.example.cocovoitte.Classes.Reserver;
import com.example.cocovoitte.Classes.Trajet;
import com.example.cocovoitte.Classes.Utilisateur;
import com.example.cocovoitte.Classes.UtilisateurLocal;
import com.example.cocovoitte.ClassesDAO.AutorisationDAO;
import com.example.cocovoitte.ClassesDAO.AutoriserDAO;
import com.example.cocovoitte.ClassesDAO.ReserverDAO;
import com.example.cocovoitte.ClassesDAO.TrajetDAO;
import com.example.cocovoitte.ClassesDAO.UtilisateurDAO;
import com.example.cocovoitte.ClassesDAO.UtilisateurLocalDAO;


import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Autorisation.class, Autoriser.class, Reserver.class, Trajet.class, Utilisateur.class, UtilisateurLocal.class},
        version = 1)
//Ajout du convertisseur pour gerer les Dates
@TypeConverters({Converters.class})

public abstract class AppDatabase extends RoomDatabase {
    public abstract AutorisationDAO autorisationDAO();
    public abstract AutoriserDAO autoriserDAO();
    public abstract ReserverDAO reserverDAO();
    public abstract TrajetDAO trajetDAO();
    public abstract UtilisateurDAO utilisateurDAO();
    public abstract UtilisateurLocalDAO utilisateurLocalDAO();


    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class, "base-nom"
                            )
                            .addCallback(roomCallback) // <--- IL MANQUAIT CETTE LIGNE
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback roomCallback = new
            RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    Log.d("AppDatabase", "creation du jeu d'essai");
                    AppDatabase.databaseWriteExecutor.execute(() -> {

                        Utilisateur userTest = new Utilisateur("NomTest", "PremonTest", "MailTest", "MdPTest");
                        //Instanciation au début du programme
                        INSTANCE.utilisateurDAO().insert(userTest);
 /*                       INSTANCE.utilisateurLocalDAO().insert(new UtilisateurLocal(userTest));
                        long x = INSTANCE.trajetDAO().insert(new Trajet("test", "test", new Date(),10, 3, new ArrayList<>(),false, 1));
*/
                    });
                }
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                }
            };
}
