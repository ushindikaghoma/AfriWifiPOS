package com.soft.afri_wifi.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.soft.afri_wifi.DataBase.ConnexionAPI;
import com.soft.afri_wifi.DataBase.DataFromAPI;
import com.soft.afri_wifi.DataBase.DatabaseHandler;
import com.soft.afri_wifi.DataBase.SqlDataHelper;
import com.soft.afri_wifi.Depot.data.DepotRepository;
import com.soft.afri_wifi.Menu.ContentMenuActivity;
import com.soft.afri_wifi.Models.currentUsers;
import com.soft.afri_wifi.Models.tUtilisateur;
import com.soft.afri_wifi.R;
import com.soft.afri_wifi.serveur.me_URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button connecter ;
    TextView newAccount;
    EditText phone_edt, password_edt;
    ProgressDialog alertDialog;

    SqlDataHelper sqlDataHelper;
    String adresseIP, phone_number_user, password_user,
            fonction_user, service_affecte_user, compte_affecte_user, niveau_user,depot_affecte_user,
            getPhoneUser, getPasswordUser, nom_user, reponse_connection,
            compte_depense, compte_stock_affecte, mode_type, pref_mode_type;
    AlertDialog dialogSettings = null;
    DataFromAPI dataFromAPI;

    //Exercice

    Button test;

    SharedPreferences preferences;
    public static SharedPreferences.Editor editor;

    DepotRepository depotRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        depotRepository = DepotRepository.getInstance();
        Button connecter  = findViewById(R.id.cirLoginButton);
        EditText edtUserName = findViewById(R.id.editTextUserName);
        EditText edtPassword = findViewById(R.id.editTextPassword);

        sqlDataHelper = new SqlDataHelper(this);
        dataFromAPI = new DataFromAPI(this);



        preferences = getSharedPreferences("maPreference", MODE_PRIVATE);
        editor = preferences.edit();
        connecter.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edtUserName.getText().toString().trim()))
                {
                    edtUserName.setError("Saisir le user name");
                    edtUserName.requestFocus();
                    return;
                }else
                {
                    getPhoneUser = edtUserName.getText().toString();
                    getPasswordUser = edtPassword.getText().toString();
//
//

                        new connexionClass(edtUserName.getText().toString(),edtPassword.getText().toString()).execute();
                        //Toast.makeText(LoginActivity.this, ""+dataFromAPI.GetComptStockAffecte("DP1"), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public class AsynLoginUserStation extends AsyncTask<Void, Void, Void>
    {
        Context context;
        String phone_number;

        public AsynLoginUserStation(Context context, String phone_number) {
            this.context = context;
            this.phone_number = phone_number;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            alertDialog = new ProgressDialog(context);
//
//            alertDialog.setCancelable(false);
//            alertDialog.setMessage("Connection en cours...");
//            alertDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            alertDialog.show();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            //Toast.makeText(LoginActivity.this, ""+niveau_user+" "+nom_user+""+phone_number, Toast.LENGTH_SHORT).show();

            if (niveau_user.replace(" ","%20").equals("client"))
            {
                //startActivity(new Intent(getApplicationContext(), MainActivityClient.class)
                startActivity(new Intent(getApplicationContext(), ContentMenuActivity.class)
                        .putExtra("phoneuser", phone_number_user)
                        .putExtra("nomuser", nom_user)
                        .putExtra("fonctionuser", fonction_user)
                        .putExtra("serviceuser", service_affecte_user)
                        .putExtra("compteaffecte", compte_affecte_user)
                        .putExtra("depotaffecte", depot_affecte_user)
                        .putExtra("compte_stock_user", compte_stock_affecte));


                editor.putString("pref_nom_user",nom_user);
                editor.putString("pref_foncion_user",fonction_user);
                editor.putString("pref_service_user",service_affecte_user);
                editor.putString("pref_compte_user",compte_affecte_user);
                editor.putString("pref_depot_user",depot_affecte_user);
                editor.putString("pref_compte_stock_user", compte_stock_affecte);
                editor.putString("pref_compte_depense_user", compte_depense);

                editor.commit();
                editor.apply();

                finish();
            }
            else if(niveau_user.replace(" ","%20").equals("Admin"))
            {
                startActivity(new Intent(getApplicationContext(), ContentMenuActivity.class)
                        //startActivity(new Intent(getApplicationContext(), MainMenuStation.class)
                        .putExtra("phoneuser", phone_number_user)
                        .putExtra("nomuser", nom_user)
                        .putExtra("fonctionuser", fonction_user)
                        .putExtra("serviceuser", service_affecte_user)
                        .putExtra("compteaffecte", compte_affecte_user)
                        .putExtra("depotaffecte", depot_affecte_user)
                        .putExtra("compte_stock_user", compte_stock_affecte));

                editor.putString("pref_nom_user",nom_user);
                editor.putString("pref_foncion_user",fonction_user);
                editor.putString("pref_service_user",service_affecte_user);
                editor.putString("pref_compte_user",compte_affecte_user);
                editor.putString("pref_depot_user",depot_affecte_user);
                editor.putString("pref_compte_stock_user", compte_stock_affecte);
                editor.putString("pref_compte_depense_user", compte_depense);

                editor.commit();
                editor.apply();

                finish();
            }
            else if (niveau_user.replaceAll("\\s+$","").equals("Caisse"))
            {
//                startActivity(new Intent(getApplicationContext(), MainMenuStation.class)
                startActivity(new Intent(getApplicationContext(), ContentMenuActivity.class)
                        .putExtra("phoneuser", phone_number_user)
                        .putExtra("nomuser", nom_user)
                        .putExtra("fonctionuser", fonction_user)
                        .putExtra("serviceuser", service_affecte_user)
                        .putExtra("compteaffecte", compte_affecte_user)
                        .putExtra("depotaffecte", depot_affecte_user)
                        .putExtra("compte_stock_user", compte_stock_affecte));

                editor.putString("pref_nom_user",nom_user);
                editor.putString("pref_foncion_user",fonction_user);
                editor.putString("pref_service_user",service_affecte_user);
                editor.putString("pref_compte_user",compte_affecte_user);
                editor.putString("pref_depot_user",depot_affecte_user);
                editor.putString("pref_compte_stock_user", compte_stock_affecte);
                editor.putString("pref_compte_depense_user", compte_depense);

                editor.commit();
                editor.apply();

                finish();

            }

            //Toast.makeText(context, ""+fonction_user, Toast.LENGTH_SHORT).show();

            finish();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            JSONArray jsonArray;

            try
            {
                jsonArray = new JSONArray(dataFromAPI.GetLogin(phone_number));

                for (int i =0; i<jsonArray.length(); i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

//                    phone_number_user = jsonObject.optString("numPhone");
                    nom_user = jsonObject.optString("NomUtilisateur");
                    password_user = jsonObject.optString("MotPasseUtilisateur");
                    fonction_user = jsonObject.optString("FonctionUt");
                    niveau_user = jsonObject.optString("NiveauUtilisateur");
                    service_affecte_user = jsonObject.optString("ServiceAffe");
                    compte_affecte_user = jsonObject.optString("Compte");
                    depot_affecte_user = jsonObject.optString("DepotAffecter");
                    compte_depense = jsonObject.optString("CaisseDepense");
                }

            } catch (JSONException e)
            {

            }

            compte_stock_affecte = dataFromAPI.GetComptStockAffecte(depot_affecte_user);

            return null;
        }
    }

    // Async Task Verifier number

    public class AsynIsNumberExist extends AsyncTask<Void, Void, Void>
    {
        Context context;
        String phone_number, password;

        public AsynIsNumberExist(Context context, String phone_number, String password) {
            this.context = context;
            this.phone_number = phone_number;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            alertDialog = new ProgressDialog(context);

            alertDialog.setCancelable(false);
            alertDialog.setMessage("Connection en cours...");
            alertDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            alertDialog.show();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            alertDialog.dismiss();

            Toast.makeText(LoginActivity.this, ""+reponse_connection, Toast.LENGTH_SHORT).show();

            if (reponse_connection.equals("true"))
            {
                //new AsynLoginUser(context, phone_number).execute();
                new AsynLoginUserStation(context, phone_number).execute();

                Toast.makeText(LoginActivity.this, ""+phone_number, Toast.LENGTH_SHORT).show();
            }
            if (reponse_connection.equals("false"))
            {
                Toast.makeText(LoginActivity.this, "echec de connection au serveur, numero ou mot de passe incorrect ou n'existe pas sur le systeme ", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {

            JSONArray jsonArray;
            reponse_connection = dataFromAPI.IsUserExist(phone_number, password);
            try
            {
                jsonArray = new JSONArray(reponse_connection);


            } catch (JSONException e)
            {

            }

            return null;
        }
    }


//    Cette place concerne la connexion avec sqlite

    private class  connexionClass extends AsyncTask<String, Void,String>{
        String username;
        String password;
        String codeDepot;

        public connexionClass(String username, String password) {
            this.username = username;
            this.password = password;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            alertDialog = new ProgressDialog(LoginActivity.this);

            alertDialog.setCancelable(false);
            alertDialog.setMessage("Connection en cours...");
            alertDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            alertDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            alertDialog.dismiss();
            if(s.contains("false")){
                Toast.makeText(LoginActivity.this, "Nom d'utilisateur ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
            }else{

                //recuperation des information de l'utilisateur
                //connexion reussie avec succes
                try {
                    //si l'insertion a réussie on update la collonne etat upate dans lse serveur
//                    JSONObject jsonObjectj = new JSONObject(reponse);
//                    JSONArray jsonArray = jsonObjectj.getJSONArray("data");
                    JSONArray jsonArray = new JSONArray(s);
                    //JSONObject jsonArray = new JSONObject(s);

                    //Log.e("s",s);

                    SQLiteDatabase db = DatabaseHandler.getInstance(LoginActivity.this).getWritableDatabase();
                    db.beginTransaction();

                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        Gson gson = new Gson(); // Or use new GsonBuilder().create();
                        tUtilisateur myObject = gson.fromJson(jsonObject1.toString(), tUtilisateur.class);
                        //tUtilisateur myObject = gson.fromJson(jsonArray.toString(), tUtilisateur.class);
                        //verification de l'etat du phone

                        //Log.e("User object",""+dataFromAPI.GetComptStockAffecte(myObject.getDepotAffecter()));

                        //  Toast.makeText(context, ""+myObject.getNomUtilisateur(), Toast.LENGTH_SHORT).show();

                        currentUsers.setCurrentUsers(LoginActivity.this,myObject);
                        currentUsers.setConnexionTrue(LoginActivity.this);

                        codeDepot = myObject.getDepotAffecter();

                        editor.putString("pref_nom_user",myObject.getNomUtilisateur());
                        editor.putString("pref_foncion_user",myObject.getFonctionUt());
                        editor.putString("pref_service_user",myObject.getServiceAffe());
                        editor.putString("pref_compte_user",Integer.toString(myObject.getCompte()));
                        editor.putString("pref_depot_user",myObject.getDepotAffecter());
                        //editor.putString("pref_compte_stock_user", compte_stock_affecte);
                        editor.putString("pref_compte_depense_user", Integer.toString(myObject.getCaisseDepense()));

                        editor.commit();
                        editor.apply();

                        CompteDepot(myObject.getDepotAffecter());


                        //Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(LoginActivity.this, ""+myObject.getDepotAffecter(), Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(LoginActivity.this, "Connexion réussie avec succès", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,ContentMenuActivity.class));
                    finish();
                    db.setTransactionSuccessful();
                    db.endTransaction();
                    db.close();

                } catch (JSONException e) {
                    e.printStackTrace();
                    s = e.toString();
                }

                //recuperation des information de l'utilsateur
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            //verfication des l'existance d'un utilisateur
            String responseReturn = "";
            String reponse = ConnexionAPI.getDataFromServer(new me_URL(LoginActivity.this).IsUserExist(username,password));
            //String reponse = ConnexionAPI.getDataFromServer("https://www.google.com/");
            //String reponse = new DataFromAPI(LoginActivity.this).IsUserExist(username,password);
            if(reponse.contains("true")){
                //recuperation des information de l'uitlisateur
                String reponseUserData = ConnexionAPI.getDataFromServer(new me_URL(LoginActivity.this).GetLogin(username));
                //String reponseUserData = new DataFromAPI(LoginActivity.this).GetLogin(username);
                compte_stock_affecte = ConnexionAPI.getDataFromServer(new me_URL(LoginActivity.this).GetUserCompteStock(codeDepot));
                responseReturn =  reponseUserData;
            }else{
                responseReturn = reponse;
            }

            return responseReturn;
        }
    }

    public void CompteDepot(String codeDepot)
    {
        Call<String> call_compte_depot = depotRepository.depotConnexion().getCompteDepot(codeDepot);
        //load.setVisibility(View.VISIBLE);

        call_compte_depot.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful())
                {
                    editor.putString("pref_compte_stock_user", response.body());

                    editor.commit();
                    editor.apply();

                    Log.e("Compte retrofit",""+response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // load.setVisibility(View.GONE);
                Log.e("Echec","");
            }
        });
    }
}