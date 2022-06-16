package com.example.myapplication.features.newslist.framework

import com.example.myapplication.features.newslist.domin.datasource.remote.NewsListRemoteDataSource
import com.example.simplecomposeapp.features.newslist.entities.response.NewsResponse
import io.reactivex.Single


class NewsListRemoteDataSourceImpl(
    private val endpoint: NewsListEndpoint
) : NewsListRemoteDataSource {
    override fun getAllNews(): Single<NewsResponse> {
        return endpoint.getBreakingNews()
    }
}