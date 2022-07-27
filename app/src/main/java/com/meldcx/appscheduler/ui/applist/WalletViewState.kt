package com.meldcx.appscheduler.ui.applist

import com.meldcx.appscheduler.data.AppItem

sealed class WalletViewState {
/*
    var walletItems: List<AppItem>? = null,
    val walletAmount: String? = null
*/

    object Loading : WalletViewState()
    data class ResultAllPersona(val data: List<AppItem>) : WalletViewState()
    data class Exception(val callErrors: CallErrors) : WalletViewState()
}