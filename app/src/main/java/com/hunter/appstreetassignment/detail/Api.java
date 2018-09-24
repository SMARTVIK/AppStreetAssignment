package com.hunter.appstreetassignment.detail;

import com.hunter.appstreetassignment.simplegallerycaching.ImageModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("latest")
    Call<ImageModel> getPhotos();

    //    https://api.pexels.com/v1/search?query=example+query&per_page=15&page=1
    @GET("search")
    Call<ImageModel> searchPhotos(@Query("query") String query, @Query("per_page") int perPage, @Query("page") int page);

    @GET("https://pixabay.com/api/?key=10195958-7a6b24272417e5bdb818a2418&image_type=photo&q=yellow+flowers&page=1")
    Call<ImageModel> getPicsFromPixaApi();
//    https://pixabay.com/api/?key=10195958-7a6b24272417e5bdb818a2418&q=yellow+flowers&image_type=photo&pretty=true
}
