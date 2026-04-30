package com.example.cocovoitte.Utils;


//Classe pour les outils de validation (utile pour test unitaire)
public class ValidationUtils {
    public static boolean isEmailValide(String email) {
        if (email == null) return false;
        // Verification du format d'email
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

    public static boolean isPasswordValid(String passwd){
        if (passwd == null) return false;

        //Verification de 1 MAJ, 1 caractères spécial, et minimum 8 caractères
        String regex = "^(?=.*[A-Z])(?=.*\\d).{8,}$";
        return passwd.matches(regex);
    }
}