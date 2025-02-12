package com.aditys.gojek.di

import android.content.Context
import androidx.room.Room
import com.aditys.gojek.model.AppDatabase
import com.aditys.gojek.model.RepositoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "repositories.db"
        ).build()
    }

    @Provides
    fun provideRepositoryDao(database: AppDatabase): RepositoryDao {
        return database.repositoryDao()
    }
}