package com.yoox.items.di

import com.yoox.items.app.repository.HistoryRepository
import com.yoox.items.app.repository.HistoryRepositoryImpl
import com.yoox.items.app.repository.ItemsRepository
import com.yoox.items.app.repository.ItemsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindItemsRepository(impl: ItemsRepositoryImpl): ItemsRepository

    @Binds
    abstract fun bindHistoryRepository(impl: HistoryRepositoryImpl): HistoryRepository

}
