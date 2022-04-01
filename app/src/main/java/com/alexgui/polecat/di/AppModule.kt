package com.alexgui.polecat.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.alexgui.polecat.ApiEndPoint
import com.alexgui.polecat.model.local.AppDatabase
import com.alexgui.polecat.model.local.CatFeedDao
import com.alexgui.polecat.model.remote.CatFactService
import com.alexgui.polecat.model.remote.CatFeedRemoteDataSource
import com.alexgui.polecat.model.remote.CatImageService
import com.alexgui.polecat.model.repository.CatFeedRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        //val cache = Cache(context.cacheDir!!, 5 * 1024 * 1024)
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Log.d("debug", it) }).apply { level = HttpLoggingInterceptor.Level.BODY }
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ApiEndPoint.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .build()
    }

    @Provides
    fun providesCatImageService(retrofit: Retrofit): CatImageService = retrofit.create(CatImageService::class.java)

    @Provides
    fun providesCatFactService(retrofit: Retrofit): CatFactService = retrofit.create(CatFactService::class.java)

    @Singleton
    @Provides
    fun provideCatFeedRemoteDataSource(catImageService: CatImageService, catFactService: CatFactService) = CatFeedRemoteDataSource(catImageService,catFactService  )

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room
            .databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "catfeed")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCatFeedDao(db: AppDatabase) = db.catFeedDao()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: CatFeedRemoteDataSource,
                          localDataSource: CatFeedDao) =
        CatFeedRepository(remoteDataSource, localDataSource)
}