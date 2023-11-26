package com.soft.afri_wifi.Models;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;


public class currentUsers {

    public static String storeKeeperRole = "Store Keeper";
    public static String auditeurRole = "Auditor";


    public static tUtilisateur getCurrentUsers(Context context){
        //enregistrement de l'object du cuurent users dans les preferences
        Gson gson = new Gson();
        String json = PreferenceManager.getDefaultSharedPreferences(context).getString("currentUsers", "");
        return gson.fromJson(json, tUtilisateur.class);
    }
    public static void setCurrentUsers(Context context, tUtilisateur myObject){
        //enregistrement de l'object du cuurent users dans les preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editorr = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(myObject);
        editorr.putString("currentUsers", json);
        editorr.commit();

        //return gson.fromJson(json, authPhone.class);
    }


    public static void setConnexionTrue(Context context){
        //cette methode pour enregistres dans les preferences que la connexion a reussi

        //SharedPreferences shared = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences shared = context.getSharedPreferences("maPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorr = shared.edit();
        editorr.putString("authLogin","true");
        editorr.apply();
        editorr.commit();

    }
    public static void setDeconnexionTrue(Activity context){
        //lors de la dexonnexion
        //SharedPreferences shared = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences shared = context.getSharedPreferences("maPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorr = shared.edit();
        editorr.putString("authLogin","");
        editorr.apply();
        editorr.commit();

    }

    public static void setUsersAccess(Activity context, String access){
        //lors de la dexonnexion
        SharedPreferences shared = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editorr = shared.edit();
        editorr.putString("userRoles",access);
        editorr.apply();
        editorr.commit();

    }

    public static String getUserRoles(Activity context){
        //lors de la dexonnexion
        SharedPreferences shared = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return  shared.getString("userRoles","");
    }

    public static boolean getIsAuditor(Activity context){
        //lors de la dexonnexion
        SharedPreferences shared = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return  shared.getString("userRoles","").equals(auditeurRole);
    }
}
