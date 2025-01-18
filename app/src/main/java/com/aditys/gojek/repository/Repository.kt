package com.aditys.gojek.repository

import com.aditys.gojek.model.RepositoryEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    val trendingRepositories: Flow<List<RepositoryEntity>>
    suspend fun fetchAndStoreRepositories()
}