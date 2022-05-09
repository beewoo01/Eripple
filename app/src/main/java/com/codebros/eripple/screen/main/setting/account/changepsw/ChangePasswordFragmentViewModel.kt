package com.codebros.eripple.screen.main.setting.account.changepsw

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class ChangePasswordFragmentViewModel: BaseViewModel() {

    private val _changePasswordLiveData = MutableLiveData<Int>()
    val changePasswordLiveData : LiveData<Int> = _changePasswordLiveData

    fun changePassword(account_idx : Int, password : String) = viewModelScope.launch {
        val response = repository.updatePassword(account_idx, password)

        if (response.isSuccessful) {
            val result = response.body()
            result?.let {
                _changePasswordLiveData.value = it
            }

        } else {
            _changePasswordLiveData.value = 0

        }

    }
}