package com.example.cocovoitte.Utils;

public class ValidationUtils {
    public static boolean isEmailValide(String email) {
        if (email == null) return false;
        // Ta regex
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }
}