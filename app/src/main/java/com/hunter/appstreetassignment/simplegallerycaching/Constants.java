package com.hunter.appstreetassignment.simplegallerycaching;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

public class Constants {
    public static String BASE_URL = "http://www.splashbase.co/api/v1/images/";
    public static String API_KEY = "10195958-7a6b24272417e5bdb818a2418";
    public static String PIXA_BAY = "https://pixabay.com/api/?key=10195958-7a6b24272417e5bdb818a2418&min_height=400&min_width=400&image_type=photo&q=yellow+flowers&page="; //base url including api key
//    https://pixabay.com/api/?key=10195958-7a6b24272417e5bdb818a2418&q=yellow+flowers&image_type=photo&pretty=true

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getPhotos(String query, Context context, int page, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JSONObject params = new JSONObject();
        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(params.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        client.get(context, query.length() > 0 ? PIXA_BAY + page + "&q=" + query : PIXA_BAY + page, stringEntity, RequestParams.APPLICATION_JSON, asyncHttpResponseHandler);
    }
}