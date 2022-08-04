package com.meldcx.appscheduler.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {
    @GET("actnowaz.json")
    fun getRepositories(): Call<Example>
}

fun apiClient() : ApiClient = retrofit().create(ApiClient::class.java)