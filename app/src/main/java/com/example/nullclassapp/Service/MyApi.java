package com.example.nullclassapp.Service;



import com.example.nullclassapp.model.ChatModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {
    @GET("2.3/search/advanced")
    Call<ChatModel> getResponse(@Query("page") int page, @Query("pagesize") int pagesize, @Query("order") String order, @Query("sort") String sort
    , @Query("q") String query, @Query("site") String site);
}
