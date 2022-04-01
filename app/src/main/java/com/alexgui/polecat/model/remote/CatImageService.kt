package com.alexgui.polecat.model.remote

import com.alexgui.polecat.model.data.CatImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatImageService {
    //https://cataas.com/cat/623f9e14af3c590018309547
    @GET("cat")
    suspend fun getRandomCatImage(@Query("json") json:String?="true") : Response<CatImageResponse>
}