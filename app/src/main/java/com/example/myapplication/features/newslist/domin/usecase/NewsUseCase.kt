package com.example.myapplication.features.newslist.domin.usecase

import com.example.myapplication.features.newslist.domin.datasource.remote.NewsListRemoteDataSource
import com.example.myapplication.features.newslist.entities.response.Article
import com.example.myapplication.features.newslist.entities.ui.NewsUIModel
import io.reactivex.Single
import javax.inject.Inject

class NewsUseCase
@Inject
constructor(
    private val dataSource: NewsListRemoteDataSource
) {

    fun getNewsList(): Single<List<NewsUIModel>> {
        return dataSource.getAllNews()
            .map {news -> news.articles.mapIndexed { index, article ->  article.mapToNewsUIModel(index) } }
    }

    private fun Article.mapToNewsUIModel(id:Int): NewsUIModel =
        NewsUIModel(
            id = id,
            title = title ?: "",
            description = description ?: "",
            imageUrl = urlToImage ?: ""
        )

}

