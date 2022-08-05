package com.meldcx.appscheduler.repository

import com.meldcx.appscheduler.retrofit.apiClient
import com.meldcx.appscheduler.utils.Constant


class CurrencyListRepository() : CurrencyListRepositoryInterface {
    override suspend fun getCurrencyRateList() = apiClient().getRepositories(Constant.API_KEY)
}