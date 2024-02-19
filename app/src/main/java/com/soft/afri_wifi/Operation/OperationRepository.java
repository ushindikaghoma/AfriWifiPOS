package com.soft.afri_wifi.Operation;

import android.content.Context;

import com.soft.afri_wifi.DataBase.PostApiUrl;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OperationRepository {
    private static OperationRepository instance;
    Context context;

    private OperationConnexion operationConnexion;

    public static OperationRepository getInstance() {
        if (instance == null) {
            instance = new OperationRepository();
        }
        return instance;
    }

    public OperationRepository() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PostApiUrl.URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        operationConnexion = retrofit.create(OperationConnexion.class);

    }

    public OperationConnexion operationConnexion()
    {
        return operationConnexion;
    }
}
