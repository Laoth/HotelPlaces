package com.example.gus.places.service;

import com.example.gus.places.model.PlaceModel;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Laotshi on 11/16/15.
 */
public interface PlaceService {
    @GET("/typeahead?type=")
    PlaceModel listPlaces(@Query("lookup") String country);
}
