package com.soft.afri_wifi.Comptabilite.data;

import com.soft.afri_wifi.Operation.Reponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ComptabiliteConnexion {
    @POST("api/Comptabilite/SaveMvtCompte")
    Call<Integer> insertMvtCompte(@Body ComptabiliteResponse comptabiliteResponse);

    @POST("api/Comptabilite/SaveMvt")
    Call<Reponse> SaveMvtCompte(@Body ComptabiliteResponse comptabiliteResponse);
    @POST("api/Comptabilite/SaveMvtOneTwo")
    Call<Reponse> SaveMvtCompteOneTwo(@Body ComptabiliteResponse mvtResponse);
}
