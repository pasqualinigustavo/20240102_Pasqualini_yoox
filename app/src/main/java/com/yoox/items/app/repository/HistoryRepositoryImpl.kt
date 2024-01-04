package com.yoox.items.app.repository

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yoox.items.domain.model.History
import com.yoox.items.utilities.Const
import com.yoox.items.utilities.Const.EMPTY
import com.yoox.items.utilities.Const.HISTORY_ITEMS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.reflect.Type
import javax.inject.Inject


class HistoryRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : HistoryRepository {

    private fun readHistoryItems() : List<History> {
        val items = sharedPreferences.getString(HISTORY_ITEMS, EMPTY).orEmpty()
        if(items.isNotEmpty()) {
            val listType: Type = object : TypeToken<ArrayList<History?>?>() {}.getType()
            return Gson().fromJson(items, listType)
        }
        return emptyList()
    }

    override fun getHistory() : Flow<MutableList<History>?> = flow {
        runCatching {
            readHistoryItems()
        }.onSuccess {
            emit(it.toMutableList())
        }.onFailure {
            emit(null)
            throw it
        }
    }.flowOn(Const.requestDispatchers)
    override fun addItem(history: History) : Flow<Boolean> = flow {
        runCatching {
            addHistoryItem(history)
        }.onSuccess {
            emit(true)
        }.onFailure {
            emit(false)
            throw it
        }
    }.flowOn(Const.requestDispatchers)

    private fun addHistoryItem(history: History) {
        val list = readHistoryItems().toMutableList()
        if(!list.contains(history)) {
            list.add(history)
            val itemList: String = Gson().toJson(list)
            sharedPreferences.edit()
                .putString(HISTORY_ITEMS, itemList).apply()
        }
    }
}

