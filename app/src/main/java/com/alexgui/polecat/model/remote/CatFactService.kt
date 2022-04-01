package com.alexgui.polecat.model.remote

import com.alexgui.polecat.model.data.CatFactResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CatFactService {
    @GET
    suspend fun getRandomCatFacts(@Url url: String ) : Response<CatFactResponse>

}