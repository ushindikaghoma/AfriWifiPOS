package com.soft.afri_wifi.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IssoCorp on 5/17/2017.
 */


public class DatabaseHandler extends SQLiteOpenHelper {


    public static final String DATABASE_NAME="afriwifi.db";
    Context context;

    public DatabaseHandler(Context context) {

        super(context, DATABASE_NAME, null, 1);
        this.context = context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean updateEtatUpload(String table_name, String collonne, String whereClauseCollone, String argument, int etatUpload){
            //cette methode permet la mis a jours de toutes les tables
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put(collonne,etatUpload);
        return db.update(table_name,contentValues,whereClauseCollone+" = ? ",new String[]{argument})>0 ;
    }


    public boolean updateTABLEall(String talble_name, String whereClauseCollone, String id, ContentValues contentValues){
            //cette methode permet la mis a jours de toutes les tables
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.update(talble_name,contentValues,whereClauseCollone+" = ? ",new String[]{id}) ;
        return result > 0;
    }

    public boolean updateEtatOperation(String table_name, String collonne, String whereClauseCollone, String argument, int valider){
        //cette methode permet la mis a jours de toutes les tables
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put(collonne,valider);
        return db.update(table_name,contentValues,whereClauseCollone+" = ? ",new String[]{argument})>0 ;
    }

    public boolean updateOperation(String TABLE_NAME, String COLONNE_NAME,  String numOperation) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLONNE_NAME, 1);

        return db.update(TABLE_NAME, values, "NumOperation = ?", new String[]{numOperation}) >0;
    }




    public Cursor getAuthPhone(String codeDepotOrIMEI){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM `authPhone` WHERE codeDepot = '"+codeDepotOrIMEI+"' OR IMEIPhone  = '"+codeDepotOrIMEI+"'",null);
        return cursor;
    }





    private static DatabaseHandler mInstance = null;

    public static synchronized DatabaseHandler getInstance(Context ctx){
        synchronized (DatabaseHandler.class){
            if(mInstance == null){
                mInstance = new DatabaseHandler(ctx.getApplicationContext());
                //Log.e("open",1++)
            }
            return  mInstance;
        }
    }


//    public static void importDB(Context context) {
//        try {
//           // File sd = Environment.getExternalStorageDirectory();
//            File sd = createFolders.databaseFolderExport();
//           // sd.mkdir();
//
//            if (sd.canWrite()) {
//                File backupDB = context.getDatabasePath(DatabaseHandler.DATABASE_NAME);
//                String backupDBPath = String.format("%s.bak", DatabaseHandler.DATABASE_NAME);
//                File currentDB = new File(sd, backupDBPath);
//
//                FileChannel src = new FileInputStream(currentDB).getChannel();
//                FileChannel dst = new FileOutputStream(backupDB).getChannel();
//                dst.transferFrom(src, 0, src.size());
//                src.close();
//                dst.close();
//
//                Toast.makeText(context,"Import success", Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }





