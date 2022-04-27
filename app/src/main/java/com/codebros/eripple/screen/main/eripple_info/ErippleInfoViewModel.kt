package com.codebros.eripple.screen.main.eripple_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.eripple.Eripple
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class ErippleInfoViewModel : BaseViewModel() {

    private val _erippleListLiveData = MutableLiveData<List<Eripple>?>()
    val erippleList: LiveData<List<Eripple>?> = _erippleListLiveData

    private val _erippleAddedBookMarkState = MutableLiveData<Eripple?>()
    val erippleAddedBookmarkState: LiveData<Eripple?> = _erippleAddedBookMarkState

    private val _erippleRemovedState = MutableLiveData<Boolean>()
    val erippleRemovedState : LiveData<Boolean> = _erippleRemovedState


    fun getErippleList(account_idx: Int) = viewModelScope.launch {

        val response = repository.getAllEripple(account_idx)

        if (response.isSuccessful) {

            val result = response.body()
            _erippleListLiveData.value = result?.map {

                Eripple(
                    uid = it.hashCode().toLong(),
                    type = CellType.ERIPPLE,
                    eripple_idx = it.eripple_idx,
                    eripple_name = it.eripple_name,
                    eripple_longitude = it.eripple_longitude,
                    eripple_latitude = it.eripple_latitude,
                    eripple_address = it.eripple_address,
                    eripple_address_detail = it.eripple_address_detail,
                    eripple_thumbnail = it.eripple_thumbnail,
                    eripple_status = it.eripple_status,
                    eripple_createtime = it.eripple_createtime,
                    eripple_updatetime = it.eripple_updatetime,
                    bookmark_idx = it.bookmark_idx
                )

            } ?: kotlin.run {
                null
            }

        } else {
            _erippleListLiveData.value = null
        }
    }

    fun addBookMark(account_idx: Int, eripple_idx: Int) = viewModelScope.launch {
        val response = repository.addBookMark(account_idx, eripple_idx)

        if (response.isSuccessful) {

            val result = response.body()
            result?.let { entity ->
                _erippleAddedBookMarkState.value = Eripple(
                    uid = entity.hashCode().toLong(),
                    type = CellType.ERIPPLE,
                    eripple_idx = entity.eripple_idx,
                    eripple_name = entity.eripple_name,
                    eripple_longitude = entity.eripple_longitude,
                    eripple_latitude = entity.eripple_latitude,
                    eripple_address = entity.eripple_address,
                    eripple_address_detail = entity.eripple_address_detail,
                    eripple_thumbnail = entity.eripple_thumbnail,
                    eripple_status = entity.eripple_status,
                    eripple_createtime = entity.eripple_createtime,
                    eripple_updatetime = entity.eripple_updatetime,
                    bookmark_idx = entity.bookmark_idx
                )


            } ?: kotlin.run {
                _erippleAddedBookMarkState.value = null
            }


        } else {
            _erippleAddedBookMarkState.value = null
        }


    }

    fun removeBookMark(bookmark_idx: Int) = viewModelScope.launch {
        val response = repository.removeBookMark(bookmark_idx)

        if (response.isSuccessful) {
            val result = response.body()
            result?.let {
                if (it > 0) {
                    _erippleRemovedState.postValue(true)
                } else {
                    _erippleRemovedState.postValue(false)
                }
            } ?: kotlin.run {
                _erippleRemovedState.postValue(false)
            }

        } else {
            _erippleRemovedState.postValue(false)
        }
    }

}