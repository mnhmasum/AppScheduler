package com.meldcx.appscheduler.dependencyinjection

import android.content.Context
import com.meldcx.appscheduler.data.CurrencyDao
import com.meldcx.appscheduler.dependencyinjection.scope.PerActivity
import com.meldcx.appscheduler.repository.CurrencyConverterRepository
import com.meldcx.appscheduler.ui.currency.CurrencyConverterViewModel
import com.meldcx.appscheduler.ui.currency.CurrencyViewAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by nazmul 08/06/2022.
 */
@Module
class ActivityModule(private val context: Context) {

    @Provides
    fun provideCurrencyRepository(currencyDao: CurrencyDao): CurrencyConverterRepository {
        return CurrencyConverterRepository(currencyDao)
    }

    @Provides
    @PerActivity
    fun provideCurrencyViewModel(currencyListRepository: CurrencyConverterRepository): CurrencyConverterViewModel {
        return CurrencyConverterViewModel(currencyListRepository)
    }

    @Provides
    @PerActivity
    fun provideCurrencyViewAdapter(currencyConverterViewModel: CurrencyConverterViewModel): CurrencyViewAdapter {
        return CurrencyViewAdapter(currencyConverterViewModel)
    }

}