//    public static void exportDB(Context context) {
//        try {
////            File sd = Environment.getExternalStorageDirectory();
//            File sd = createFolders.databaseFolderExport();
//
//
//            File data = Environment.getDataDirectory();
//
//            if (sd.canWrite()) {
//                String backupDBPath = String.format("%s.bak", DatabaseHandler.DATABASE_NAME);
//                File currentDB = context.getDatabasePath(DatabaseHandler.DATABASE_NAME);
//                File backupDB = new File(sd, backupDBPath);
//
//                FileChannel src = new FileInputStream(currentDB).getChannel();
//                FileChannel dst = new FileOutputStream(backupDB).getChannel();
//                dst.transferFrom(src, 0, src.size());
//                src.close();
//                dst.close();
//
//                Toast.makeText(context,"Export success", Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public static void exportDBD(Context context) {
//        try {
////            File sd = Environment.getExternalStorageDirectory();
//            File data = createFolders.databaseFolderExport();
//
//
//
//
////            File data = Environment.getDataDirectory();
//
//            if (data.canWrite()) {
//                String backupDBPath = String.format("%s.bak", DatabaseHandler.DATABASE_NAME);
//                File currentDB = context.getDatabasePath(DatabaseHandler.DATABASE_NAME);
//                File backupDB = new File(data, backupDBPath);
//
//                FileChannel src = new FileInputStream(currentDB).getChannel();
//                FileChannel dst = new FileOutputStream(backupDB).getChannel();
//                dst.transferFrom(src, 0, src.size());
//                src.close();
//                dst.close();
//
//                Toast.makeText(context,"Export success", Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            Log.e("dd",e.getMessage());
//            e.printStackTrace();
//        }
//    }



    public static void exportDatabse(Context context)
    {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//"+context.getPackageName()+"//databases//"+ DatabaseHandler.DATABASE_NAME+"";
                String backupDBPath = "salespos.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {

        }
    }




    public static   ContentValues contentValuesFromHashMap(HashMap<String, String> values) {

        ContentValues storeValues = new ContentValues();

        int count = 0;
        for(Map.Entry<String, String> entry : values.entrySet()) {
            String column = entry.getKey();
            if(!column.equals("id") && !column.equals("solde") && !column.equals("idService")){
                storeValues.put(column, (String) entry.getValue());
                // Log.e(count+"",column+" : " +entry.getValue());
            }

        }
        return storeValues;
    }


    public static   ContentValues contentValuesFromHashMapOperation(HashMap<String, String> values) {

        ContentValues storeValues = new ContentValues();

        int count = 0;
        for(Map.Entry<String, String> entry : values.entrySet()) {
            String column = entry.getKey();
            if(!column.equals("id") && !column.equals("solde") && !column.equals("NumOpSource")){
                storeValues.put(column, (String) entry.getValue());
                // Log.e(count+"",column+" : " +entry.getValue());
            }

        }
        return storeValues;
    }




    /**
     * recuperation des toutes les donnes d'une table
     * @param db
     * @param tableName
     * @return
     */
    public static Cursor all(SQLiteDatabase db,String tableName) {
        return   db.rawQuery("SELECT *  FROM '"+tableName+"'",null);

    }

    /**
     *
     * find element by reference wherecolause
     * @param db
     * @param tableName
     * @param whereClause
     * @param id
     * @return
     */
    public static Cursor find(SQLiteDatabase db,String tableName,String whereClause,String id) {
        String req = "SELECT *  FROM "+tableName+"  WHERE "+whereClause+" = ?";
        return   db.rawQuery(req,new String[]{id});
    }


    /**
     *
     * @param db
     * @param tableName
     * @param dateWhereClause
     * @param date_debut
     * @param date_fin
     * @return
     *
     * reuperation des donnees par intervalle
     */
    public static Cursor intervalle(SQLiteDatabase db,String tableName,String dateWhereClause,String date_debut,String date_fin) {
        return   db.rawQuery("SELECT *  FROM "+tableName+" WHERE "+dateWhereClause+" BETWEEN ? and ?",new String[]{date_debut,date_fin});

    }

    public static boolean delete(SQLiteDatabase db,String tableName,String whereClause,String id){
        int result =  db.delete(tableName,whereClause+" = ? ",new String[]{id});

        if(result > 0){
            return true;
        }
        return false;
    }

    public static boolean deleteAll(SQLiteDatabase db,String tableName){
        int result =  db.delete(tableName,null,null);

        if(result > 0){
            return true;
        }
        return false;
    }


    public static JSONArray cur2Json(Cursor cursor) {
        //http://stackoverflow.com/questions/13070791/android-cursor-to-jsonarray
        JSONArray resultSet = new JSONArray();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            final int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();
            int i;// = 0;
            for (  i = 0; i < totalColumn; i++) {

                if (cursor.getColumnName(i) != null) {
                    try {
                        String getcol = cursor.getColumnName(i),
                                getstr = cursor.getString(i);


                        //mLog.error("ColumnName(i):\t " + getcol + "\t: " + getstr);
                        rowObject.put(
                                getcol,
                                getstr
                        );

                    } catch (JSONException e) {
                        Log.e("", e.getMessage());
                    }
                }
            }//for
            Log.e("Log","columns i:\t " + i + "\totalColumn:\t " + totalColumn);
            resultSet.put(rowObject);
            cursor.moveToNext();
        }

        return resultSet;
    }//cur2Json
}

