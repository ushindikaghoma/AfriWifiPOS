package com.soft.afri_wifi.mes_classes;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

public class getIMEIPhone {

    //CETTE CLASSE PERMET DE RECUPERER L'IMEI DU TELEPHONE

//    private String android_id = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

    @SuppressLint("MissingPermission")
    public static String getIMEITelephone(Context context){

        String deviceId;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
//            ActivityCompat.requestPermissions(context,new String[] {Manifest.permission.READ_PHONE_STATE },1);
//            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                deviceId = Settings.Secure.getString(
//                        context.getContentResolver(),
//                        Settings.Secure.ANDROID_ID);
//            } else {
//                final TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//                if (mTelephony.getDeviceId() != null) {
//                    deviceId = mTelephony.getDeviceId();
//                } else {
//                    deviceId = Settings.Secure.getString(
//                            context.getContentResolver(),
//                            Settings.Secure.ANDROID_ID);
//                }
//            }
            deviceId ="";
        }else{
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                deviceId = Settings.Secure.getString(
                        context.getContentResolver(),
                        Settings.Secure.ANDROID_ID);
            } else {
                final TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                if (mTelephony.getDeviceId() != null) {
                    deviceId = mTelephony.getDeviceId();
                } else {
                    deviceId = Settings.Secure.getString(
                            context.getContentResolver(),
                            Settings.Secure.ANDROID_ID);
                }
            }

//            idPhone = Build.getSerial();
        }




//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            deviceId = Settings.Secure.getString(
//                    context.getContentResolver(),
//                    Settings.Secure.ANDROID_ID);
//        } else {
//            final TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//            if (mTelephony.getDeviceId() != null) {
//                deviceId = mTelephony.getDeviceId();
//            } else {
//                deviceId = Settings.Secure.getString(
//                        context.getContentResolver(),
//                        Settings.Secure.ANDROID_ID);
//            }
//        }

        return deviceId;

//        String simSerialNo="";
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
//
//            SubscriptionManager subsManager = (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
//
//            List<SubscriptionInfo> subsList = subsManager.getActiveSubscriptionInfoList();
//
//            if (subsList!=null) {
//                for (SubscriptionInfo subsInfo : subsList) {
//                    if (subsInfo != null) {
//                        simSerialNo  = subsInfo.get();
//                    }
//                }
//            }
//        } else {
//            TelephonyManager tMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//            simSerialNo = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
//        }
//
//        return  simSerialNo;
        //return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);


    }
}
