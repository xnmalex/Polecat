package com.alexgui.polecat

import android.util.Log
import com.alexgui.polecat.model.local.CatFeedDao
import com.alexgui.polecat.model.repository.CatFeedRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepositoryTest {

    @Mock
    private lateinit var catFeedRepository: CatFeedRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

    }



    @Test
    fun check_api_result() {
        runBlocking {

            val result = catFeedRepository.getCatFeed(

            )
            //Log.v("debug", result)
            //Assert.assertEquals(result, Result.checUserDetailsStored)
        }

    }

   
}