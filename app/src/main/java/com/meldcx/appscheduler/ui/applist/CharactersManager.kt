package com.meldcx.appscheduler.ui.applist

import com.meldcx.appscheduler.data.AppItem
import kotlinx.coroutines.flow.Flow

interface CharactersManager {
    fun getAllCharacters(): Flow<Result<List<AppItem>>>
    fun searchCharacters(name : String) : Flow<Result<List<AppItem>>>
}