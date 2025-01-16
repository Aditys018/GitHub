package com.aditys.gojek.network

import com.aditys.gojek.model.RepositoryEntity
import retrofit2.http.GET

interface GitHubApi {
    @GET("repositories?language=&since=daily&spoken_language_code=")
    suspend fun getTrendingRepositories(): List<RepositoryEntity>
}