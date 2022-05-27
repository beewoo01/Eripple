package com.codebros.eripple.screen.account.chagepsw

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.model.account.AccountInfo
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class ChangePasswordViewModel : BaseViewModel() {

    private val _accountState = MutableLiveData<Int?>()
    val accountState: LiveData<Int?> = _accountState

    private val _changePswState = MutableLiveData<Int?>()
    val changePswState: LiveData<Int?> = _changePswState

    fun getAccountInfoForChangePsw(phone: String, name: String) = viewModelScope.launch(exceptionhandler) {
        val response = repository.getAccountInfoForChangePsw(phone, name)

        if (response.isSuccessful) {
            val result = response.body()

            _accountState.value = result

        } else {
            _accountState.value = null
        }


    }

    fun changePsw(account_password : String, account_idx: Int) = viewModelScope.launch(exceptionhandler) {
        val response = repository.changePsw(account_password, account_idx)

        if (response.isSuccessful) {
            _changePswState.value = response.body()
        } else {
            _changePswState.value = null
        }

    }


}