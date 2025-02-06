// MainViewModel.kt
package com.aditys.gojek.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditys.gojek.model.RepositoryEntity
import com.aditys.gojek.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
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
                _trendingRepositories.value = repository.getTrendingRepositories()
            } catch (e: Exception) {
                _errorMessage.value = "An unexpected error occurred."
            } finally {
                _isLoading.value = false
                _isRefreshing.value = false
            }
        }
    }
}