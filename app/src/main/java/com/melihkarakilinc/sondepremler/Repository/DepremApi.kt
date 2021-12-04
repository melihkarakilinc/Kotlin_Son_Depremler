package com.melihkarakilinc.sondepremler.Repository

import com.melihkarakilinc.sondepremler.Model.DepremInf
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DepremApi {
    @GET("api/pure_api.php")
    suspend fun getDeprem(): Response<List<DepremInf>>

    companion object {
        val BASE_URL = "https://deprem.odabas.xyz/"
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(DepremApi::class.java)
    }
}