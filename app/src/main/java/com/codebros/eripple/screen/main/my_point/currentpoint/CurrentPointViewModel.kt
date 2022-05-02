package com.codebros.eripple.screen.main.my_point.currentpoint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class CurrentPointViewModel : BaseViewModel() {

    private val _myPointLiveData = MutableLiveData<HashMap<String, Int>?>()
    val myPointLiveData: LiveData<HashMap<String, Int>?> = _myPointLiveData

    fun getPointSituation(account_idx: Int) = viewModelScope.launch {
        val response = repository.getPointSituation(account_idx)

        if (response.isSuccessful) {

            val result = response.body()
            _myPointLiveData.value = result

        } else {

            _myPointLiveData.value = null
        }
    }

}