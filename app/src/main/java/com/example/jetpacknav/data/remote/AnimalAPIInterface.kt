package com.example.jetpacknav.data.remote

import com.example.jetpacknav.data.remote.model.AnimalApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface AnimalAPIInterface {

    //Making a GET request to the API
    @GET("/facts/random")
    suspend fun getAnimal(@QueryMap params: Map<String, String>
    ) : Response<AnimalApiModel>

}