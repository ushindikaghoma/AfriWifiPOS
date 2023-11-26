package com.soft.afri_wifi.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.soft.afri_wifi.R;

public class ContentMenuActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private ActionBarDrawerToggle mToogle;
    private DrawerLayout layout;
    final int ACTION_DECONNEXION = R.id.action_deconnexion;
    final  int ACTION_SYNCRONISER = R.id.action_sync;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_menu);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new FragmentHome()).commit();

        }
        bottomNavigationView.setBackground(null);


        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //View hView = navigationView.inflateHeaderView(R.layout.header_drawer);


        View hView = navigationView.inflateHeaderView(R.layout.header_drawer);
        TextView txt_nom_ustisiateur = hView.findViewById(R.id.txt_nom_ustisiateur);

        //txt_nom_ustisiateur.setText(currentUsers.getCurrentUsers(ContentMenuActivty.this).getNomUtilisateur());

//        TextView txt_mis_ajour = navigationView.findViewById(R.id.txt_date_mis_ajour);
//        txt_mis_ajour.setText("DERNIER MIS A JOUR : 2023-09-11");
//
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        layout = (DrawerLayout) findViewById(R.id.main_layout);
        mToogle = new ActionBarDrawerToggle(this, layout, R.string.open, R.string.close);
        layout.addDrawerListener(mToogle);
        mToogle.syncState();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.navigationHome)
                {
                    replaceFragment(new FragmentHome());
                }
                if(item.getItemId() == R.id.navigationSDashboard)
                {
                    replaceFragment(new FragmentDashboard());
                }


                return true;
            }
        });
    }
    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}