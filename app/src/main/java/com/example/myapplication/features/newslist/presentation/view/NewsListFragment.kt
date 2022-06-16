package com.example.myapplication.features.newslist.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.core.behaviours.NEWS_KEY
import com.example.myapplication.features.newslist.entities.ui.NewsUIModel
import com.example.myapplication.features.newslist.presentation.components.HorizontalDottedProgressBar
import com.example.myapplication.features.newslist.presentation.components.LoadingRecipeListShimmer
import com.example.myapplication.features.newslist.presentation.components.NewsList
import com.example.myapplication.features.newslist.presentation.viewmodel.NewsListScreenState
import com.example.myapplication.features.newslist.presentation.viewmodel.NewsListViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private val viewModel: NewsListViewModel by viewModels()
    private lateinit var composeView: ComposeView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        composeView = ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        }
        return composeView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.screenStates.observe(viewLifecycleOwner) {
            onScreenStateChange(it)
        }
        viewModel.getAllNews()
    }

    private fun onScreenStateChange(screenState: NewsListScreenState) {
        when (screenState) {
            is NewsListScreenState.Error -> handleError(screenState.message)
            is NewsListScreenState.Loading -> handleLoading()
            is NewsListScreenState.Success -> handleSuccess(screenState.newsList)
            is NewsListScreenState.NavigateToNewsDetails -> handleNavigateToNewsDetails(screenState.news)
        }
    }

    private fun handleNavigateToNewsDetails(news: NewsUIModel) {
        val bundle = Bundle()
        bundle.putSerializable(NEWS_KEY, news)
        findNavController().navigate(R.id.viewNewsDetails, bundle)
    }

    private fun handleSuccess(data: List<NewsUIModel>) {
        composeView.setContent {
            NewsList(
                newsList = data,
                onNavigateToRecipeDetailScreen = viewModel::onNewsSelected
            )
        }
    }


    private fun handleLoading() {
        composeView.setContent {
            ShowLoader()
        }
    }

    @Composable
    private fun ShowLoader(){
        HorizontalDottedProgressBar()
        LoadingRecipeListShimmer(imageHeight = 250.dp)
    }

    private fun handleError(errorMessage: String) {
        composeView.setContent {
            ShowLoader()
            Box {
                Snackbar(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    content = {
                        Text(
                            text = errorMessage,
                            color = Color.White
                        )
                    },
                    backgroundColor = Color.Red
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyApplicationTheme {
        }
    }

}