package com.aditys.gojek.network

import com.aditys.gojek.model.Repository
import retrofit2.http.GET

interface GitHubApi {
    @GET("repositories/trending")
    suspend fun getTrendingRepositories(): List<Repository>
}