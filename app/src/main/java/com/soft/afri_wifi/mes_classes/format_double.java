package com.soft.afri_wifi.mes_classes;

import java.text.DecimalFormat;

/**
 * Created by IssoNet on 12/4/2018.
 */

public class format_double
{
     public static String getDoubleFormated(double value){
        String valeur = new DecimalFormat("###,###,###,###,###,###,###.##").format(value);
        return valeur;
    }
    public static String   getDoubleFormat(double value)
    {
        String valeur = new DecimalFormat("#,###.####").format(value);
        return valeur;
    }

    public static String getDoubleFormatedAsDouble(double value){
        String valeur = new DecimalFormat("#####################.##").format(value);
        return valeur;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
