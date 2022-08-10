package com.meldcx.appscheduler.apiclient

import com.meldcx.appscheduler.retrofit.apiClient
import com.meldcx.appscheduler.retrofit.initRetrofit
import com.meldcx.appscheduler.utils.Constant
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ApiCallClientTest {
    @Test
    fun testPlacesService() {
        runBlocking {
            initRetrofit()
            val result = apiClient().getRepositories(Constant.API_KEY)
            val error = result.errorBody()
            assert(error == null)
            val response = result.body()
            assert(response != null)
            assert(result.code() == 200)
        }
    }
}