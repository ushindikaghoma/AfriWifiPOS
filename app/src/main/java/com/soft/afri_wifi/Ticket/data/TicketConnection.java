package com.soft.afri_wifi.Ticket.data;

import com.soft.afri_wifi.Article.data.ArticleResponse;
import com.soft.afri_wifi.Operation.OperationResponse;
import com.soft.afri_wifi.Operation.Reponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TicketConnection {
    @GET("api/Ticket/GetListeTcket")
    Call<List<TicketResponse>> getListeTicket();
    @GET("api/Ticket/GetInfosTickect")
    Call<List<ArticleResponse>> getRadomTicket(@Query("codeDepot") String codeDepot,
                                               @Query("catArticle") int catArticle);
    @POST("api/Ticket/SaveMvtTicket")
    Call<Reponse> insertMvtTicket(@Body MvtTicketReponse mvtTicketReponse);
    @GET("api/Ticket/UpdateEtatForfait")
    Call<String> updateEtatTicket(@Query("username") String userName,
                                  @Query("password") String password );
}
