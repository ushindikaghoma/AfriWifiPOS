package com.soft.afri_wifi.mes_classes;

import java.util.Calendar;
import java.util.Date;

public class dateTimeLongCurrent {


//   public static void main (String [] arg) {
//       System.out.println(getTimeNow());
//   }

    public static String getTimeNow(){
        // creating a Calendar object
        Calendar c1 = Calendar.getInstance();

        // creating a date object with specified time.
        Date dateOne = c1.getTime();

       // System.out.println(dateOne.getTime());

        return String.valueOf(dateOne.getTime());
    }
}
