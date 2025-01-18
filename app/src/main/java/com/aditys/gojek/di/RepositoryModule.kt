package com.aditys.gojek.di

import com.aditys.gojek.repository.Repository
import com.aditys.gojek.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}