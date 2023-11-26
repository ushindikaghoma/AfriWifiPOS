
package com.soft.afri_wifi.Article.data;


import com.soft.afri_wifi.DataBase.PostApiUrl;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleRepository {
    private static ArticleRepository instance;

    private ArticleConnexion articleConnexion;

    public static ArticleRepository getInstance() {
        if (instance == null) {
            instance = new ArticleRepository();
        }
        return instance;
    }

    public ArticleRepository() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PostApiUrl.URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        articleConnexion = retrofit.create(ArticleConnexion.class);

    }

    public ArticleConnexion articleConnexion()
    {
        return articleConnexion;
    }
}
