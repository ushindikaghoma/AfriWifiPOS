package com.soft.afri_wifi.Ticket.data;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.soft.afri_wifi.Article.data.ArticleResponse;
import com.soft.afri_wifi.Comptabilite.data.ComptabiliteRepository;
import com.soft.afri_wifi.Comptabilite.data.ComptabiliteResponse;
import com.soft.afri_wifi.DataBase.DataFromAPI;
import com.soft.afri_wifi.Operation.OperationRepository;
import com.soft.afri_wifi.Operation.OperationResponse;
import com.soft.afri_wifi.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketAdapter  extends RecyclerView.Adapter<TicketAdapter.TicketListAdapter>{


    Context context;
    private ArrayList<TicketResponse> list;
    TicketRepository ticketRepository;

    SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    String pref_code_depot, pref_compte_user, pref_compte_stock_user,nom_user, pref_mode_type,
            todayDate, prefix_operation;

    DataFromAPI dadataFromAPI;
    Calendar calendar;
    public TicketAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;

        ticketRepository = TicketRepository.getInstance();

        preferences = context.getSharedPreferences("maPreference", MODE_PRIVATE);
        editor = preferences.edit();

        dadataFromAPI = new DataFromAPI(context);

        calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        todayDate = format.format(calendar.getTime());

        pref_code_depot = preferences.getString("pref_depot_user","");
        pref_compte_user = preferences.getString("pref_compte_user","");
        nom_user = preferences.getString("pref_nom_user","");
        pref_compte_stock_user = preferences.getString("pref_compte_stock_user","");
        pref_mode_type = preferences.getString("pref_mode_type","");

        prefix_operation = nom_user.substring(0,2).toUpperCase()+pref_code_depot;
    }

    @NonNull
    @Override
    public TicketListAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_ticket, parent, false);
        return new TicketListAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketListAdapter holder, int position) {
        TicketResponse ticketResponse = list.get(position);
        //holder.cardViewEmpty.setVisibility(View.GONE);
        holder.textView_designation_tickect.setText("" + ticketResponse.getDesignationTicket());
        holder.textView_prix_unitaire_ticket.setText("" + ticketResponse.getPrixTicket()+"$");
        holder.textView_validite_ticket.setText("" + ticketResponse.getValiditeTicket());

        holder.linearLayoutTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(context, ""+pref_code_depot+" "+list.get(position).getCatArticle(),Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                View myView = LayoutInflater.from(context).inflate(R.layout.dialog_select_forfait,null);
                builder.setView(myView);

                TextView nom_forfait = myView.findViewById(R.id.dialog_forfait_designation_produit);
                TextView prix_forfait = myView.findViewById(R.id.dialog_forfait_prix);
                TextView validite_forfait = myView.findViewById(R.id.dialog_forfait_validite);
                TextView username = myView.findViewById(R.id.dialog_forfait_username);
                TextView password = myView.findViewById(R.id.dialog_forfait_password);
                TextView annuler = myView.findViewById(R.id.dialog_forfait_annuler_btn);
                Button confirmer_vente = myView.findViewById(R.id.dialog_forfait_confirmer_btn);
                ProgressBar progressBar = myView.findViewById(R.id.progress_dailog_forfait);
                EditText editTextLibele = myView.findViewById(R.id.editTextLibele);

                progressBar.setVisibility(View.GONE);

                nom_forfait.setText("FORFAIT: "+list.get(position).getDesignationTicket());
                prix_forfait.setText(""+list.get(position).getPrixTicket()+"$");
                validite_forfait.setText(list.get(position).getValiditeTicket());


                LoadInfosTicket(pref_code_depot,
                        Integer.valueOf(list.get(position).getCatArticle()), username, password);

                String libelle = "Vente en cash du" + " " + nom_forfait.getText().toString()
                        + " de" + " " +validite_forfait.getText().toString()+ " à" + " ";
                editTextLibele.setText(libelle);



                final AlertDialog dialog = builder.create();
                dialog.show();

                annuler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                confirmer_vente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (username.getText().toString().trim().equals("Null")
                                || password.getText().toString().trim().equals("Null") )
                        {
                            Toast.makeText(context, "Ticket invalide", Toast.LENGTH_SHORT).show();
                        }else {

                            new AsyncCreateOperation(libelle, progressBar, list.get(position).getPrixTicket(),
                                    list.get(position).getPrixTicket(), dialog, myView,
                                    list.get(position).getCatArticle(),list.get(position).getIdTicket(),
                                    username.getText().toString(), password.getText().toString()).execute();
                        }



                    }
                });
            }
        });

    }

    public void LoadInfosTicket(String codeDepot, int catArticle,
                                TextView username, TextView password)
    {
        Call<List<ArticleResponse>> call_liste_fournisseur = ticketRepository.ticketConnexion().getRadomTicket(codeDepot, catArticle);
        //loadTicket.setVisibility(View.VISIBLE);
        call_liste_fournisseur.enqueue(new Callback<List<ArticleResponse>>() {
            @Override
            public void onResponse(Call<List<ArticleResponse>> call, Response<List<ArticleResponse>> response) {
                if (response.isSuccessful())
                {
                    //loadTicket.setVisibility(View.GONE);
                    double _sortie_totale = 0;
                    double _vente_totale = 0;
                    ArrayList<ArticleResponse>list_local_ticket = new ArrayList<>();
                    for (int a = 0; a < response.body().size(); a++)
                    {
                        username.setText(response.body().get(a).getUserName());
                        password.setText(response.body().get(a).getPassword());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<ArticleResponse>> call, Throwable t) {
                //loadTicket.setVisibility(View.GONE);
            }
        });
    }

    public void NouveauMvtCompte(String numOperation, String libele,
                                 int numCompteDebitEntree,
                                 int numCompteCreditSortie, double montant,
                                 String username, String password   )
    {
        ComptabiliteRepository comptabiliteRepository = ComptabiliteRepository.getInstance();
        ComptabiliteResponse comptabiliteResponse = new ComptabiliteResponse();

        comptabiliteResponse.setNumOperation(numOperation);
        comptabiliteResponse.setLibelle(libele);
        comptabiliteResponse.setNumCompteDebitEntree(numCompteDebitEntree);
        comptabiliteResponse.setNumCompteCreditSortie(numCompteCreditSortie);
        comptabiliteResponse.setMontant(montant);
        comptabiliteResponse.setQte(1);
        comptabiliteResponse.setDesignationCompteDebit("");
        comptabiliteResponse.setDesignationCreditSortie("");
        comptabiliteRepository.comptabiliteConnexion().insertMvtCompte(comptabiliteResponse).enqueue(new Callback<Integer>()
        {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful())
                {
                    Log.e("Achat", ""+response.body());

//                    String update_operation = dataFromAPI.UpdatEtatOperation(1, numOperation);
//
//                    if (update_operation.equals("1"))
//                    {
//                        Toast.makeText(NouveauAchatActivity.this, "Operation validée", Toast.LENGTH_LONG).show();
//                        finish();
//                    }else
//                    {
//                        Toast.makeText(NouveauAchatActivity.this, "Echec"+numOperation +" "+update_operation, Toast.LENGTH_LONG).show();
//                    }

                    //updateOperation(1, numOperation);

                    updateEtatTicket(username, password);

                }
                else
                {
                    switch (response.code())
                    {
                        case 404:
                            Toast.makeText(context, "Serveur introuvable", Toast.LENGTH_LONG).show();
                            break;
                        case 500:
                            Toast.makeText(context, "Serveur en pane",Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(context, "Erreur inconnu", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(context, "Probleme de connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    // Async create operation

    public class AsyncCreateOperation extends AsyncTask<Void, Void, Void>
    {
        String libelle, codeArticle,catArticle,username,password;
        ProgressBar progressBarSaveoperation;
        AlertDialog dialog;
        View view;
        double totalMontant, prixRevien, quantite;
        int  idTicket;

        public AsyncCreateOperation(String libelle, ProgressBar progressBarSaveoperation,
                                    double totalMontant, double prixRevien,
                                    AlertDialog dialog, View view,
                                    String catArticle, int idTicket, String username, String password) {
            this.libelle = libelle;
            this.progressBarSaveoperation = progressBarSaveoperation;
            this.totalMontant = totalMontant;
            this.prixRevien = prixRevien;
            this.dialog = dialog;
            this.view = view;
            this.catArticle = catArticle;
            this.idTicket = idTicket;
            this.username = username;
            this.password = password;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarSaveoperation.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            progressBarSaveoperation.setVisibility(View.GONE);

            // get last operation

//            String last_num_operation = dadataFromAPI.GetLatestOperation();
//
//            // save les mouvements
//
//            new AsyncSaveInPanierAttente(codeArticle, last_num_operation, prixRevien,
//                                          totalMontant,  quantite, libelle, dialog, view).execute();


        }

        @Override
        protected Void doInBackground(Void... voids) {

            //SaveNewOperation(codeClient, nomUtilisateur, clientSelection);
            SaveNewOperationAttente(libelle, totalMontant, prixRevien,
                                        dialog, view,
                                        catArticle, idTicket,
                                        username, password);

            return null;
        }
    }

    public class AsyncGetLatestOp extends AsyncTask<Void, Void, Void>
    {
        String num_operation, codeArticle, libelle, catArticle, userName, password;
        double totalMontant, prixRevien, quantite;
        AlertDialog dialog;
        View view;
        int idTicket;

        public AsyncGetLatestOp(String libelle,double totalMontant,
                                double prixRevien,AlertDialog dialog,
                                View view, String catArticle,
                                String userName, String password, int idTicket) {

            this.libelle = libelle;
            this.totalMontant = totalMontant;
            this.prixRevien = prixRevien;
            this.dialog = dialog;
            this.view = view;
            this.catArticle = catArticle;
            this.userName = userName;
            this.password = password;
            this.idTicket = idTicket;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

//            view.getContext().startActivity(new Intent(context, NouveauAchatActivity.class)
//                    .putExtra("num_operation", num_operation)
//                    .putExtra("libelle", libelle));

//            context.startActivity(new Intent(context, NouveauAchatActivity.class)
//                    .putExtra("num_operation", num_op)
//                    .putExtra("libelle", libelle));

            dialog.dismiss();
            //((Activity)view.getContext()).finish();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            num_operation = dadataFromAPI.GetLatestOperation();


            // Verifier si l article et l operation de trouve dans le panier, a



                SaveMvtTicket(num_operation, totalMontant, prixRevien, catArticle,
                        idTicket, userName, password, libelle);


            return null;
        }

    }

    public void SaveMvtTicket(String numOperation, double montant, double prix,
                              String catArticle, int idTicket, String userName,
                              String password, String libelle)
    {
        TicketRepository ticketRepository = TicketRepository.getInstance();
        MvtTicketReponse mvtTicketReponse = new MvtTicketReponse();

        mvtTicketReponse.setNumOperation(numOperation);
        mvtTicketReponse.setCatArticle(catArticle);
        mvtTicketReponse.setUserName(userName);
        mvtTicketReponse.setPassword(password);
        mvtTicketReponse.setEtat(0);
        mvtTicketReponse.setPrix(prix);
        mvtTicketReponse.setEntree(0);
        mvtTicketReponse.setSortie(montant);
        mvtTicketReponse.setIdTicket(idTicket);
        ticketRepository.ticketConnexion().insertMvtTicket(mvtTicketReponse).enqueue(new Callback<Integer>()
        {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful())
                {
                    Log.e("Ticket", ""+response.body());

//                    String update_operation = dataFromAPI.UpdatEtatOperation(1, numOperation);
//
//                    if (update_operation.equals("1"))
//                    {
//                        Toast.makeText(NouveauAchatActivity.this, "Operation validée", Toast.LENGTH_LONG).show();
//                        finish();
//                    }else
//                    {
//                        Toast.makeText(NouveauAchatActivity.this, "Echec"+numOperation +" "+update_operation, Toast.LENGTH_LONG).show();
//                    }

                    //updateOperation(1, numOperation);
                    NouveauMvtCompte(numOperation, libelle,701001,
                            572038,
                            montant, userName, password);

                }
                else
                {
                    switch (response.code())
                    {
                        case 404:
                            Toast.makeText(context, "Serveur introuvable", Toast.LENGTH_LONG).show();
                            break;
                        case 500:
                            Toast.makeText(context, "Serveur en pane",Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(context, "Erreur inconnu", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(context, "Probleme de connection", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void updateEtatTicket(String username, String password)
    {
        Call<String> call_update = ticketRepository.ticketConnexion().updateEtatTicket(username, password);
//        progressBarLoadPrix.setVisibility(View.VISIBLE);
        call_update.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(context, "Vente effectuée avec succès",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                progressBarLoadPrix.setVisibility(View.GONE);
                Toast.makeText(context, "Echec! Veuillez ressayer!",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void SaveNewOperationAttente(String libelle, double total,
                                         double prixRevien, AlertDialog dialog,
                                         View view, String catArticle, int idTicket,
                                         String username, String password)
    {

        OperationRepository operationRepository = OperationRepository.getInstance();
        OperationResponse operationResponse = new OperationResponse();
        //operationAttenteResponse.setCodeClient(codeClient);
        operationResponse.setNumOperation(prefix_operation);
        operationResponse.setCodeClient("");
        operationResponse.setDateSysteme(todayDate);
        operationResponse.setLibelle(libelle);
        operationResponse.setNomUtilisateur(nom_user);
        operationResponse.setDateOperation(todayDate);
        operationResponse.setCodeEtatdeBesoin("0");
        operationResponse.setDateSysteme(todayDate);
        operationResponse.setValider(0);
        //operationAttenteResponse.setValiderPar("none");
        operationRepository.operationConnexion().insertOperationAttenteStation(operationResponse).enqueue(new Callback<Integer>()
        {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful())
                {
                    //Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
                    Log.e("Ope",""+response);

                    new AsyncGetLatestOp(libelle, total, prixRevien, dialog, view,
                            catArticle, username, password, idTicket).execute();
                }
                else
                {
                    switch (response.code())
                    {
                        case 404:
                            Toast.makeText(context, "Serveur introuvable", Toast.LENGTH_LONG).show();
                            break;
                        case 500:
                            Toast.makeText(context, "Serveur en pane",Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(context, "Erreur inconnu", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(context, "Probleme de connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TicketListAdapter extends RecyclerView.ViewHolder
    {
        TextView textView_designation_tickect;
        TextView textView_prix_unitaire_ticket;
        TextView textView_validite_ticket;
        LinearLayout linearLayoutTicket;
        CardView cardViewEmpty;

        public TicketListAdapter(@NonNull View itemView) {
            super(itemView);

            textView_designation_tickect = itemView.findViewById(R.id.viewTicketDesignation);
            textView_prix_unitaire_ticket = itemView.findViewById(R.id.viewTicketPrix);
            textView_validite_ticket = itemView.findViewById(R.id.viewTicketValidite);
            linearLayoutTicket = itemView.findViewById(R.id.ViewlinearLayoutTicketClick);
            //cardViewEmpty = itemView.findViewById(R.id.card_empty);

        }
    }

    public void setList(List<TicketResponse> list)
    {
        this.list.clear();
        this.list.addAll(list);
    }
}
