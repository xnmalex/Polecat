package com.alexgui.polecat.model.remote

import javax.inject.Inject

class CatFeedRemoteDataSource  @Inject constructor(
    private val catImageService: CatImageService,
    private val catFactService: CatFactService
): BaseDataSource(){
    suspend fun getRandomCatFacts() = getResult {catFactService.getRandomCatFacts("https://cat-fact.herokuapp.com/facts/random")}

    suspend fun getRandomCatImage() = getResult {catImageService.getRandomCatImage()}
}