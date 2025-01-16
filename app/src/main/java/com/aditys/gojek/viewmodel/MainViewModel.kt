package com.aditys.gojek.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditys.gojek.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val trendingRepositories = repository.trendingRepositories
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun refreshRepositories() {
        viewModelScope.launch {
            repository.fetchAndStoreRepositories()
        }
    }
}

