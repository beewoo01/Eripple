package com.codebros.eripple.screen.main.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.event.EventWithThumbnail
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class EventViewModel : BaseViewModel() {

    private val _eventListLiveData = MutableLiveData<List<EventWithThumbnail>>()
    val eventListLiveData : LiveData<List<EventWithThumbnail>> = _eventListLiveData

    fun getEvent() = viewModelScope.launch {

        val response = repository.getEventForHomeFragment()

        if (response.isSuccessful) {
            val result = response.body()

            _eventListLiveData.value = result?.map { eventWithThumbnailEntity ->
                EventWithThumbnail(
                    uid = eventWithThumbnailEntity.hashCode().toLong(),
                    type = CellType.EVENT_CELL,
                    event_idx = eventWithThumbnailEntity.event_idx,
                    event_title = eventWithThumbnailEntity.event_title,
                    event_createTime = eventWithThumbnailEntity.event_createtime,
                    event_updateTime = eventWithThumbnailEntity.event_updatetime,
                    event_image_idx = eventWithThumbnailEntity.event_image_idx,
                    event_image_url = eventWithThumbnailEntity.event_image_url
                )
            }
        }

    }


}