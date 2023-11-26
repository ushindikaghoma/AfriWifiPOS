package com.soft.afri_wifi.Comptabilite.data;

import com.soft.afri_wifi.DataBase.PostApiUrl;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MvtRepository {

    private static MvtRepository instance;

    private ComptabiliteConnexion comptabiliteConnexion;

    public static MvtRepository getInstance() {
        if (instance == null) {
            instance = new MvtRepository();
        }
        return instance;
    }

    public MvtRepository() {

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
