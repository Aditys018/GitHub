package com.aditys.gojek.di

import android.app.Application
import androidx.room.Room
import com.aditys.gojek.database.AppDatabase
import com.aditys.gojek.database.RepositoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "trending_repos.db").build()

    @Provides
    fun provideRepositoryDao(database: AppDatabase): RepositoryDao =
        database.repositoryDao()
}
