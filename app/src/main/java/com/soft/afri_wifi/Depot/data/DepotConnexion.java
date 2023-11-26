package com.soft.afri_wifi.Depot.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DepotConnexion {
    @GET("api/Depot/GetListeDepot")
    Call<List<DepotResponse>> getListeDepot();

    @GET("api/Depot/GetCodeDepot")
    Call<String> getCodeDepot(@Query("designationDepot") String designationDepot);

    @GET("api/Compte/GetPrixParDepot")
    Call<Double> getPrixDepot(@Query("numCompte") int numeroCompte,
                              @Query("codeArticle") String codeArticle);

    @GET("api/Depot/GetCompteStockAffecte")
    Call<String> getCompteDepot(@Query("codeDepot") String codeDepot);
}
