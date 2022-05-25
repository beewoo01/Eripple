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

    private val _applyExchangePointLiveData = MutableLiveData<Int?>()
    val applyExchangePointLiveData: LiveData<Int?> = _applyExchangePointLiveData

    fun getPointSituation(account_idx: Int) = viewModelScope.launch(exceptionhandler) {

        val response = repository.getPointSituation(account_idx)

        if (response.isSuccessful) {

            val result = response.body()
            _applyExchangeLiveData.value = result

        } else {

            _applyExchangeLiveData.value = null
        }

    }

    fun applyExchangePoint(account_idx: Int, applier_point: Int, setting_point: Int) =
        viewModelScope.launch {

            val response = repository.applyExchangePoint(account_idx, applier_point, setting_point)

            if (response.isSuccessful) {

                val result = response.body()
                _applyExchangePointLiveData.value = result

            } else {
                _applyExchangePointLiveData.value = null
            }

        }

}