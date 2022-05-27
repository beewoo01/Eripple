package com.codebros.eripple.screen.main.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.model.account.AccountInfo
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class SettingViewModel : BaseViewModel() {
    private val _accountInfo = MutableLiveData<AccountInfo?>()
    val accountInfo: LiveData<AccountInfo?> = _accountInfo

    fun getAccountInfo(account_idx: Int) = viewModelScope.launch(exceptionhandler) {
        val response = repository.getAccountInfo(account_idx)

        if (response.isSuccessful) {
            val result = response.body()
            result?.let { entity ->
                _accountInfo.value = AccountInfo(
                    account_email = entity.account_email,
                    account_name = entity.account_name,
                    account_phone = entity.account_phone
                )

            }
        } else {
            _accountInfo.value = null
        }
    }
}