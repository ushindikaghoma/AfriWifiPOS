package com.soft.afri_wifi.mes_classes;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class errorMessage {
    public static  void showDialog(Context context, String message){
        final AlertDialog alertDialogDialogg = new AlertDialog.Builder(context).create();
        alertDialogDialogg.setTitle("Message d'erreur");
        alertDialogDialogg.setMessage("\n\n"+message+"\n\n");
        alertDialogDialogg.setButton(DialogInterface.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialogDialogg.dismiss();
            }
        });

        errorSound.playSound(context);
        alertDialogDialogg.show();
    }
}
