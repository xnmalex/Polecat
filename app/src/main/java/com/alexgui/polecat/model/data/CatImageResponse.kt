package com.alexgui.polecat.model.data

data class CatFactResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)