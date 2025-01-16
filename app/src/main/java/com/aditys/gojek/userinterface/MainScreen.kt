package com.aditys.gojek.userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aditys.gojek.model.RepositoryEntity
import com.aditys.gojek.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {

    val repositories by viewModel.trendingRepositories.collectAsState(initial = emptyList())


    LazyColumn {
        items(repositories) { repo ->
            RepositoryItem(repo)
        }
    }
}

@Composable
fun RepositoryItem(repo: RepositoryEntity) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .clickable {  }
    ) {

        Text(text = repo.name, fontWeight = FontWeight.Bold)
        repo.description?.let { Text(text = it) }
        Text(text = "Language: ${repo.language ?: "Unknown"}")
        Text(text = "Stars: ${repo.stars}")
        Text(text = "Forks: ${repo.forks}")
    }
}
