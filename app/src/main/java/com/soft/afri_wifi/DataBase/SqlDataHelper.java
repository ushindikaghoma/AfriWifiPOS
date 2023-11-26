package com.soft.afri_wifi.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlDataHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "guesthousehouse.db";

    //IP table
    public static final String adresseTable = "tableIP";
    public static  final String ID_IP = "ID" ;
    public static final String ADRESS_IP = "ADRESSE";



    public SqlDataHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ adresseTable+"("+ID_IP+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ADRESS_IP+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+adresseTable);
        onCreate(sqLiteDatabase);
    }

    // My methodes for the IP table

    public boolean insertAdresse (String adresse)
    {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ADRESS_IP, adresse);

        long result = sqLiteDatabase.insert(adresseTable, null, contentValues);

        if(result == -1){

            return false;

        }else {

            return  true;
        }
    }

    // Modification de l adresse ip

    public boolean updateAdresse(String newAdress){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ADRESS_IP, newAdress);
        db.update(adresseTable, contentValues, "1", null);
        return true;
    }

    // Get l adresse actuelle

    public Cursor getCurrentAdress(){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor res = sqLiteDatabase.rawQuery("select * from " +adresseTable, null);

        return res;
    }
}
