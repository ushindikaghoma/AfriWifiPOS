package com.soft.afri_wifi.mes_classes;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class permissionREQUEST {

//    CETTE CLASSE PERMET DE DEMANDE L'ACCESS A UNE PERMISSION

    public static void askForPermission(Activity context, String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {

                ActivityCompat.requestPermissions(context, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(context, new String[]{permission}, requestCode);
            }
        } else {
            // Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }
}
