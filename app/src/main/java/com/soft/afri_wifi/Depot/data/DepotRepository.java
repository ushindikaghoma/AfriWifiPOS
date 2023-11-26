package com.soft.afri_wifi.Depot.data;



import com.soft.afri_wifi.DataBase.PostApiUrl;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DepotRepository {
    private static DepotRepository instance;

    private DepotConnexion depotConnexion;

    public static DepotRepository getInstance() {
        if (instance == null) {
            instance = new DepotRepository();
        }
        return instance;
    }

    public DepotRepository() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PostApiUrl.URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        depotConnexion = retrofit.create(DepotConnexion.class);

    }

    public DepotConnexion depotConnexion()
    {
        return depotConnexion;
    }
}
