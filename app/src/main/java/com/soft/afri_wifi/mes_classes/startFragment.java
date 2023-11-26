package com.soft.afri_wifi.mes_classes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class startFragment {

    public static void startFraggement(AppCompatActivity context , Fragment fragment){
        FragmentTransaction fragmentTransaction5 = context.getSupportFragmentManager().beginTransaction();
       // fragmentTransaction5.replace(R.id.frame_contentt, fragment,"FragmentName");
        fragmentTransaction5.commit();
    }

}
