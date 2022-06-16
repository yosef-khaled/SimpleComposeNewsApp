package com.example.myapplication.features.newslist.entities.response

import com.example.news.models.Source

data class Article(
    val source: Source?,
    val author: String?,
    val title: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val url: String?,
    val urlToImage: String?
)