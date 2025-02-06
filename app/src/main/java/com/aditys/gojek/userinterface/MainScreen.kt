package com.aditys.gojek.userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.aditys.gojek.model.RepositoryEntity
import com.aditys.gojek.viewmodel.MainViewModel
import com.valentinilk.shimmer.shimmer

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val repositories by viewModel.trendingRepositories.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

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

@Composable
fun ShimmerLoading() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        repeat(5) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 8.dp)
                    .background(Color.Gray, RoundedCornerShape(8.dp))
                    .shimmer()
            )
        }
    }
}

@Composable
fun RepositoryItem(repo: RepositoryEntity) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Image(
            painter = rememberImagePainter(repo.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .padding(end = 16.dp),
            contentScale = ContentScale.Crop
        )
        Column {
            Text(text = repo.name)
            repo.description?.let { Text(text = it) }
            Text(text = "Language: ${repo.language ?: "Unknown"}")
            Text(text = "Stars: ${repo.stars}")
            Text(text = "Forks: ${repo.forks}")
        }
    }
}