package com.aditys.gojek.userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.aditys.gojek.model.RepositoryEntity
import com.aditys.gojek.viewmodel.MainViewModel
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val repositories by viewModel.trendingRepositories.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    Column {
        Text(
            text = "TRENDING REPOSITORIES",
            modifier = Modifier.padding(25.dp),
            style = MaterialTheme.typography.headlineMedium
        )
        SwipeRefresh(
            state = SwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.fetchTrendingRepositories() }
        ) {
            if (isLoading) {
                ShimmerLoading()
            } else if (errorMessage != null) {
                Text(text = errorMessage ?: "Unknown error", color = Color.Red)
            } else {
                LazyColumn {
                    items(repositories) { repo ->
                        RepositoryItem(repo)
                    }
                }
            }
        }
    }
}

@Composable
fun ShimmerLoading() {
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.Window)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        repeat(10) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .padding(vertical = 8.dp)
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .shimmer(shimmer)
            )
        }
    }
}

@Composable
fun ErrorMessage(message: String?, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message ?: "Unknown error", color = Color.Red)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text(text = "Try Again")
        }
    }
}

@Composable
fun RepositoryItem(repo: RepositoryEntity) {
    val isExpanded = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .clickable { isExpanded.value = !isExpanded.value }
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(repo.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 8.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(text = repo.ownerName)
                Text(text = repo.name)
            }
            Icon(
                imageVector = if (isExpanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null
            )
        }
        if (isExpanded.value) {
            Column {
                repo.description?.let { Text(text = it) }
                Text(text = "Language: ${repo.language ?: "Unknown"}")
                Text(text = "Stars: ${repo.stars}")
                Text(text = "Forks: ${repo.forks}")
            }
        }
    }
}