package com.example.cocovoitte.Classes;

import androidx.room.Embedded;
import androidx.room.Relation;

public class AssocTrajetUtilisateur {
    @Embedded
    Reserver laResa;

    @Relation(
            entity = Reserver.class,
            parentColumn = "idU",
            entityColumn = "idU")
    Utilisateur leUser;

    @Relation(
            entity = Reserver.class,
            parentColumn = "idT",
            entityColumn = "idT")
    Trajet leTrajet;
}
