package com.codebros.eripple.screen.main.setting.notice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.notice.Notice
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class NoticeViewModel : BaseViewModel() {

    private val _noticeListLiveData = MutableLiveData<List<Notice>?>()
    val noticeListLiveData: LiveData<List<Notice>?> = _noticeListLiveData

    fun getNotice() = viewModelScope.launch(exceptionhandler) {

        val response = repository.getNotice()
        if (response.isSuccessful) {

            val result = response.body()
            _noticeListLiveData.value = result?.map { noticeEntity ->

                Notice(
                    uid = noticeEntity.hashCode().toLong(),
                    type = CellType.NOTICE_CELL,
                    notice_idx = noticeEntity.notice_idx,
                    notice_title = noticeEntity.notice_title,
                    notice_contents = noticeEntity.notice_contents
                )
            }

        } else {
            _noticeListLiveData.value = null
        }

    }

    //getNotice

}