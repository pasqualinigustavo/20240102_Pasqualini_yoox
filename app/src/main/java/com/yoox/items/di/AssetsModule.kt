package com.yoox.items.di

import com.yoox.items.app.repository.ItemsRepository
import com.yoox.items.app.repository.ItemsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AssetsModule {

    @Binds
    abstract fun bindRepository(impl: ItemsRepositoryImpl): ItemsRepository

}
