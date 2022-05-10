package com.codebros.eripple.screen.main.setting.account.bank

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class BankEditViewModel : BaseViewModel() {
    private val _bankAccountNumberLiveData = MutableLiveData<String?>()
    val bankAccountNumberLiveData: LiveData<String?> = _bankAccountNumberLiveData

    private val _registerAccountBank = MutableLiveData<Int?>()
    val registerAccountBank: LiveData<Int?> = _registerAccountBank

    fun getAccountBank(account_idx: Int) = viewModelScope.launch {
        val response = repository.getAccountBank(account_idx)
        if (response.isSuccessful) {
            _bankAccountNumberLiveData.value = response.body()
        } else {
            _bankAccountNumberLiveData.value = null
        }

    }

    fun registerAccountBank(account_idx: Int, bank_idx: Int, bank_account_number: String) =
        viewModelScope.launch {
            val response =
                repository.registerAccountBank(account_idx, bank_idx, bank_account_number)

            if (response.isSuccessful) {
                val result = response.body()
                _registerAccountBank.value = result

            } else {
                _registerAccountBank.value = null

            }
        }

}