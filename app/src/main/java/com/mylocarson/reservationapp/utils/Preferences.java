package com.mylocarson.reservationapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.mylocarson.reservationapp.classes.Constants;
import com.mylocarson.reservationapp.models.tables.Table;
import com.mylocarson.reservationapp.models.user.User;

public class Preferences {

    public static final String PREF_NAME = "Reservations";
    public static final String PERSONAL_PREF = "Personal";
    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;

    public static void  clearAll(Context context){
        pref = context.getSharedPreferences(PREF_NAME, 0);
        editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    public static void saveUser(Context context, User user){
        pref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor = pref.edit();

        Gson gson = new Gson();
        String jsonString = gson.toJson(user);

        editor.putString(Constants.USER,jsonString);
        editor.apply();
    }

    public static User getUser(Context context){
        pref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        String user ="";
        Gson gson = new Gson();

        user = pref.getString(Constants.USER,"NULL");
        return gson.fromJson(user,User.class);
    }

    public static void saveSelectedTable(Context context, Table table){
        pref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor = pref.edit();

        Gson gson = new Gson();
        String jsonString = gson.toJson(table);

        editor.putString(Constants.USER,jsonString);
        editor.apply();
    }

    public static Table getSelectedTable(Context context){
        pref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        String table ="";
        Gson gson = new Gson();

        table = pref.getString(Constants.USER,"NULL");
        return gson.fromJson(table,Table.class);
    }



}

