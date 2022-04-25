package com.codebros.eripple.screen.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.data.entity.EventWithThumbnailEntity
import com.codebros.eripple.data.entity.SimpleErippleInfoWithBookmarkEntity
import com.codebros.eripple.data.repository.Repository
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val _myCurrentPoint = MutableLiveData<Int?>()
    val myCurrentPoint: LiveData<Int?> = _myCurrentPoint

    private val _myBookMarkEripple = MutableLiveData<List<SimpleErippleInfoWithBookmarkEntity>?>()
    val myBookMarkEripple: LiveData<List<SimpleErippleInfoWithBookmarkEntity>?> = _myBookMarkEripple

    private val _eventList = MutableLiveData<List<EventWithThumbnailEntity>?>()
    val eventList : LiveData<List<EventWithThumbnailEntity>?> = _eventList

    fun postMyCurrentPoint(
        account_idx: Int
    ): Job = viewModelScope.launch {

        val response = repository.getMyCurrentPoint(account_idx = account_idx)

        if (response.isSuccessful) {
            val result = response.body()
            _myCurrentPoint.postValue(result)

        } else {
            _myCurrentPoint.postValue(0)
        }
    }

    fun postMyBookMarkEripple(
        account_idx: Int
    ): Job = viewModelScope.launch {

        val response = repository.getErippleInBookmark(account_idx)

        if (response.isSuccessful) {
            val result = response.body()
            _myBookMarkEripple.postValue(result)

        } else {

            _myBookMarkEripple.postValue(null)
        }

    }

    fun postEvent(): Job = viewModelScope.launch {

        val response = repository.getEventForHomeFragment()

        if (response.isSuccessful) {

            val result = response.body()
            _eventList.postValue(result)

        } else {
            _eventList.postValue(null)
        }

    }

}