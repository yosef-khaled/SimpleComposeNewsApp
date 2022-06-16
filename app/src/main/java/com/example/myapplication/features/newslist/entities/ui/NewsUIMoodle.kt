package com.example.myapplication.features.newslist.entities.ui

import java.io.Serializable

data class NewsUIModel(
    val id:Int,
    val title:String,
    val description : String,
    val imageUrl:String
) : Serializable