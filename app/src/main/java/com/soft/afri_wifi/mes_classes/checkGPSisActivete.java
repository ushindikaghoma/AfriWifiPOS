package com.soft.afri_wifi.mes_classes;

import android.content.Context;
import android.location.LocationManager;

public class checkGPSisActivete {

    //Check GPS Status true/false
    public static boolean checkGPSStatus(Context context){
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE );
        boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return statusOfGPS;
    }

}
