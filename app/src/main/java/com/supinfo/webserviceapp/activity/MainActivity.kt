package com.supinfo.webserviceapp.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.supinfo.webserviceapp.R
import com.supinfo.webserviceapp.adapter.TVShowAdapter
import com.supinfo.webserviceapp.apiInterface.ApiInterface
import com.supinfo.webserviceapp.client.ApiClient
import com.supinfo.webserviceapp.constant.Constants
import com.supinfo.webserviceapp.model.TVShowResponse
import com.supinfo.webserviceapp.model.TvShow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var tvShowAdapter: TVShowAdapter
    private lateinit var recyclerTVSHow: RecyclerView
    private lateinit var tvShowResponse: TVShowResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * 1 - Ajout de la dépendence Retrofit, GSON et GSON-CONVERTER
         * 2 - Créer des classes POJO utiles pour les données
         * 3 - Créer une classe ApiClient, retournant une instance de Retrofit
         * 4 - Créer une Interface ApiInterface (HTTP Endpoint)
         * 5 - Utiliser Retrofit
        **/

        val apiInterface = ApiClient.getRetrofitClient()?.create(ApiInterface::class.java)
        val callRetrofit: Call<TVShowResponse>? = apiInterface?.getMostPopular()
        callRetrofit?.enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(
                call: Call<TVShowResponse>,
                response: Response<TVShowResponse>
            ) {
                tvShowResponse = response.body()!!
                displayTVShows(tvShowResponse.tvShows)
            }
            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                Log.d(Constants.TVSHOW, t.localizedMessage)
            }
        })
    }

    private fun displayTVShows(tvShows: List<TvShow>) {

        // Init adapter and layoutManager
        linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        tvShowAdapter = TVShowAdapter(tvShows)

        // Init RecyclerView
        recyclerTVSHow = findViewById(R.id.recyclerTVSHow)
        recyclerTVSHow.setHasFixedSize(false)
        recyclerTVSHow.layoutManager = linearLayoutManager
        recyclerTVSHow.adapter = tvShowAdapter
    }

}