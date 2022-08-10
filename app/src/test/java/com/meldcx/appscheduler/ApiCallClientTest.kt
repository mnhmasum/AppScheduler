package com.meldcx.appscheduler

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
            val errorBody = result.errorBody()
            assert(errorBody == null)
            val responseWrapper = result.body()
            assert(responseWrapper != null)
            assert(result.code() == 200)
        }
    }
}