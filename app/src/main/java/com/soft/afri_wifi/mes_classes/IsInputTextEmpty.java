package com.soft.afri_wifi.mes_classes;


import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

/**
 * Created by IssoNet on 11/26/2018.
 */

public class IsInputTextEmpty {


    public static boolean isEditeTextEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() == 0 || etText.getText().toString().matches("")){
            etText.setError("Veuillez completer c'est champs");
            return true;
        }

        return false;
    }


    public static boolean isEditeTextEmpty(TextView etText) {
        if (etText.getText().toString().trim().length() == 0 || etText.getText().toString().matches("")){
            etText.setError("Please complete this field");
            return true;
        }

        return false;
    }

    public static boolean isEditeTextEmpty(TextInputEditText etText) {
        if (etText.getText().toString().trim().length() == 0 || etText.getText().toString().matches("")){
            etText.setError("Veuillez completer c'est champs");
            return true;
        }

        return false;
    }
    public static boolean isEditeTextEmpty( AutoCompleteTextView etText) {
        if (etText.getText().toString().trim().length() == 0 || etText.getText().toString().matches("")){
            etText.setError("Veuillez completer c'est champs");
            return true;
        }

        return false;
    }

}
