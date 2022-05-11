package com.codebros.eripple.screen.main.my_point.appliyexchage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class ApplyExchangeViewModel : BaseViewModel() {

    private val _applyExchangeLiveData = MutableLiveData<HashMap<String, Int>?>()
    val applyExchangeLiveData: LiveData<HashMap<String, Int>?> = _applyExchangeLiveData

    fun getPointSituation(account_idx: Int) = viewModelScope.launch(exceptionhandler) {

        val response = repository.getPointSituation(account_idx)

        if (response.isSuccessful) {

            val result = response.body()
            _applyExchangeLiveData.value = result

        } else {

            _applyExchangeLiveData.value = null
        }

    }

}