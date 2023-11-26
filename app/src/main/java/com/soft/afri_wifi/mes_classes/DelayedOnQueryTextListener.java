package com.soft.afri_wifi.mes_classes;

import android.os.Handler;

import androidx.appcompat.widget.SearchView;

public abstract class DelayedOnQueryTextListener implements SearchView.OnQueryTextListener {

    private Handler handler = new Handler();
    private Runnable runnable;
    boolean canRun = true;

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(final String s) {


        if (s.length() >= 3) {

            if (canRun) {
                canRun = false;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        canRun = true;
                        DelayedOnQueryTextListener.this.onDelayerQueryTextChange(s);
                    }
                }, 500);
            }
        }else{
            return  false;
        }


//        handler.removeCallbacks(runnable);
//        runnable = new Runnable() {
//            @Override
//            public void run() {
//
//
//
//            }
//        };
//        handler.postDelayed(runnable, 500);
        return true;
    }

    public abstract void onDelayerQueryTextChange(String query);
}