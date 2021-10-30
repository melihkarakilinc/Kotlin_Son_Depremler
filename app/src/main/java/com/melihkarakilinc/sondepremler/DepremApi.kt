package com.melihkarakilinc.sondepremler

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DepremApi {
    @GET("api/pure_api.php")
    fun getDeprem(): Call<DepremInf>

    companion object {
        val BASE_URL = "https://deprem.odabas.xyz/"
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(DepremApi::class.java)
    }
}