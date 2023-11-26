//package com.scakstoreman.Article.data;
//
//import static android.content.Context.MODE_PRIVATE;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.AsyncTask;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.recyclerview.widget.RecyclerView;
//
//
//import com.soft.afri_wifi.Article.data.ArticleResponse;
//import com.soft.afri_wifi.DataBase.DataFromAPI;
//import com.soft.afri_wifi.Depot.data.DepotRepository;
//import com.soft.afri_wifi.R;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleListAdapter>{
//
//    Context context;
//    private ArrayList<ArticleResponse> list;
//    SharedPreferences preferences;
//    public static SharedPreferences.Editor editor;
//    String pref_code_depot, pref_compte_user, pref_compte_stock_user,nom_user,
//            todayDate, prefix_operation;
//    DepotRepository depotRepository;
//    Calendar calendar;
//    String isArticleInPanier;
//    DataFromAPI dadataFromAPI;
//
//
//
//    public ArticleAdapter(Context context) {
//        this.list = new ArrayList<>();
//        this.context = context;
//
//        preferences = context.getSharedPreferences("maPreference", MODE_PRIVATE);
//        editor = preferences.edit();
//
//        calendar = Calendar.getInstance();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        todayDate = format.format(calendar.getTime());
//
//        depotRepository = DepotRepository.getInstance();
//        dadataFromAPI = new DataFromAPI(context);
//
//        pref_code_depot = preferences.getString("pref_depot_user","");
//        pref_compte_user = preferences.getString("pref_compte_user","");
//        nom_user = preferences.getString("pref_nom_user","");
//        pref_compte_stock_user = preferences.getString("pref_compte_stock_user","");
//
//        //prefix_operation = nom_user.substring(0,2).toUpperCase()+pref_code_depot;
//
//
//    }
//
//    @NonNull
//    @Override
//    public ArticleListAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.model_articles, parent, false);
//        return new ArticleListAdapter(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ArticleListAdapter holder, int position) {
//        ArticleResponse articleResponse = list.get(position);
//        holder.textView_designation_article.setText("" + articleResponse.getDesignationArticle());
//        holder.textView_prix_unitaire.setText("" + articleResponse.getPrixAchat());
//
//
//        holder.linearLayoutArticle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                View myView = LayoutInflater.from(context).inflate(R.layout.dialog_add_quantite_panier,null);
//                builder.setView(myView);
//
//                EditText editTextNomProduit = myView.findViewById(R.id.dialog_add_quanite_nom_produit);
//                EditText editTextPrixDepot = myView.findViewById(R.id.dialog_add_quanite_prix_depot);
//                EditText editTextQuantite = myView.findViewById(R.id.dialog_add_quanite_quanite);
//                ProgressBar loadDialog = myView.findViewById(R.id.progressAddQteDialog);
//                Button annuler  = myView.findViewById(R.id.dialog_add_quantite_annuler_btn);
//                Button confirmer = myView.findViewById(R.id.dialog_add_quanite_confirmer_btn);
//
//                editTextNomProduit.setEnabled(false);
//                editTextNomProduit.setText(articleResponse.getDesignationArticle());
//
//
//
//                LoadPrixDepot(Integer.valueOf(pref_compte_stock_user), articleResponse.getCodeArticle(),
//                        loadDialog, editTextPrixDepot);
//
//
//                final AlertDialog dialog = builder.create();
//                dialog.show();
//
//                annuler.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//
//                confirmer.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        if (TextUtils.isEmpty(editTextQuantite.getText().toString().trim())
//                            || Double.parseDouble(editTextPrixDepot.getText().toString().trim()) == 0)
//                        {
//                            editTextQuantite.setError("Echec! verifiez vos champs de saisi!");
//                            editTextQuantite.requestFocus();
//                            return;
//                        }else
//                        {
//                            double quantite = Double.parseDouble(editTextQuantite.getText().toString());
//                            double prix_depot = Double.parseDouble(editTextPrixDepot.getText().toString());
//                            double total_entree = prix_depot * quantite;
//
//                            String codeArticle  = articleResponse.getCodeArticle();
//                            String libelle  = "Achat en cash de"+" "+quantite+"KG"+" "+"de"+" "+
//                                    articleResponse.getDesignationArticle();
//
//                            new AsyncCreateOperation(libelle, loadDialog, codeArticle, total_entree,
//                                    prix_depot, quantite, dialog, myView).execute();
//                        }
//
//
//                    }
//                });
//
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    class ArticleListAdapter extends RecyclerView.ViewHolder
//    {
//        TextView textView_designation_article;
//        TextView textView_prix_unitaire;
//        TextView textView_designation_code;
//        TextView textView_total;
//        LinearLayout linearLayoutArticle;
//
//        public ArticleListAdapter(@NonNull View itemView) {
//            super(itemView);
//
////            textView_designation_code = itemView.findViewById(R.id.modelArticleCode);
//            textView_designation_article = itemView.findViewById(R.id.modelArticleDesignation);
//            textView_prix_unitaire = itemView.findViewById(R.id.modelArticlePrix);
//            linearLayoutArticle = itemView.findViewById(R.id.model_article_linear_selectionner);
//
//        }
//    }
//
//    public void LoadPrixDepot(int numCompte, String codeArticle, ProgressBar progressBarLoadPrix, EditText displayPrix)
//    {
//        Call<Double> call_prix_depot = depotRepository.depotConnexion().getPrixDepot(numCompte, codeArticle);
//        progressBarLoadPrix.setVisibility(View.VISIBLE);
//        call_prix_depot.enqueue(new Callback<Double>() {
//            @Override
//            public void onResponse(Call<Double> call, Response<Double> response) {
//                if (response.isSuccessful())
//                {
//                    displayPrix.setEnabled(false);
//                    displayPrix.setText(response.body().toString());
//                    progressBarLoadPrix.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Double> call, Throwable t) {
//                progressBarLoadPrix.setVisibility(View.GONE);
//            }
//        });
//    }
//
//    private void SaveNewOperationAttente(String codeArticle, String libelle, double total,
//                                         double prixRevien, double quanite, AlertDialog dialog,
//                                         View view)
//    {
//
//        OperationRepository operationRepository = OperationRepository.getInstance();
//        OperationResponse operationResponse = new OperationResponse();
//        //operationAttenteResponse.setCodeClient(codeClient);
//        operationResponse.setNumOperation(prefix_operation);
//        operationResponse.setCodeClient("");
//        operationResponse.setDateSysteme(todayDate);
//        operationResponse.setLibelle(libelle);
//        operationResponse.setNomUtilisateur(nom_user);
//        operationResponse.setDateOperation(todayDate);
//        operationResponse.setCodeEtatdeBesoin("0");
//        operationResponse.setDateSysteme(todayDate);
//        operationResponse.setValider(0);
//        //operationAttenteResponse.setValiderPar("none");
//        operationRepository.operationConnexion().insertOperationAttenteStation(operationResponse).enqueue(new Callback<Integer>()
//        {
//            @Override
//            public void onResponse(Call<Integer> call, Response<Integer> response) {
//                if (response.isSuccessful())
//                {
//                    //Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
//                    Log.e("Ope",""+response);
//
//                    new AsyncGetLatestOp(codeArticle, libelle, total, prixRevien, quanite, dialog, view).execute();
//                }
//                else
//                {
//                    switch (response.code())
//                    {
//                        case 404:
//                            Toast.makeText(context, "Serveur introuvable", Toast.LENGTH_LONG).show();
//                            break;
//                        case 500:
//                            Toast.makeText(context, "Serveur en pane",Toast.LENGTH_LONG).show();
//                            break;
//                        default:
//                            Toast.makeText(context, "Erreur inconnu", Toast.LENGTH_LONG).show();
//                            break;
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Integer> call, Throwable t) {
//                Toast.makeText(context, "Probleme de connection", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    // Async create operation
//
//    public class AsyncCreateOperation extends AsyncTask <Void, Void, Void>
//    {
//        String libelle, codeArticle;
//        ProgressBar progressBarSaveoperation;
//        AlertDialog dialog;
//        View view;
//        double totalMontant, prixRevien, quantite;
//
//        public AsyncCreateOperation(String libelle, ProgressBar progressBarSaveoperation,
//                                    String codeArticle, double totalMontant, double prixRevien,
//                                    double quantite, AlertDialog dialog, View view) {
//            this.libelle = libelle;
//            this.progressBarSaveoperation = progressBarSaveoperation;
//            this.codeArticle = codeArticle;
//            this.totalMontant = totalMontant;
//            this.prixRevien = prixRevien;
//            this.quantite = quantite;
//            this.dialog = dialog;
//            this.view = view;
//
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressBarSaveoperation.setVisibility(View.VISIBLE);
//
//        }
//
//        @Override
//        protected void onPostExecute(Void unused) {
//            super.onPostExecute(unused);
//
//            progressBarSaveoperation.setVisibility(View.GONE);
//
//            // get last operation
//
////            String last_num_operation = dadataFromAPI.GetLatestOperation();
////
////            // save les mouvements
////
////            new AsyncSaveInPanierAttente(codeArticle, last_num_operation, prixRevien,
////                                          totalMontant,  quantite, libelle, dialog, view).execute();
//
//
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            //SaveNewOperation(codeClient, nomUtilisateur, clientSelection);
//            SaveNewOperationAttente(codeArticle, libelle, totalMontant, prixRevien, quantite, dialog, view);
//
//            return null;
//        }
//    }
//
//    public class AsyncGetLatestOp extends AsyncTask<Void, Void, Void>
//    {
//       String num_operation, codeArticle, libelle;
//       double totalMontant, prixRevien, quantite;
//        AlertDialog dialog;
//        View view;
//
//        public AsyncGetLatestOp(String codeArticle, String libelle,
//                                double totalMontant, double prixRevien,
//                                double quantite, AlertDialog dialog, View view) {
//            this.codeArticle = codeArticle;
//            this.libelle = libelle;
//            this.totalMontant = totalMontant;
//            this.prixRevien = prixRevien;
//            this.quantite = quantite;
//            this.dialog = dialog;
//            this.view = view;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(Void unused) {
//            super.onPostExecute(unused);
//
//            view.getContext().startActivity(new Intent(context, NouveauAchatActivity.class)
//                    .putExtra("num_operation", num_operation)
//                    .putExtra("libelle", libelle));
//
////            context.startActivity(new Intent(context, NouveauAchatActivity.class)
////                    .putExtra("num_operation", num_op)
////                    .putExtra("libelle", libelle));
//
//            dialog.dismiss();
//            ((Activity)view.getContext()).finish();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            num_operation = dadataFromAPI.GetLatestOperation();
//
//
//            // Verifier si l article et l operation de trouve dans le panier, a
//
//            isArticleInPanier = dadataFromAPI.IsArticleInPanier(num_operation, codeArticle);
//
//            if (isArticleInPanier.equals("true"))
//            {
//                Log.e("Panier", "Ce produit est dja au panier");
//            }
//            if (isArticleInPanier.equals("false"))
//            {
//
//                SaveInPanierAttente(codeArticle,num_operation, totalMontant, prixRevien, quantite);
//
//            }
//
//            return null;
//        }
//
//    }
//
//    public class AsyncSaveInPanierAttente extends AsyncTask<Void, Void, Void>
//    {
//        String codeArticle,num_op, dateOperation, codePompe, libelle;
//        double prixVenteUnitaire, totalMontant, prixRevien,
//                montantSortie, quantite;
//        ProgressBar progressBarLoadPanier;
//        ListView listViewPanier;
//        String code_client_;
//        TextView displayTotalGenPanier, displayTotalSortiePanier;
//        String numero_bon, code_chambre;
//
//        AlertDialog dialog ;
//        View view;
//
//        public AsyncSaveInPanierAttente(String codeArticle, String num_op,
//                                        double totalMontant,double prixRevien,
//                                        double quantite, String libelle, AlertDialog dialog,
//                                        View view) {
//            this.codeArticle = codeArticle;
//            this.num_op = num_op;
//            this.totalMontant = totalMontant;
//            this.prixRevien = prixRevien;
//            this.quantite = quantite;
//            this.libelle = libelle;
//            this.dialog = dialog;
//            this.view = view;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
////            alertDialog = new ProgressDialog(VenteActivityNew.this);
////
////            alertDialog.setCancelable(false);
////            alertDialog.setMessage("En cours...");
////            alertDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
////            alertDialog.show();
//        }
//
//        @Override
//        protected void onPostExecute(Void unused) {
//            super.onPostExecute(unused);
//
//            view.getContext().startActivity(new Intent(context, NouveauAchatActivity.class)
//                    .putExtra("num_operation", num_op)
//                    .putExtra("libelle", libelle));
//
////            context.startActivity(new Intent(context, NouveauAchatActivity.class)
////                    .putExtra("num_operation", num_op)
////                    .putExtra("libelle", libelle));
//
//            dialog.dismiss();
//            new ListeArticleActivity().finish();
//
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            // Verifier si l article et l operation de trouve dans le panier, a
//
//            isArticleInPanier = dadataFromAPI.IsArticleInPanier(num_op, codeArticle);
//
//            if (isArticleInPanier.equals("true"))
//            {
//                Log.e("Panier", "Ce produit est dja au panier");
//            }
//            if (isArticleInPanier.equals("false"))
//            {
//
//                SaveInPanierAttente(codeArticle,num_op, totalMontant, prixRevien, quantite);
//
//            }
//
//            return null;
//        }
//    }
//    // Save in Panier attente
//
//    public void SaveInPanierAttente(String codeArticle,String numOperation,
//                                    double totalMontant, double prixRevien, double quantite)
//    {
//        PanierAttenteRepository panierAttenteRepository = PanierAttenteRepository.getInstance();
//        PanierAttenteResponse panierAttenteResponse = new PanierAttenteResponse();
//
//        panierAttenteResponse.setCodeDepot(pref_code_depot);
//        panierAttenteResponse.setCodePompe("");
//        panierAttenteResponse.setCodeArticle(codeArticle);
//        panierAttenteResponse.setPrixRevient(prixRevien);
//        panierAttenteResponse.setPrixVenteUnitaire(prixRevien);
//        panierAttenteResponse.setQuantiteSortie(0);
//        panierAttenteResponse.setQuantiteEntree(quantite);
//        panierAttenteResponse.setQuantiteEntreeAchat(quantite);
//        panierAttenteResponse.setNumOperation(numOperation);
//        panierAttenteResponse.setRefComptabilite("");
//        panierAttenteResponse.setSortie(0);
//        panierAttenteResponse.setQteSortieVente(0);
//        panierAttenteResponse.setSommeVente(0);
//        panierAttenteResponse.setVente(0);
//        panierAttenteResponse.setEntree(totalMontant);
//        panierAttenteResponse.setSommeAchat(totalMontant);
//        panierAttenteResponse.setPrixVente(prixRevien);
//        panierAttenteResponse.setPrixAchat(prixRevien);
//        panierAttenteResponse.setIndexDemarrer(0);
//        panierAttenteResponse.setNumeroBon("");
//        panierAttenteResponse.setCodeChambre("");
//
//
//        panierAttenteRepository.panierAttenteConnexion().insertPanierAttenteStation(panierAttenteResponse).enqueue(new Callback<Integer>()
//        {
//            @Override
//            public void onResponse(Call<Integer> call, Response<Integer> response) {
//                if (response.isSuccessful())
//                {
//                    Log.e("MvtStock",""+response);
//                    Toast.makeText(context, "Bien ajouté au panier", Toast.LENGTH_SHORT).show();
//
//
//
//                }
//                else
//                {
//                    switch (response.code())
//                    {
//                        case 404:
//                            Toast.makeText(context, "Serveur introuvable", Toast.LENGTH_LONG).show();
//                            Log.e("OpeAttente",""+response);
//                            break;
//                        case 500:
//                            Toast.makeText(context, "Serveur en pane"+pref_code_depot,Toast.LENGTH_LONG).show();
//                            Log.e("OpeAttente",""+response);
//                            break;
//                        default:
//                            Toast.makeText(context, "Erreur inconnu", Toast.LENGTH_LONG).show();
//                            Log.e("OpeAttente",""+response);
//                            break;
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Integer> call, Throwable t) {
//                Toast.makeText(context, "Probleme de connection", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    public void setList(List<ArticleResponse> list)
//    {
//        this.list.clear();
//        this.list.addAll(list);
//    }
//}
