
package com.soft.afri_wifi.Article.data;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleConnexion {
    @GET("api/Stock/GetClientArticles")
    Call<List<ArticleResponse>> getListeArticle();
}
