package com.soft.afri_wifi.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class DataFromAPI extends ConnexionAPI{
    String url = "";
    String response = ""; int delete_response;
    StringBuffer ipServeur;
    String server;
    Context context;
    Cursor serveur;
    public static  String URL = "";

    public String postClient = "";

    SqlDataHelper sqliteDataHelper;

    public DataFromAPI(Context context) {
        this.context = context;

        sqliteDataHelper = new SqlDataHelper(context);
        serveur = sqliteDataHelper.getCurrentAdress();

        StringBuffer buffer = new StringBuffer();


        while (serveur.moveToNext()) {
            ipServeur = buffer.append(serveur.getString(1));
            server = ipServeur.toString();
        }

       // server = "192.168.1.30/TouchBistroIshango"; local
        server = "afrisofttech-001-site52.btempurl.com"; // en ligne

        postClient = "http://"+server+"/api/Clients/Create";
        //URL = server;
    }

    //Get server for post

    public String GetUrlPost()
    {
        try
        {
            url ="http://"+server+"/";

        }catch (Exception e)
        {

        }

        return url;
    }

    // Login url

    public String GetLogin(String phone_number)
    {
        phone_number = phone_number.replace(" ", "%20");
        try {
            url = "http://"+server+"/api/Utilisateur/GetSeConnecter?phone_number="+phone_number;
            //url = "http://localhost/WebApisGestionHotel/api/Utilisateur/GetSeConnecter?phone_number=Yann";
//            url = "http://"+server+"/api/User/GetSeConnecter?phone_number="+phone_number;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/User/GetSeConnecter?phone_number="+phone_number;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Utilisateur/GetSeConnecter?phone_number="+phone_number;
            response = call(url);

            Log.e("Response Login", response);
        } catch (Exception e) {
            Log.e("Response", "" + e);
        }
        Log.e("Response jk", response);
        return response;
    }

    public String IsUserExist(String phone_number, String password)
    {
        phone_number = phone_number.replace(" ", "%20");
        password = password.replace(" ", "%20");
        try
        {
            url = "http://"+server+"/api/Utilisateur/IsPhoneExist?phonenumber="+phone_number+"&password="+password;
//            url = "http://"+server+"/api/User/IsPhoneExist?phonenumber="+phone_number+"&password="+password;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/User/IsPhoneExist?phonenumber="+phone_number+"&password="+password;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Utilisateur/IsPhoneExist?phonenumber="+phone_number+"&password="+password;
            response = call(url);

        } catch (Exception e) {
            Log.e("Response", "" + e);
        }
        Log.e("Response", response);
        return response;
    }

    // Get client affectés url

    public String GetClientAffectes()
    {
        try
        {
            url = "http://"+server+"/api/Affectation/GetClientAffectes";
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Affectation/GetClientAffectes";
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Affectation/GetClientAffectes";
            response = call(url);

            Log.e("Response", response);
        } catch (Exception e) {
            Log.e("Response", "" + e);
        }
        Log.e("Response", response);
        return response;
    }

    // Get liste de tous les clients

    public String GetListeClient()
    {
        try
        {
            url = "http://"+server+"/api/Clients/GetListeClient";
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Clients/GetListeClient";
            //url = "http://localhost/WebApisStation/api/Clients/GetListeClient";
            response = call(url);
            Log.e("Abonnes", response);
        }catch (Exception e)
        {

        }
        Log.e("Abonnes", response);
        return response;
    }

    //Get la liste des client abonnés

    public String GetListesClientAbonnes()
    {
        try
        {
            url = "http://"+server+"/api/Compte/GetListeDesAbonnes";
            //url = "http://afrisofttech-003-site31.btempurl.com/api/Compte/GetListeDesAbonnes";
            response = call(url);


        }catch (Exception e)
        {

        }
        return response;
    }

    //Get solde par client

    public String GetSoldeAbonne(String num_compte)
    {
        try
        {
            url ="http://"+server+"/api/Stock/GetSoldeAbonnes?num_compte="+num_compte;
//            url ="http://afrisofttech-003-site31.btempurl.com/api/Stock/GetSoldeAbonnes?num_compte="+num_compte;
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    // Get la liste des articles

    public String GetListeArticle()
    {
        try
        {
            url = "http://"+server+"/api/Stock/GetClientArticles";
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Stock/GetClientArticles";
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Stock/GetClientArticles";
            response = call(url);
            Log.e("Response stock", response);
        }catch (Exception e)
        {

        }
        Log.e("Response stock", response);
        return response;
    }

    //Get liste article par depot

    public String GetListeArtileParDepot(String code_depot)
    {
        try
        {
            url ="http://"+server+"/api/Stock/GetClientArticlesParDepot?code_depot="+code_depot;
//            url ="http://afrisofttech-003-site31.btempurl.com/api/Stock/GetClientArticlesParDepot?code_depot="+code_depot;
//            url ="http://afrisofttech-003-site13.btempurl.com/api/Stock/GetClientArticlesParDepot?code_depot="+code_depot;
            response = call(url);
        }catch (Exception e)
        {

        }
        return response;
    }

    // Get liste categorie article

    public String GetListeCategorieArticle()
    {
        try
        {
            url = "http://"+server+"/api/Stock/GetCategorieArticle";
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Stock/GetCategorieArticle";
            response = call(url);
            Log.e("Cat aarticle", url);

        }catch (Exception e)
        {

        }
        return response;
    }
    //Get article par categorie

    public String GetListeArticleParCategorie(int idCategorie)
    {
        try
        {
            url = "http://"+server+"/ApisGestionHotel/api/Stock/GetClientArticlesParCategorie?idCategorie="+idCategorie;
//            url = "http://afrisofttech-003-site31.btempurl.com/ApisGestionHotel/api/Stock/GetClientArticlesParCategorie?idCategorie="+idCategorie;
            response = call(url);

        }catch (Exception e)
        {

        }

        return response;
    }

    // Get the latest operation

    public  String GetLatestOperation()
    {
        try
        {
            url  = "http://"+server+"/api/Operation/GetLatestOp";
//            url  = "http://afrisofttech-003-site31.btempurl.com/api/Operation/GetLatestOp";
//            url  = "http://afrisofttech-003-site13.btempurl.com/api/Operation/GetLatestOp";
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    //Get last operation attente

    public  String GetLatestOperationAttente()
    {
        try
        {
            url  = "http://"+server+"/api/Operation/GetLatestOpAttente";
//            url  = "http://afrisofttech-003-site31.btempurl.com/api/Operation/GetLatestOp";
//            url  = "http://afrisofttech-003-site13.btempurl.com/api/Operation/GetLatestOp";
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    // Annuler operation

    public String DeleteOperation(String num_operation)
    {
        try
        {
            url = "http://"+server+"/api/Operation/DeleteOperation?num_operation="+num_operation;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Operation/DeleteOperation?num_operation="+num_operation;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Operation/DeleteOperation?num_operation="+num_operation;
            response = call(url);
            Log.e("Delete", response);
        }catch (Exception e)
        {

        }

        return response;
    }

    public String DeleteOperationAttente(String num_operation)
        {
            try
            {
                url = "http://"+server+"/api/Operation/DeleteOperation?num_operation="+num_operation;
    //            url = "http://afrisofttech-003-site31.btempurl.com/api/Operation/DeleteOperation?num_operation="+num_operation;
    //            url = "http://afrisofttech-003-site13.btempurl.com/api/Operation/DeleteOperation?num_operation="+num_operation;
                response = call(url);
                Log.e("Delete", response);
            }catch (Exception e)
            {

            }

            return response;
        }

    //Get liste mvt stock, le panier

    public String GetListeMvtPanier(String num_op)
    {
        try
        {
            url = "http://"+server+"/api/Stock/GetListeMvtStock?num_op="+num_op;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Stock/GetListeMvtStock?num_op="+num_op;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Stock/GetListeMvtStock?num_op="+num_op;
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    //Get liste mvt panier attente

    public String GetListeMvtPanierAttente(String num_op)
    {
        try
        {
            url = "http://"+server+"/api/Stock/GetListeMvtStockAttente?num_op="+num_op;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Stock/GetListeMvtStock?num_op="+num_op;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Stock/GetListeMvtStock?num_op="+num_op;
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    // Get montant total dans le panier

    public String GetTotalMontantInPanier(String num_op)
    {
        try
        {
            url = "http://"+server+"/api/Stock/GetTotalInPanier?num_op="+num_op;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Stock/GetTotalInPanier?num_op="+num_op;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Stock/GetTotalInPanier?num_op="+num_op;
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    // Get total montant attente

    public String GetTotalMontantInPanierAttente(String num_op)
    {
        try
        {
            url = "http://"+server+"/api/Stock/GetTotalInPanierAttente?num_op="+num_op;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Stock/GetTotalInPanier?num_op="+num_op;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Stock/GetTotalInPanier?num_op="+num_op;
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    public String GetTotalSortieInPanier(String num_op)
    {
        try
        {
            url = "http://"+server+"/api/Stock/GetTotalSortieInPanier?num_op="+num_op;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Stock/GetTotalSortieInPanier?num_op="+num_op;
//          url = "http://afrisofttech-003-site13.btempurl.com/api/Stock/GetTotalSortieInPanier?num_op;
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    //Get total montant sortie attente

    public String GetTotalSortieInPanierAttente(String num_op)
    {
        try
        {
            url = "http://"+server+"/api/Stock/GetTotalSortieInPanierAttente?num_op="+num_op;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Stock/GetTotalSortieInPanier?num_op="+num_op;
//          url = "http://afrisofttech-003-site13.btempurl.com/api/Stock/GetTotalSortieInPanier?num_op;
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    // Is article in panier?

    public String IsArticleInPanier(String numOperation, String codeArticle)
    {
        try
        {
            url = "http://"+server+"/api/Stock/IsArticleInPanier?numOperation="+numOperation+"&codeArticle="+codeArticle;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Stock/IsArticleInPanier?numOperation="+numOperation+"&codeArticle="+codeArticle;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Stock/IsArticleInPanier?numOperation="+numOperation+"&codeArticle="+codeArticle;
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    // Is article in panier attente

    public String IsArticleInPanierAttente(String numOperation, String codeArticle)
    {
        try
        {
            url = "http://"+server+"/api/Stock/IsArticleInPanierAttente?numOperation="+numOperation+"&codeArticle="+codeArticle;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Stock/IsArticleInPanier?numOperation="+numOperation+"&codeArticle="+codeArticle;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Stock/IsArticleInPanier?numOperation="+numOperation+"&codeArticle="+codeArticle;
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    // Update quantite in panier

    public String UpdateQuantiteInPanier(String num_op, String code_article, int quantite_sortie, int quantite_sortie_vente, double total_montant, double sortie, double somme_vente, double vente )
    {
        try
        {
            url = "http://"+server+"/api/Stock/UpdateQuantiteInPanier?num_operation="+num_op+"&code_article="+code_article+"&quantiteSortie="+quantite_sortie+"&quantiteSortieVente="+quantite_sortie_vente+"&totalmontant="+total_montant+"&sortie="+sortie+"&sommeVente="+somme_vente+"&vente="+vente+"";
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Stock/UpdateQuantiteInPanier?num_operation="+num_op+"&code_article="+code_article+"&quantiteSortie="+quantite_sortie+"&quantiteSortieVente="+quantite_sortie_vente+"&totalmontant="+total_montant+"&sortie="+sortie+"&sommeVente="+somme_vente+"&vente="+vente+"";
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Stock/UpdateQuantiteInPanier?num_operation="+num_op+"&code_article="+code_article+"&quantiteSortie="+quantite_sortie+"&quantiteSortieVente="+quantite_sortie_vente+"&totalmontant="+total_montant+"&sortie="+sortie+"&sommeVente="+somme_vente+"&vente="+vente+"";
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    // Delete article in panier

    public String DeleteArticleInPanier(String codeArticle, String numOperation)
    {
        try
        {
            url = "http://"+server+"/api/Stock/DeleteArticleInPanier?codeArticle="+codeArticle+"&numOperation="+numOperation+"";
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Stock/DeleteArticleInPanier?codeArticle="+codeArticle+"&numOperation="+numOperation+"";
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Stock/DeleteArticleInPanier?codeArticle="+codeArticle+"&numOperation="+numOperation+"";
            response = call(url);

        }catch (Exception e)
        {

        }

        return response;
    }

    //Get the latest entered codeclient

    public String GetLatestCodeClient()
    {
        try
        {
            url = "http://"+server+"/api/Clients/GetLatestCodeClient";
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Clients/GetLatestCodeClient";
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Clients/GetLatestCodeClient";
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    // Get commandes en cours ou validees

    public String GetCommandesEnCoursValidees(String nomUtilisateur, int valider)
    {
        try
        {
            url = "http://"+server+"/api/Operation/GetCommandeEnCoursEtValidees?nomUtilisateur="+nomUtilisateur+"&valider="+valider+"";
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Operation/GetCommandeEnCoursEtValidees?nomUtilisateur="+nomUtilisateur+"&valider="+valider+"";
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Operation/GetCommandeEnCoursEtValidees?nomUtilisateur="+nomUtilisateur+"&valider="+valider+"";
            response = call(url);
        }catch (Exception e)
        {

        }

        return  response;
    }

    // Get  commande en cours ou validees par date

    public String GetCommandesEnCoursValideesParDate(String nomUtilisateur, int valider, String date)
    {
        try
        {
            url = "http://"+server+"/ApisGestionHotel/api/Operation/GetCommandeEnCoursEtValideesParDate?nomUtilisateur="+nomUtilisateur+"&valider="+valider+"&date="+date;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Operation/GetCommandeEnCoursEtValideesParDate?nomUtilisateur="+nomUtilisateur+"&valider="+valider+"&date="+date;
            response = call(url);

        }catch (Exception e)
        {

        }

        return  response;
    }

    // Update etat operation

    public String UpdatEtatOperation(int valider, String numOperation)
    {
        try
        {
            url = "http://"+server+"/api/Operation/UpdateEtatOperation?valider="+valider+"&num_operation="+numOperation+"";
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Operation/UpdateEtatOperation?valider="+valider+"&validerPar="+validerPar+"&num_operation="+numOperation+"";
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Operation/UpdateEtatOperation?valider="+valider+"&validerPar="+validerPar+"&num_operation="+numOperation+"";
            response = call(url);
        }catch (Exception e)
        {

        }
        Log.e("Update", response);
        return response;
    }

    //Get liste client par type

    public String GetListClientParType(String typeClient)
    {
        try
        {
            url = "http://"+server+"/api/Clients/GetClientParType?typeClient="+typeClient;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Clients/GetClientParType?typeClient="+typeClient;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Clients/GetClientParType?typeClient="+typeClient;
            response = call(url);
        }catch (Exception e)
        {

        }
        return response;
    }

    public String GetListClientAmbulant(String typeClient)
    {
        try
        {
            url = "http://"+server+"/api/Client/GetClientAmbulant?typeClient="+typeClient;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Clients/GetClientAmbulant?typeClient="+typeClient;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Clients/GetClientParType?typeClient="+typeClient;
            response = call(url);
        }catch (Exception e)
        {

        }
        return response;
    }

    //Get code client where nom selectionne

    public String GetCodeClient(String nom_client)
    {
        try
        {
            url = "http://"+server+"/api/Client/GetCodeClient?nom_client="+nom_client;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Clients/GetCodeClient?nom_client="+nom_client;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Clients/GetCodeClient?nom_client="+nom_client;
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    //Get code client par numero de telephone

    public String GetCodeClientParPhone(String phone_number)
    {
        try
        {
            url = "http://"+server+"/api/Clients/GetCodeClientParPhone?phone_number="+phone_number;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Clients/GetCodeClientParPhone?phone_number="+phone_number;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Clients/GetCodeClientParPhone?phone_number="+phone_number;
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }


    //Verifier si le client existe par numero de telephone

    public String IsClientExist(String phone_number)
    {
        try
        {
            url = "http://"+server+"/api/Clients/IsClientExist?phonenumber="+phone_number;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Clients/IsClientExist?phonenumber="+phone_number;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Clients/IsClientExist?phonenumber="+phone_number;
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    //Get infos affectation client (Paiement, solde, facturation)

    public String GetSoldeClient(String code_client)
    {
        try
        {
           url = "http://"+server+"/api/Affectation/GetClientAffectesParClient?codeClient="+code_client;
//           url = "http://afrisofttech-003-site31.btempurl.com/api/Affectation/GetClientAffectesParClient?codeClient="+code_client;
//           url = "http://afrisofttech-003-site13.btempurl.com/api/Affectation/GetClientAffectesParClient?codeClient="+code_client;
           response = call(url);
        }catch (Exception e)
        {

        }
        return response;
    }

    // Get releves client

    public String GetRelevesClient(String code_client, String num_compte)
    {
        try
        {
            url = "http://"+server+"/api/Compte/GetRelevesClient?code_client="+code_client+"&num_compte="+num_compte;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Compte/GetRelevesClient?code_client="+code_client+"&num_compte="+num_compte;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/Compte/GetRelevesClient?code_client="+code_client+"&num_compte="+num_compte;
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    // Get fiche stock

    public String GetFicheStock(String code_article, String code_depot, String date_debut, String date_fin)
    {
        try
        {
            url = "http://"+server+"/api/FicheEtSommaireStock/GetFicheStock?code_article="+code_article+"&code_depot="+code_depot+"&date_debut="+date_debut+"&date_fin="+date_fin;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/FicheEtSommaireStock/GetFicheStock?code_article="+code_article+"&code_depot="+code_depot+"&date_debut="+date_debut+"&date_fin="+date_fin;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/FicheEtSommaireStock/GetFicheStock?code_article="+code_article+"&code_depot="+code_depot+"&date_debut="+date_debut+"&date_fin="+date_fin;
            response = call(url);
        }catch (Exception e)
        {

        }

        return response;
    }

    // Get mouvement stock

    public String GetMouvementStock(String code_depot, String date_debut, String date_fin)
    {
        try
        {
            url = "http://"+server+"/api/FicheEtSommaireStock/GetMouvementStock?code_depot="+code_depot+"&date_debut="+date_debut+"&date_fin="+date_fin;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/FicheEtSommaireStock/GetMouvementStock?code_depot="+code_depot+"&date_debut="+date_debut+"&date_fin="+date_fin;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/FicheEtSommaireStock/GetMouvementStock?code_depot="+code_depot+"&date_debut="+date_debut+"&date_fin="+date_fin;
            response = call(url);
        }catch (Exception e)
        {

        }
        return response;
    }

    // Get sommaire stock

    public String GetSommaireStock(String code_depot, String date)
    {
        try
        {
            url = "http://"+server+"/api/FicheEtSommaireStock/GetSommaireStock?code_depot="+code_depot+"&date="+date;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/FicheEtSommaireStock/GetSommaireStock?code_depot="+code_depot+"&date="+date;
//            url = "http://afrisofttech-003-site13.btempurl.com/api/FicheEtSommaireStock/GetSommaireStock?code_depot="+code_depot+"&date="+date;
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    // Get liste des chambres non occupées

    public String GetChambreNonOccupees()
    {
        try
        {
            url = "http://"+server+"/api/Chambre/GetListeChambreNonOccupees";
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Chambre/GetListeChambreNonOccupees";
            //url = "http://afrisofttech-003-site13.btempurl.com/api/Chambre/GetListeChambreNonOccupees";
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }


    // ======

    public String GetListePompe(String nomUtilisateur)
    {
        try
        {
            url = "http://"+server+"/api/Pompe/GetListePompe?nomUtilisateur="+nomUtilisateur;
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Pompe/GetListePompe";
            response = call(url);

        }catch (Exception e)
        {

        }

        return response;
    }

    //----

    public  String GetCodePompe(String pompe)
    {
        try
        {
            url= "http://"+server+"/api/Pompe/GetCodePompe?pompe="+pompe;
//            url= "http://afrisofttech-003-site31.btempurl.com/api/Pompe/GetCodePompe?pompe="+pompe;
            response = call(url);

        }catch (Exception e)
        {

        }

        return response;
    }

    public  String GetIndexPompe(String pompe)
    {
        try
        {
            url= "http://"+server+"/api/Pompe/GetIndexPompe?pompe="+pompe;
//            url= "http://afrisofttech-003-site31.btempurl.com/api/Pompe/GetIndexPompe?pompe="+pompe;
            response = call(url);

        }catch (Exception e)
        {

        }

        return response;
    }
    public  String GetIndexInitialPompe(String pompe)
    {
        try
        {
            url= "http://"+server+"/api/Pompe/GetIndexInitialPompe?pompe="+pompe;
//            url= "http://afrisofttech-003-site31.btempurl.com/api/Pompe/GetIndexPompe?pompe="+pompe;
            response = call(url);

        }catch (Exception e)
        {

        }

        return response;
    }

    //Get liste depot

    public String GetListeDepot()
    {
        try
        {
            url = "http://"+server+"/api/Depot/GetListeDepot";
            response = call(url);
            Log.e("Depot", response);
        }catch (Exception e)
        {

        }
        return response;
    }

    //get compte stock affecte

    public String GetComptStockAffecte(String codeDepot)
    {
        try
        {
            url = "http://"+server+"/api/Depot/GetCompteStockAffecte?codeDepot="+codeDepot;
            response = call(url);
            Log.e("UUshher", response);
        }catch (Exception e)
        {
            Log.e("UUshher", response);
        }
        return response;
    }

    //get code et compte depot par designation depot

    public String GetCodeEtCompteDepot(String designationDepot)
    {
        try
        {
            url = "http://"+server+"/api/Depot/GetCompteEtCodeDepot?designation="+designationDepot;
            response = call(url);

        }catch (Exception e)
        {

        }
        return response;
    }

    public String GetSoldeAbonnesParArticle(String num_compte)
    {
        try
        {
            url = "http://"+server+"/api/Stock/GetSoldeAbonnesParArticle?num_compte="+num_compte;
            //url = "http://192.168.1.217/WebApisStation/api/Stock/GetSoldeAbonnesParArticle?num_compte=41101";
//            url = "http://afrisofttech-003-site31.btempurl.com/api/Stock/GetSoldeAbonnesParArticle?num_compte="+num_compte;
            response = call(url);
        }catch (Exception e)
        {

        }
        return response;
    }

    // Get prix client abonnes par Article

    public String GetPrixAbonnesParArticle(String numCompte, String codeArticle)
    {
        try
        {
            url = "http://"+server+"/api/Compte/GetPrixAbonnesParArticle?numCompte="+numCompte+"&codeArticle="+codeArticle;
            response = call(url);
        }catch (Exception e)
        {

        }
        return response;
    }

    public String GetCodeDepot(String designationDepot)
    {
        try
        {
            url = "http://"+server+"/api/Depot/GetCodeDepot?designationDepot="+designationDepot;
            response = call(url);
        }catch (Exception e)
        {

        }
        return response;
    }
}
