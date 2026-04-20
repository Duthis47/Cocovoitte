package com.example.cocovoitte.database;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.Date;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String fromBooleanList(ArrayList<Boolean> list) {
        StringBuilder sb = new StringBuilder();
        for (Boolean b : list) {
            sb.append(b ? "1" : "0");
        }
        return sb.toString();
    }

    @TypeConverter
    public static ArrayList<Boolean> toBooleanList(String value) {
        ArrayList<Boolean> list = new ArrayList<>();
        for (char c : value.toCharArray()) {
            list.add(c == '1');
        }
        return list;
    }

    @TypeConverter
    public static String fromStringList(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String b : list) {
            String txt = b+";";
            sb.append(txt);
        }
        return sb.toString();
    }

    @TypeConverter
    public static ArrayList<String> toStringList(String value) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(value.split(";")));
        return list;
    }
}