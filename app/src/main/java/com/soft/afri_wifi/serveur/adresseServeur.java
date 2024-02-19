package com.soft.afri_wifi.serveur;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;




public class adresseServeur {
    private String adresseIP;
    private Context context;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

//    public static String adresseIPNEW = "http://192.168.1.108:8081/taxeWebApi/";
    public static String adresseIPNEW = "http://afrisofttech-001-site52.btempurl.com/";


    public adresseServeur(Context context) {
        this.context = context;
      //  sharedPref =  context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

       /// adresseIP = "http://"+sharedPref.getString(settingsKeys.AdresseServeurKey,"");
       // adresseIP ="http://192.168.11.2:8081";
        adresseIP ="http://192.168.126.162:8081";
        adresseIP ="http://192.168.1.100/AfriWifi";
        adresseIP ="http://afrisofttech-002-site38.btempurl.com";
//        adresseIP ="http://192.168.252.43/TouchBistroIshango";
        //adresseIP ="http://192.168.1.44/AfriWifi";
    }

    public String getAdresseIP() {

        return adresseIP;
    }

    public adresseServeur(String adresseIP, Context context) {
        this.adresseIP = adresseIP;
        this.context = context;
        this.sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        this.editor =  sharedPref.edit();
    }

    public void setAdresseIP(String adresseIP) {
        this.adresseIP = adresseIP;
       // editor.putString(settingsKeys.AdresseServeurKey,adresseIP);
        editor.apply();
        editor.commit();
    }
}
