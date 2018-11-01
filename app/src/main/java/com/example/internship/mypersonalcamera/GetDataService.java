package com.example.internship.mypersonalcamera;

import com.example.internship.mypersonalcamera.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {

    @GET("above/{observer_lat}/{observer_lng}/{observer_alt}/{search_radius}/{category_id}/{authorization}")
//    @GET("above/41.702/-76.014/0/90/18/53VELF-92F346-8HGD75-3IPC")
    Call<Response> PostFromURLData(
            @Path("observer_lat") double observer_lat,
            @Path("observer_lng") double observer_lng,
            @Path("observer_alt") double observer_alt,
            @Path("search_radius") int search_radius,
            @Path("category_id") int category_id,
            @Path("authorization") String authorization
    );

//    Callback<List<RootObject>> getAllSatelliteList
}
