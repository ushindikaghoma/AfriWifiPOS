package com.soft.afri_wifi.mes_classes;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class snackView {

    public static void display(View view, String message, int duration){
        Snackbar.make(view, message, duration)
                .setAction("Action", null).show();
    }
}
