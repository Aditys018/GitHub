package com.aditys.gojek.network

import com.aditys.gojek.model.DataClassRepo
import retrofit2.http.GET
import retrofit2.http.Query


interface GitHubApi {
    @GET("search/repositories")
    suspend fun getTrendingRepositories(
        @Query("q") query: String = "stars:>1",
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ): SearchResponse
}

data class SearchResponse(
    val items: List<DataClassRepo>
)