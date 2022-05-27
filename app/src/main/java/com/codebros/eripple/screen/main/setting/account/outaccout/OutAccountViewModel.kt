package com.codebros.eripple.screen.main.setting.account.outaccout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class OutAccountViewModel : BaseViewModel() {
    private val _outAccountState = MutableLiveData<Int?>()
    val outAccountState: LiveData<Int?> = _outAccountState

    fun outAccount(
        account_idx: Int,
        account_phone: String,
        account_password: String,
        account_name: String
    ) = viewModelScope.launch(exceptionhandler) {

        val response =
            repository.leaveAccount(account_idx, account_phone, account_password, account_name)

        if (response.isSuccessful) {
            val result = response.body()
            _outAccountState.value = result

        } else {
            _outAccountState.value = null

        }
    }
}