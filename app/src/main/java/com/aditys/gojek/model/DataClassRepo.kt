package com.aditys.gojek.model


data class DataClassRepo(
    val id: Int,
    val name: String,
    val description: String?,
    val language: String?,
    val stargazers_count: Int,
    val forks_count: Int ,
    val owner: Owner
)

data class Owner(
    val avatar_url: String
)