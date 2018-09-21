package com.hunter.appstreetassignment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("latest")
    Call<Image> getPhotos();

//    https://api.pexels.com/v1/search?query=example+query&per_page=15&page=1

    @GET("search")
    Call<Image> searchPhotos(@Query("query") String query, @Query("per_page") int perPage, @Query("page") int page);
}
