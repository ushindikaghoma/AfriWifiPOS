package com.soft.afri_wifi.Comptabilite.data;

import com.soft.afri_wifi.DataBase.PostApiUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComptabiliteRepository {

    private static ComptabiliteRepository instance;

    private ComptabiliteConnexion comptabiliteConnexion;

    public static ComptabiliteRepository getInstance() {
        if (instance == null) {
            instance = new ComptabiliteRepository();
        }
        return instance;
    }

    public ComptabiliteRepository() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PostApiUrl.URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        comptabiliteConnexion = retrofit.create(ComptabiliteConnexion.class);

    }

    public ComptabiliteConnexion comptabiliteConnexion()
    {
        return comptabiliteConnexion;
    }
}
