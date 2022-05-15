package com.codebros.eripple.screen.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.event.SimpleErippleInfoWithBookmark
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BookMarkViewModel : BaseViewModel() {

    private val _myBookMarkEripple = MutableLiveData<List<SimpleErippleInfoWithBookmark>?>()
    val myBookMarkEripple: LiveData<List<SimpleErippleInfoWithBookmark>?> = _myBookMarkEripple

    fun postMyBookMarkEripple(
        account_idx: Int
    ): Job = viewModelScope.launch(exceptionhandler) {

        val response = repository.getErippleInBookmark(account_idx)

        if (response.isSuccessful) {
            val result = response.body()

            _myBookMarkEripple.value = result?.map { entity ->

                SimpleErippleInfoWithBookmark(
                    uid = entity.hashCode().toLong(),
                    type = CellType.BOOKMARK_ACTIVITY_CELL,
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
}