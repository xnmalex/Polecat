package com.alexgui.polecat.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexgui.polecat.model.data.CatFeed
import com.alexgui.polecat.model.remote.Resource
import com.alexgui.polecat.model.repository.CatFeedRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import androidx.hilt.lifecycle.ViewModelInject

@ExperimentalCoroutinesApi
class CatFeedViewModel @ViewModelInject constructor(
   private val catFeedRepository: CatFeedRepository
): ViewModel(){

    private val _catFeed: MutableLiveData<Resource<List<CatFeed>>> = MutableLiveData()
    val catFeed: LiveData<Resource<List<CatFeed>>>
        get() = _catFeed


    fun getFeed(){
        viewModelScope.launch {
            catFeedRepository.getCatFeed().onEach { dataState ->
                _catFeed.value = dataState
            }
                .launchIn(viewModelScope)

        }
    }

    fun getDatabaseFeed(){
        viewModelScope.launch {
            catFeedRepository.getDatabaseFeed().onEach { dataState ->
                _catFeed.value = dataState
            }
                .launchIn(viewModelScope)

        }
    }

    suspend fun deleteCatFeed(catFeed: CatFeed){
        catFeedRepository.deleteCatFeed(catFeed)
        getDatabaseFeed()
    }
}