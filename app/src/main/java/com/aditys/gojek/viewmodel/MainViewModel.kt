package com.aditys.gojek.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditys.gojek.extensions.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.aditys.gojek.model.RepositoryEntity
import com.aditys.gojek.network.GitHubApi
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val gitHubApi: GitHubApi
) : ViewModel() {
    private val _trendingRepositories = MutableStateFlow<List<RepositoryEntity>>(emptyList())
    val trendingRepositories: StateFlow<List<RepositoryEntity>> = _trendingRepositories

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchTrendingRepositories()
    }

    fun fetchTrendingRepositories() {
        viewModelScope.launch {
            _isLoading.value = true
            _isRefreshing.value = true
            try {
                val response = gitHubApi.getTrendingRepositories()
                _trendingRepositories.value = response.items.map { it.toEntity() }
            } catch (e: HttpException) {
                if (e.code() == 503) {
                    _errorMessage.value = "Service Unavailable. Please try again later."
                } else {
                    _errorMessage.value = "An unexpected error occurred."
                }
            } finally {
                _isLoading.value = false
                _isRefreshing.value = false
            }
        }
    }
}