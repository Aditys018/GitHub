package com.aditys.gojek.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aditys.gojek.model.RepositoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepositoryDao {
    @Query("SELECT * FROM repositories")
    fun getAllRepositories(): Flow<List<RepositoryEntity>>

    @Insert
    suspend fun insertRepositories(repositories: List<RepositoryEntity>)

    @Query("DELETE FROM repositories")
    suspend fun deleteAllRepositories()
}