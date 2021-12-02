package com.supinfo.webserviceapp.client

import com.supinfo.webserviceapp.constant.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object{

        private var retrofit : Retrofit? = null
        fun getRetrofitClient() : Retrofit? {

            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}