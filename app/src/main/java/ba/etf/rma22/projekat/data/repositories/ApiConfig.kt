package ba.etf.rma22.projekat.data.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    val defaultni : String = "https://rma22ws.herokuapp.com"
    val baseURL = "https://rma22ws.herokuapp.com"


    var retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(defaultni)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    fun postaviBaseURL(baseURL : String) : Unit {
        retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}