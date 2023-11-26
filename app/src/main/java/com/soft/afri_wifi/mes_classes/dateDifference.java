package com.soft.afri_wifi.mes_classes;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class dateDifference {
    public static long getDifferenceDaysss(String date1, String date2) {

//        //Parsing the date
//        LocalDate dateBefore = LocalDate.parse(date1);
//        LocalDate dateAfter = LocalDate.parse(date2);
//
//        //calculating number of days in between
//        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        return  999;
    }

    public static long getDifferenceDays(String date11, String date22) {

        //Parsing the date
    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
    long noOfDaysBetween = 0;
    try {
        Date date1 = myFormat.parse(date11);
        Date date2 = myFormat.parse(date22);
        long diff = date2.getTime() - date1.getTime();
        noOfDaysBetween = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
    } catch (ParseException e) {
        e.printStackTrace();
    }
        Log.e("33", TimeUnit.DAYS.convert(noOfDaysBetween, TimeUnit.MILLISECONDS)+"");

        return  noOfDaysBetween;
    }


    public static boolean checkCurrentHeureBetwween(){
        boolean check = false;
        try {
            String string1 = "17:00:00";
            Date time1 = new SimpleDateFormat("HH:mm:ss").parse(string1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);
            calendar1.add(Calendar.DATE, 1);


            String string2 = "23:59:59";
            Date time2 = new SimpleDateFormat("HH:mm:ss").parse(string2);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.add(Calendar.DATE, 1);

            String currentTime = new date_du_jour().getTime();
            Date d = new SimpleDateFormat("HH:mm:ss").parse(currentTime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(d);
            calendar3.add(Calendar.DATE, 1);

            Date x = calendar3.getTime();
            if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
                //checkes whether the current time is between 14:49:00 and 20:11:13.
                System.out.println(true);
                check = true;
                Log.e("heure","Justein ");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return check;
    }
}
