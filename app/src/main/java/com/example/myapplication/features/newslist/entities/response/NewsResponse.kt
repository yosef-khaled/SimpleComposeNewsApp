package com.example.simplecomposeapp.features.newslist.entities.response

import com.example.myapplication.features.newslist.entities.response.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)