package com.example.myapplication.features.newslist.domin.datasource.remote

import com.example.simplecomposeapp.features.newslist.entities.response.NewsResponse
import io.reactivex.Single

interface NewsListRemoteDataSource {
    fun getAllNews() : Single<NewsResponse>
}