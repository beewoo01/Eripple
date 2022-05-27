package com.codebros.eripple.screen.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.data.entity.EventWithThumbnailEntity
import com.codebros.eripple.data.entity.SimpleErippleInfoWithBookmarkEntity
import com.codebros.eripple.data.repository.Repository
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.alarm.AlarmModel
import com.codebros.eripple.model.event.EventWithThumbnail
import com.codebros.eripple.model.event.SimpleErippleInfoWithBookmark
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val _myCurrentPoint = MutableLiveData<Int?>()
    val myCurrentPoint: LiveData<Int?> = _myCurrentPoint

    private val _myBookMarkEripple = MutableLiveData<List<SimpleErippleInfoWithBookmark>?>()
    val myBookMarkEripple: LiveData<List<SimpleErippleInfoWithBookmark>?> = _myBookMarkEripple

    private val _eventList = MutableLiveData<List<EventWithThumbnail>?>()
    val eventList: LiveData<List<EventWithThumbnail>?> = _eventList

    private val _alarmLivaData = MutableLiveData<List<AlarmModel>?>()
    val alarmList : LiveData<List<AlarmModel>?> = _alarmLivaData


    fun getMyCurrentPoint(account_idx: Int): Job = viewModelScope.launch {
        
    }

    override fun onCleared() {
        super.onCleared()
        Log.wtf("HomeViewModel", "onCleared")
    }

    fun postMyCurrentPoint(
        account_idx: Int
    ): Job = viewModelScope.launch(exceptionhandler) {
        Log.wtf("postMyCurrentPoint", "postMyCurrentPoint")

        val response = repository.getMyCurrentPoint(account_idx = account_idx)
        if (response.isSuccessful) {
            val result = response.body()
            Log.wtf("postMyCurrentPoint", "isSuccessful")
            _myCurrentPoint.postValue(result)

        } else {
            Log.wtf("postMyCurrentPoint", "isSuccessful false")
            _myCurrentPoint.postValue(0)
        }
    }

    fun postMyBookMarkEripple(
        account_idx: Int
    ): Job = viewModelScope.launch(exceptionhandler) {

        val response = repository.getSimpleErippleInBookmark(account_idx)

        if (response.isSuccessful) {
            val result = response.body()

            _myBookMarkEripple.value = result?.map { entity ->

                SimpleErippleInfoWithBookmark(
                    uid = entity.hashCode().toLong(),
                    CellType.BOOKMARK_CELL,
                    eripple_idx = entity.eripple_idx,
                    bookmark_idx = entity.bookmark_idx,
                    eripple_name = entity.eripple_name,
                    eripple_status = entity.eripple_status
                )
            }

        } else {
            _myBookMarkEripple.value = null
        }

    }

    fun postEvent(): Job = viewModelScope.launch(exceptionhandler) {

        val response = repository.getEventForHomeFragment()

        if (response.isSuccessful) {

            val result = response.body()
            _eventList.value = result?.map {
                EventWithThumbnail(
                    uid = it.hashCode().toLong(),
                    event_idx = it.event_idx,
                    event_title = it.event_title,
                    event_createTime = it.event_createtime,
                    event_updateTime = it.event_updatetime,
                    event_image_idx = it.event_image_idx,
                    event_image_url = it.event_image_url
                )
            }

        } else {
            _eventList.value = null
        }

    }

    fun getAlarmList(account_idx : Int) : Job = viewModelScope.launch {
        val response = repository.getAlarm(account_idx)

        if (response.isSuccessful) {

            val result = response.body()
            _alarmLivaData.value = result?.map {
                AlarmModel(
                    uid = it.hashCode().toLong(),
                    type = CellType.ALARM_CELL,
                    alarm_idx = it.alarm_idx,
                    account_account_idx = account_idx,
                    eripple_eripple_idx = it.eripple_eripple_idx,
                    alarm_content = it.alarm_content,
                    alarm_type = it.alarm_type,
                    alarm_createtime = it.alarm_createtime,
                    alarm_updatetime = it.alarm_updatetime,
                    eripple_name = it.eripple_name
                )
            }
        } else {
            _alarmLivaData.value = null
        }
    }

}