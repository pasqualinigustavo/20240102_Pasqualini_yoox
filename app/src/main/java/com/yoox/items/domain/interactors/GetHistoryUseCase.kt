package com.yoox.items.domain.interactors

import com.yoox.items.app.repository.HistoryRepository
import com.yoox.items.domain.model.History
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

open class GetHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {

    suspend operator fun invoke(): Flow<MutableList<History>?> = channelFlow {
        historyRepository.getHistory().collectLatest { data ->
            send(data)
        }
    }
}
