package com.example.myapplication.features.newslist.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.features.newslist.entities.ui.NewsUIModel

@Composable
fun NewsList(
    newsList: List<NewsUIModel>,
    onNavigateToRecipeDetailScreen: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.surface)
    ) {
        if (newsList.isEmpty()) {
            NothingHere()
        } else {
            LazyColumn {
                itemsIndexed(
                    items = newsList
                ) { index, news ->
                    NewsCard(
                        news = news,
                        onClick = {
                            onNavigateToRecipeDetailScreen(news.id)
                        }
                    )
                }
            }
        }
    }
}







