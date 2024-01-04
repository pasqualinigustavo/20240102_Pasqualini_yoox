package com.yoox.items.app.repository

import com.yoox.items.domain.model.History
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun addItem(history: History): Flow<Boolean>
    fun getHistory(): Flow<MutableList<History>?>
}
