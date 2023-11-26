package com.soft.afri_wifi.mes_classes;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;

public abstract class DelayedOnQueryTextListenerEditText implements TextWatcher {

    private Handler handler = new Handler();
    private Runnable runnable;
    boolean canRun = true;


    public abstract void onDelayerQueryTextChange(String query);

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (count >= 3) {

            if (canRun) {
                canRun = false;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        canRun = true;
                        DelayedOnQueryTextListenerEditText.this.onDelayerQueryTextChange(s.toString());
                    }
                }, 500);
            }
        }else{
            //return  false;
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}