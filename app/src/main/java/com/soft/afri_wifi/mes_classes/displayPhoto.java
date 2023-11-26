package com.soft.afri_wifi.mes_classes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class displayPhoto {
    public static Bitmap getImageFromString(String encodedImage){
        try {
            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return decodedByte;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
