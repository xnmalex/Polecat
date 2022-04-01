package com.alexgui.polecat.model.repository

import com.alexgui.polecat.model.data.CatFeed
import com.alexgui.polecat.model.local.CatFeedDao
import com.alexgui.polecat.model.remote.CatFeedRemoteDataSource
import com.alexgui.polecat.model.remote.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatFeedRepository @Inject constructor(
    private val remoteDataSource: CatFeedRemoteDataSource,
    private val localDataSource: CatFeedDao

){
    suspend fun getCatFeed() : Flow<Resource<List<CatFeed>>> = flow{
        try{
            emit(Resource.loading())

            val catFacts = remoteDataSource.getRandomCatFacts();
            val catImage = remoteDataSource.getRandomCatImage();

            catImage.data?.let{
                localDataSource.insert(CatFeed(0, catImage.data?.url,catFacts.data?.text ))
            }

            emit(Resource.success(localDataSource.getCatFeed()))
        }catch (e: Exception){
            emit(Resource.error(e.localizedMessage))
        }
    }

    suspend fun getDatabaseFeed() : Flow<Resource<List<CatFeed>>> = flow{
        try{

            emit(Resource.success(localDataSource.getCatFeed()))
        }catch (e: Exception){
            emit(Resource.error(e.localizedMessage))
        }
    }

    suspend fun deleteCatFeed(catFeed: CatFeed){
        localDataSource.delete(catFeed)

    }

}