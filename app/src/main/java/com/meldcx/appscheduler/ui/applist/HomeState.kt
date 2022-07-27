package com.meldcx.appscheduler.ui.applist

import com.meldcx.appscheduler.data.AppItem

sealed class HomeState {
    object Loading : HomeState()
    data class ResultAllPersona(val data : List<AppItem>): HomeState()
    data class Exception(val callErrors: CallErrors) : HomeState()
}