package com.aditys.gojek.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aditys.gojek.model.RepositoryEntity

@Database(entities = [RepositoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}
