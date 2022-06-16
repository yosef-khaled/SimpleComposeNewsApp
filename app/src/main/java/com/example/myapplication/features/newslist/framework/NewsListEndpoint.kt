package com.example.myapplication.features.newslist.framework

import com.example.myapplication.core.behaviours.API_KEY
import com.example.simplecomposeapp.features.newslist.entities.response.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsListEndpoint {
    @GET("/v2/top-headlines")
    fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apikey")
        apiKey: String = API_KEY
    ): Single<NewsResponse>
}