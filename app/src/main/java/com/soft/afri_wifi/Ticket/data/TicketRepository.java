package com.soft.afri_wifi.Ticket.data;

import com.soft.afri_wifi.DataBase.PostApiUrl;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TicketRepository {
    private static TicketRepository instance;

    private TicketConnection ticketConnection;

    public static TicketRepository getInstance() {
        if (instance == null) {
            instance = new TicketRepository();
        }
        return instance;
    }

    public TicketRepository() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PostApiUrl.URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ticketConnection = retrofit.create(TicketConnection.class);

    }

    public TicketConnection ticketConnexion()
    {
        return ticketConnection;
    }
}
