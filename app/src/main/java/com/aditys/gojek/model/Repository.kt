package com.aditys.gojek.model

data class Repository(
    val id: Int,
    val name: String,
    val description: String?,
    val language: String?,
    val stars: Int,
    val forks: Int
)