package com.meldcx.appscheduler.ui.applist

sealed class CallErrors {
    object ErrorEmptyData : CallErrors()
    object ErrorServer: CallErrors()
    data class ErrorException(val throwable: Throwable) : CallErrors()
}