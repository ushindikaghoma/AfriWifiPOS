package com.soft.afri_wifi.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;
import com.soft.afri_wifi.DataBase.DatabaseHandler;
import com.soft.afri_wifi.serveur.sendDataPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class tUtilisateur {
    public static  String TABLE_NAME = "tUtilisateur";
    public static  String PRIMARY_KEY = "NomUtilisateur";

    private int IdUtilisareur;
    private String NomUtilisateur,MotPasseUtilisateur,NiveauUtilisateur,
            FonctionUt,ServiceAffe,DepotAffecter, DesignationUt;
    private  int Compte, CaisseDepense;

    public tUtilisateur(int idUtilisareur, String nomUtilisateur,
                        String motPasseUtilisateur, String niveauUtilisateur,
                        String fonctionUt, String serviceAffe,
                        String depotAffecter, String designationUt,
                        int compte, int caisseDepense) {
        IdUtilisareur = idUtilisareur;
        NomUtilisateur = nomUtilisateur;
        MotPasseUtilisateur = motPasseUtilisateur;
        NiveauUtilisateur = niveauUtilisateur;
        FonctionUt = fonctionUt;
        ServiceAffe = serviceAffe;
        DepotAffecter = depotAffecter;
        DesignationUt = designationUt;
        Compte = compte;
        CaisseDepense = caisseDepense;
    }

    public int getIdUtilisareur() {
        return IdUtilisareur;
    }

    public String getNomUtilisateur() {
        return NomUtilisateur;
    }

    public String getMotPasseUtilisateur() {
        return MotPasseUtilisateur;
    }

    public String getNiveauUtilisateur() {
        return NiveauUtilisateur;
    }

    public String getFonctionUt() {
        return FonctionUt;
    }

    public String getServiceAffe() {
        return ServiceAffe;
    }

    public String getDepotAffecter() {
        return DepotAffecter;
    }

    public String getDesignationUt() {
        return DesignationUt;
    }

    public int getCompte() {
        return Compte;
    }

    public int getCaisseDepense() {
        return CaisseDepense;
    }

    public static void createSqlTable(SQLiteDatabase db){
        //creation de la table dans SQL LITE
        db.execSQL("CREATE TABLE IF NOT EXISTS `tUtilisateur` (\n" +
                "  `IdUtilisateur` INTEGER PRIMARY  KEY AUTOINCREMENT NOT NULL,\n" +
                "  `NomUtilisateur` varchar(90)  UNIQUE,\n" +
                "  `MotPasseUtilisateur` varchar(50) default(null),\n" +
                "  `NiveauUtilisateur` varchar(50) default(null),\n" +
                "  `FonctionUt` varchar(60) default(null),\n" +
                "  `ServiceAffe` varchar(60) default(null),\n" +
                "  `DepotAffecteer` varchar(60) default(null),\n" +
                "  `DesignationUt` varchar(60) default(null),\n" +
                "  `Compte` INTEGER default(null),\n" +
                "  `CaisseDepense` INTEGER default(null)\n" +
                ")");

    }

    public static boolean SQLinsertCreate(SQLiteDatabase db, Context context, tUtilisateur myObject){
        //insertion dans la table authentification telephone
        ContentValues contentValues =  DatabaseHandler.contentValuesFromHashMap(sendDataPost.parameters(myObject));
        long result = db.insertWithOnConflict(TABLE_NAME,null,
                contentValues,SQLiteDatabase.CONFLICT_IGNORE);
        if(result == -1){
            Log.e("Users","false");
            DatabaseHandler.getInstance(context).updateTABLEall(TABLE_NAME +
                            "",
                    PRIMARY_KEY,myObject.getNomUtilisateur()+"",contentValues);
            return false;
        }else {
            //db.close();
            return true;
        }
    }


    public static List<tUtilisateur> connexionSQLlite(Context context, String username, String password) {
        List<tUtilisateur> dataList = new ArrayList<tUtilisateur>();
        SQLiteDatabase db = DatabaseHandler.getInstance(context).getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE (NomUtilisateur = '"+username+"' OR NumPhone='"+username+"') AND MotPasseUtilisateur ='"+password+"'",null);
        while (cursor.moveToNext()){
            dataList.add(new tUtilisateur(
                    cursor.getInt(cursor.getColumnIndexOrThrow("IdUtilisareur")),
                    cursor.getString(cursor.getColumnIndexOrThrow("NomUtilisateur")),
                    cursor.getString(cursor.getColumnIndexOrThrow("MotPasseUtilisateur")),
                    cursor.getString(cursor.getColumnIndexOrThrow("NiveauUtilisateur")),
                    cursor.getString(cursor.getColumnIndexOrThrow("FonctionUt")),
                    cursor.getString(cursor.getColumnIndexOrThrow("ServiceAffe")),
                    cursor.getString(cursor.getColumnIndexOrThrow("DesignationUt")),
                    cursor.getString(cursor.getColumnIndexOrThrow("DepotAffecteer")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("Compte")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("CaisseDepense"))
            ));
        }
        cursor.close();
        db.close();
        return dataList;
    }



    /**
     *
     * connexion de l'utilsiateur dans la base de donnees
     * @param context
     * @param reponse
     * @return
     */
    public static List<tUtilisateur> getUserFromMysql(Context context, String reponse) {

        List<tUtilisateur>dataList =  new ArrayList<>();
        try {
            //si l'insertion a réussie on update la collonne etat upate dans lse serveur
//            JSONObject jsonObjectj = new JSONObject(reponse);
//            JSONArray jsonArray = jsonObjectj.getJSONArray("data");
            JSONArray jsonArray = new JSONArray(reponse);

            SQLiteDatabase db = DatabaseHandler.getInstance(context).getWritableDatabase();
            db.beginTransaction();

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                Gson gson = new Gson(); // Or use new GsonBuilder().create();
                tUtilisateur myObject = gson.fromJson(jsonObject1.toString(), tUtilisateur.class);

                //insertion de l'utiilisateur dans la base de donnees
                SQLinsertCreate(db,context,myObject);
                dataList.add(myObject);
            }

            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();

        } catch (JSONException e) {
            e.printStackTrace();
            reponse = e.toString();
        }

        return dataList;
    }
}
