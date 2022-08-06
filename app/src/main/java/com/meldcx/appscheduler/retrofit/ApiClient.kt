package com.meldcx.appscheduler.retrofit

import com.meldcx.appscheduler.data.CurrencyData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("latest.json")
    suspend fun getRepositories(@Query("app_id") apiKey: String): Response<CurrencyData>
}

fun apiClient() : ApiClient = retrofit().create(ApiClient::class.java)