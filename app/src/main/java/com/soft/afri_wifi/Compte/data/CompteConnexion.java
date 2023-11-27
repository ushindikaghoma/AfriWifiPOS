package com.soft.afri_wifi.Compte.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CompteConnexion {
    @GET("api/Compte/GetSoldeParCompte")
    Call<Double> getSoldeCaisse (@Query("numCompte") int numCompte);

    @GET("api/Compte/GetListeFournisseur")
    Call<List<CompteResponse>> getListeFournisseur();
    @GET("api/Compte/ReleveCompteTopTenOp")
    Call<List<CompteResponse>> getTenLastOp(@Query("num_compte") int numCompte);
    @GET("api/Compte/ReleveCompteParDate")
    Call<List<CompteResponse>> getReleveCompteParDate(@Query("num_compte") int numCompte,
                                            @Query("date_debut") String date_debut,
                                            @Query("date_fin") String date_fin);
}
