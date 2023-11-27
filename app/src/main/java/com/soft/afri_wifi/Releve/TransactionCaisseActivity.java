package com.soft.afri_wifi.Releve;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.soft.afri_wifi.Compte.data.CompteRepository;
import com.soft.afri_wifi.Compte.data.CompteResponse;
import com.soft.afri_wifi.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionCaisseActivity extends AppCompatActivity {

    CompteRepository compteRepository;
    AdapterReleve adapterReleve;
    List<CompteResponse> list_local_releve;
    Calendar calendar;
    EditText date_debut, date_fin;
    String todayDate, num_compte_caisse_user;

    ProgressBar progress_load_balance, progress_load_transaction;
    TextView display_balance;
    RecyclerView recycler_liste_balance;
    SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    String pref_code_depot, pref_compte_user, pref_compte_stock_user,nom_user, pref_mode_type,
            prefix_operation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_caisse);

        //this.getSupportActionBar().setTitle("Transactions caisse");

        preferences = getSharedPreferences("maPreference", MODE_PRIVATE);
        editor = preferences.edit();


        pref_code_depot = preferences.getString("pref_depot_user","");
        pref_compte_user = preferences.getString("pref_compte_user","");
        nom_user = preferences.getString("pref_nom_user","");
        pref_compte_stock_user = preferences.getString("pref_compte_stock_user","");
        pref_mode_type = preferences.getString("pref_mode_type","");

        compteRepository = CompteRepository.getInstance();
        adapterReleve = new AdapterReleve(this);
        date_debut = findViewById(R.id.transaction_date_debut);
        date_fin = findViewById(R.id.transaction_date_fin);
        progress_load_balance = findViewById(R.id.transaction_progress_balance);
        progress_load_transaction = findViewById(R.id.transaction_progress_recycle);
        display_balance = findViewById(R.id.transaction_display_balance);
        recycler_liste_balance = findViewById(R.id.all_transaction_recycle);

        recycler_liste_balance.setHasFixedSize(true);
        recycler_liste_balance.setLayoutManager(new LinearLayoutManager(this));

        num_compte_caisse_user = getIntent().getStringExtra("num_compte");

        calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        todayDate = format.format(calendar.getTime());

        date_debut.setText(todayDate);
        date_fin.setText(todayDate);

        LoadSoldeCaisse(Integer.parseInt(pref_compte_user), progress_load_balance, display_balance);

        LoadListeReleveCompte(progress_load_transaction, recycler_liste_balance, Integer.parseInt(pref_compte_user),
                "2010-01-01", todayDate);
        date_debut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(

                        TransactionCaisseActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
//                                if(day>9 && month>9)date_debut.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                if(dayOfMonth>9 && month>9)date_debut.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                if(dayOfMonth>9 && !(month>9))date_debut.setText(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);
                                if(!(dayOfMonth>9) && month>9)date_debut.setText(year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth);
                                if(!(dayOfMonth>9) && !(month>9))date_debut.setText(year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth);
                                //date_debut.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        date_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(

                        TransactionCaisseActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                if(dayOfMonth>9 && month>9)date_fin.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                if(dayOfMonth>9 && !(month>9))date_fin.setText(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);
                                if(!(dayOfMonth>9) && month>9)date_fin.setText(year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth);
                                if(!(dayOfMonth>9) && !(month>9))date_fin.setText(year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth);
                                //date_fin.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        year, month, day);

                LoadListeReleveCompte(progress_load_transaction, recycler_liste_balance, Integer.parseInt(num_compte_caisse_user),
                        date_debut.getText().toString(), date_fin.getText().toString());

                datePickerDialog.show();
            }
        });


    }

    public void LoadSoldeCaisse(int numCompte, ProgressBar loadSolde, TextView displaySoldeCaisse)
    {
        Call<Double> call_solde_compte = compteRepository.compteConnexion().getSoldeCaisse(numCompte);
        loadSolde.setVisibility(View.VISIBLE);
        call_solde_compte.enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                if (response.isSuccessful())
                {
                    loadSolde.setVisibility(View.GONE);
                    displaySoldeCaisse.setText(String.format("CDF %s", response.body()));
                }
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {
                loadSolde.setVisibility(View.GONE);
            }
        });
    }

    public void LoadListeReleveCompte(ProgressBar loadTransactions, RecyclerView listeReleve,
                                      int numCompte, String date_debut, String date_fin)
    {
        Call<List<CompteResponse>> call_liste_releve = compteRepository.compteConnexion().getReleveCompteParDate(numCompte, date_debut, date_fin);
        loadTransactions.setVisibility(View.VISIBLE);
        call_liste_releve.enqueue(new Callback<List<CompteResponse>>() {
            @Override
            public void onResponse(Call<List<CompteResponse>> call, Response<List<CompteResponse>> response) {
                if (response.isSuccessful())
                {
                    loadTransactions.setVisibility(View.GONE);
                    double _sortie_totale = 0;
                    double _achat_total = 0;
                    list_local_releve = new ArrayList<>();
                    for (int a = 0; a < response.body().size(); a++)
                    {
                        CompteResponse liste_releve =
                                new CompteResponse (
                                        response.body().get(a).getLibelle(),
                                        response.body().get(a).getDateOperation(),
                                        response.body().get(a).getDebit(),
                                        response.body().get(a).getCredit(),
                                        response.body().get(a).getSolde()

                                );

                        _achat_total += response.body().get(a).getCredit();

//                        textView_solde_jour.setText(String.format("$%s", response.body().get(a).getSolde()));
//                        textView_solde_achat.setText(String.format("$%s", _achat_total));

                        list_local_releve.add(liste_releve);
                    }
                    adapterReleve.setList(list_local_releve);
                    listeReleve.setAdapter(adapterReleve);

                }
            }

            @Override
            public void onFailure(Call<List<CompteResponse>> call, Throwable t) {
                loadTransactions.setVisibility(View.GONE);
            }
        });
    }
}