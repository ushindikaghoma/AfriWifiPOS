package com.soft.afri_wifi.mes_classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IssoNet on 11/25/2018.
 */

public class date_du_jour {
    String formateDate;
    String datetime;
    int years;
    int semaine_enCours;
    public date_du_jour(){
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        formateDate = df.format(time);
        this.years = time.getYear();

    }
    public String getDatee(){
        return this.formateDate;
    }

    public String getDatetime(){
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formateDate = df.format(time);

        return formateDate;
    }
 public String getCurrentYear(){
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String formateDate = df.format(time);

        return formateDate;
    }

    public String getTime(){
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String formateDate = df.format(time);

        return formateDate;
    }

    public int getYears(){
        return this.years;
    }

    public String getTheFirstDateOfCurrentfWeek(){
        //recuperation du premier jour du
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        calendar.set(Calendar.DAY_OF_WEEK,calendar.getFirstDayOfWeek());


        //calendar.add(Calendar.WEEK_OF_YEAR,1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formateDate = df.format(calendar.getTime());

        return formateDate;
    }

    public String getTheLastDayOftheNextWeek(){
        //recuperation du premier jour du
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        calendar.set(Calendar.DAY_OF_WEEK,calendar.getFirstDayOfWeek());


        calendar.add(Calendar.WEEK_OF_YEAR,1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formateDate = df.format(calendar.getTime());

        return formateDate;
    }


    public String getFirstDayOfCurrentTheMonth(){
        //recuperation du premier jour du
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        calendar.set(Calendar.DAY_OF_MONTH,1);
//        int mDay =  calendar.getFirstDayOfWeek();
//        int lastDay = calendar.get(Calendar.);
        //calendar.add(Calendar.MONTH,1);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formateDate = df.format(calendar.getTime());
        return formateDate;
    }

    public String getFistDayOfTheNextMonth(){
        //recuperation du premier jour du
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        calendar.set(Calendar.DAY_OF_MONTH,1);
//        int mDay =  calendar.getFirstDayOfWeek();
//        int lastDay = calendar.get(Calendar.);
        calendar.add(Calendar.MONTH,1);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formateDate = df.format(calendar.getTime());
        return formateDate;
    }


    public String getFistDayOfTheCurrentYears(){
        //recuperation du premier jour du
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        calendar.set(Calendar.DAY_OF_YEAR,1);
//        int mDay =  calendar.getFirstDayOfWeek();
//        int lastDay = calendar.get(Calendar.);
       // calendar.add(Calendar.MONTH,1);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formateDate = df.format(calendar.getTime());
        return formateDate;
    }

    public String getFistDayOfTheNextYears(){
        //recuperation du premier jour du
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        calendar.set(Calendar.DAY_OF_YEAR,1);
//        int mDay =  calendar.getFirstDayOfWeek();
//        int lastDay = calendar.get(Calendar.);
         calendar.add(Calendar.YEAR,1);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formateDate = df.format(calendar.getTime());
        return formateDate;
    }


    public int getCurrentWeek(String input) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date datee =  df.parse(input);
        Calendar cal = Calendar.getInstance();
        cal.setTime(datee);
        int week = cal.get(Calendar.WEEK_OF_YEAR);

       return  week;
    }

    public long getDateTimstamps(){
        Date date= new Date();
        //getTime() returns current time in milliseconds
        long time = date.getTime();

        return time;
    }

}
