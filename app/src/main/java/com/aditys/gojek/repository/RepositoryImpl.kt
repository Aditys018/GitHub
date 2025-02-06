package com.aditys.gojek.repository

import com.aditys.gojek.extensions.toEntity
import com.aditys.gojek.model.RepositoryEntity
import com.aditys.gojek.network.GitHubApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: GitHubApi
) : Repository {

    private val _trendingRepositories = MutableStateFlow<List<RepositoryEntity>>(emptyList())
    override val trendingRepositories: Flow<List<RepositoryEntity>> = _trendingRepositories

    override suspend fun fetchAndStoreRepositories() {
        withContext(Dispatchers.IO) {
            val response = api.getTrendingRepositories()
            _trendingRepositories.value = response.items.map { it.toEntity() }
        }
    }
}