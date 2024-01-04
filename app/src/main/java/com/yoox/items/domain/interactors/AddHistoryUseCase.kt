package com.yoox.items.domain.interactors

import com.yoox.items.app.repository.HistoryRepository
import com.yoox.items.domain.model.History
import com.yoox.items.domain.model.Item
import com.yoox.items.domain.model.ItemDetails
import com.yoox.items.utilities.Const.EMPTY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

open class AddHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {

    suspend operator fun invoke(item: ItemDetails): Flow<Boolean> = channelFlow {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        historyRepository.addItem(
            History(
                cod = item.cod,
                brand = item.brand.name ?: EMPTY,
                microCategory = item.category.name ?: EMPTY,
                price = item.getPrice() ?: EMPTY,
                urlImage = item.urlImage,
                date = LocalDateTime.now().format(formatter),
            )
        ).collectLatest { data ->
            send(data)
        }
    }
}
