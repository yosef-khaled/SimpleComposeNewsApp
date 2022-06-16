package com.example.myapplication.features.newslist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.features.newslist.domin.usecase.NewsUseCase
import com.example.myapplication.features.newslist.entities.ui.NewsUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class NewsListViewModel
@Inject
constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {
    val screenStates by lazy { MutableLiveData<NewsListScreenState>() }
    private lateinit var newsList: List<NewsUIModel>
    private val compositeDisposable = CompositeDisposable()

    fun getAllNews(
        subscribeOnSchedulers: Scheduler = Schedulers.io(),
        observeOnSchedulers: Scheduler = AndroidSchedulers.mainThread()
    ) {
        compositeDisposable.add(newsUseCase.getNewsList()
            .subscribeOn(subscribeOnSchedulers)
            .observeOn(observeOnSchedulers)
            .doOnSubscribe { screenStates.postValue(NewsListScreenState.Loading) }
            .subscribe(
                {
                    newsList = it
                    screenStates.postValue(NewsListScreenState.Success(newsList))
                },
                { screenStates.postValue(NewsListScreenState.Error(it.message ?: "")) })
        )
    }

    fun onNewsSelected(newsId: Int) {
        val selectedNews = newsList.find { it.id == newsId }
        selectedNews?.let {
            screenStates.value = (NewsListScreenState.NavigateToNewsDetails(it))
            screenStates.value = NewsListScreenState.Loading
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

sealed class NewsListScreenState {

    data class Success(val newsList: List<NewsUIModel>) : NewsListScreenState()
    data class Error(val message: String) : NewsListScreenState()
    object Loading : NewsListScreenState()
    data class NavigateToNewsDetails(val news: NewsUIModel) : NewsListScreenState()
}
