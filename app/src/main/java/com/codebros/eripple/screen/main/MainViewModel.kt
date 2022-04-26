package com.codebros.eripple.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codebros.eripple.screen.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    private var _fragmentStatus = MutableLiveData<Int>()
    val fragmentStatus: LiveData<Int>
        get() = _fragmentStatus

    init {
        _fragmentStatus.value = 1
    }

    fun updateFragmentStatus(num : Int) {
        _fragmentStatus.value = num
    }


}