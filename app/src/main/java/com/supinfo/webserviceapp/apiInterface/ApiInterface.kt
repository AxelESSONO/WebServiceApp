package com.supinfo.webserviceapp.apiInterface

import com.supinfo.webserviceapp.model.TVShowResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("most-popular")
    fun getMostPopular() : Call<TVShowResponse>

}