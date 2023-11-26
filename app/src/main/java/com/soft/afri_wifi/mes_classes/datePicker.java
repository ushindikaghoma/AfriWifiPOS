package com.soft.afri_wifi.mes_classes;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import androidx.core.app.ActivityCompat;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class datePicker  {

    public static void dialogDatePerido(final Activity context, final TextInputEditText ed_date){
        final Calendar c = Calendar.getInstance();
        final int mYear= c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);
        final String[] date_intervalle = new String[1];


        DatePickerDialog datePickerDialog = new DatePickerDialog(context, android.R.style.Theme_Material_Dialog,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        //Toast.makeText(getContext(),i + "-" + (i1 + 1) + "-" + i2,Toast.LENGTH_SHORT).show();
                        // = i + "-" + (i1 + 1) + "-0" + i2;
                        String myFormat = "yyyy-MM-dd";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR,i);
                        calendar.set(Calendar.MONTH,i1);
                        calendar.set(Calendar.DAY_OF_MONTH,i2);

                        String dateDut = simpleDateFormat.format(calendar.getTime());
                        ed_date.setText(dateDut);

                    }
                }, mYear, mMonth, mDay);
        //datePickerDialog.setTitle(getResources().getString(R.string.dateDebut));
//        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.getDatePicker().setCalendarViewShown(true);
        datePickerDialog.getDatePicker().setSpinnersShown(false);
        datePickerDialog.show();

    }


    public static void isSingleDate(ActivityCompat context) {

       // FragmentManager fm = context.();
        long today;
        Calendar calendar;
        today = MaterialDatePicker.todayInUtcMilliseconds();
        final MaterialDatePicker.Builder singleDateBuilder = MaterialDatePicker.Builder.datePicker();
        singleDateBuilder.setTitleText("CHOISIR DATE");
        singleDateBuilder.setSelection(today);
        final MaterialDatePicker materialDatePicker = singleDateBuilder.build();
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
            }
        });
      //  materialDatePicker.show(context.getSupportFragmentManager(), "DATE");
    }
}
