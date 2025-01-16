package com.aditys.gojek.repository

import com.aditys.gojek.database.RepositoryDao
import com.aditys.gojek.model.RepositoryEntity
import com.aditys.gojek.network.GitHubApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val api: GitHubApi,
    private val dao: RepositoryDao
) {

    val trendingRepositories: Flow<List<RepositoryEntity>> =
        dao.getAllRepositories()

    suspend fun fetchAndStoreRepositories() {
        val response = api.getTrendingRepositories()
        dao.deleteAllRepositories()
        dao.insertRepositories(response)
    }
}
