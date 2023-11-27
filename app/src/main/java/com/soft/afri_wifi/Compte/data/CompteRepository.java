package com.soft.afri_wifi.Compte.data;

import com.soft.afri_wifi.DataBase.PostApiUrl;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompteRepository {
    private static CompteRepository instance;
    private CompteConnexion compteConnexion;

    public static CompteRepository getInstance() {
        if (instance == null) {
            instance = new CompteRepository();
        }
        return instance;
    }

    public CompteRepository() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PostApiUrl.URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        compteConnexion = retrofit.create(CompteConnexion.class);

    }

    public CompteConnexion compteConnexion()
    {
        return compteConnexion;
    }
}
