package com.aditys.gojek.repository

import com.aditys.gojek.extensions.toEntity
import com.aditys.gojek.model.RepositoryDao
import com.aditys.gojek.model.RepositoryEntity
import com.aditys.gojek.network.GitHubApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val gitHubApi: GitHubApi,
    private val repositoryDao: RepositoryDao
) : Repository {

    override val trendingRepositories: Flow<List<RepositoryEntity>> = flow {
        emit(getTrendingRepositories())
    }

    override suspend fun getTrendingRepositories(): List<RepositoryEntity> {
        val currentTime = System.currentTimeMillis()
        val cachedRepositories = repositoryDao.getAllRepositories()

        return if (cachedRepositories.isNotEmpty() && currentTime - cachedRepositories.first().lastFetched < 2 * 60 * 60 * 1000) {
            cachedRepositories
        } else {
            val response = gitHubApi.getTrendingRepositories().items.map { it.toEntity(currentTime) }
            repositoryDao.deleteAllRepositories()
            repositoryDao.insertRepositories(response)
            response
        }
    }
}