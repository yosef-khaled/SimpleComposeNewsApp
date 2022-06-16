package com.example.myapplication.features.newsditels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.myapplication.core.behaviours.NEWS_KEY
import com.example.myapplication.features.newslist.entities.ui.NewsUIModel
import com.example.myapplication.features.newslist.presentation.components.NewsCard

class NewsDetailsFragment : Fragment() {
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
        composeView.setContent {
            NewsCard(news = (arguments?.getSerializable(NEWS_KEY) as NewsUIModel))
        }
    }

}