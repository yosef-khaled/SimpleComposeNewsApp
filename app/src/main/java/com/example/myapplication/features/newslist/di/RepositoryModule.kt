package com.example.myapplication.features.newslist.di

import com.example.myapplication.core.RxRetrofitClient
import com.example.myapplication.features.newslist.domin.datasource.remote.NewsListRemoteDataSource
import com.example.myapplication.features.newslist.framework.NewsListEndpoint
import com.example.myapplication.features.newslist.framework.NewsListRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsDataSource(): NewsListRemoteDataSource {
        return NewsListRemoteDataSourceImpl(
            endpoint = RxRetrofitClient.clientInstant.create(
                NewsListEndpoint::class.java
            )
        )
    }
}

