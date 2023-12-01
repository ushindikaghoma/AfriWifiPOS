package com.soft.afri_wifi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.soft.afri_wifi.DataBase.DatabaseHandler;
import com.soft.afri_wifi.Login.LoginActivity;
import com.soft.afri_wifi.Menu.ContentMenuActivity;
import com.soft.afri_wifi.Models.tUtilisateur;

public class MainActivity extends AppCompatActivity {

    CardView cardOffline, cardOnline;
    SharedPreferences shared;
    SharedPreferences.Editor editorr;
    String adresseIP,val,authLogin, mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        shared = getSharedPreferences("maPreference", Context.MODE_PRIVATE);
        editorr = shared.edit();
        val = shared.getString("isFirstLogin", "");
        authLogin = shared.getString("authLogin", "");
        adresseIP = shared.getString("adresseServeur","");

        //this.getSupportActionBar().hide();

        //creation des table de la base de donne
        tUtilisateur.createSqlTable(DatabaseHandler.getInstance(this).getWritableDatabase());

        Thread myThread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(5000);

                    if(authLogin.length() == 0){
                        //utilisateu pages login
                        finish();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));

                    }else{
                        //page d'acceuil
                        finish();
                        Intent intent = new Intent(getApplicationContext(), ContentMenuActivity.class);
                        startActivity(intent);

                    }

//                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        myThread.start();
    }

}