package com.codebros.eripple.screen.main.my_point.exchange

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.exchange.AccountExchangeHistory
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class ExchangeHistoryViewModel : BaseViewModel() {

    private val _exChangeHistoryLiveData = MutableLiveData<List<AccountExchangeHistory>?>()
    val exChangeHistoryLiveData : LiveData<List<AccountExchangeHistory>?> = _exChangeHistoryLiveData

    fun getExchangeHistory(account_idx : Int) = viewModelScope.launch {
        val response = repository.getExchangeHistory(account_idx)

        if (response.isSuccessful) {
            val result = response.body()

            _exChangeHistoryLiveData.value = result?.map {
                AccountExchangeHistory(
                    uid = it.hashCode().toLong(),
                    type = CellType.EXCHANGE_HISTORY_CELL,
                    exchange_history_idx = it.exchange_history_idx,
                    exchange_cancel_idx = it.exchange_cancel_idx,
                    exchange_history_applier_point = it.exchange_history_applier_point,
                    exchange_history_status = it.exchange_history_status,
                    exchange_cancel_couse = it.exchange_cancel_couse,
                    exchange_history_createtime = it.exchange_history_createtime
                )
            }

        } else {

            _exChangeHistoryLiveData.value = null

        }
    }
}