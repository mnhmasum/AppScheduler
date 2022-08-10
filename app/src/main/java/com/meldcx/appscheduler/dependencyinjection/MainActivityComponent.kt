package com.meldcx.appscheduler.dependencyinjection

import dagger.Component
import com.meldcx.appscheduler.dependencyinjection.scope.PerActivity
import com.meldcx.appscheduler.ui.currency.CurrencyConverterActivity

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface MainActivityComponent {
    fun inject(currencyActivity: CurrencyConverterActivity)
}