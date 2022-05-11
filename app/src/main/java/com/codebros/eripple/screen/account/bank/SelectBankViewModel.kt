package com.codebros.eripple.screen.account.bank

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.bank.Bank
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class SelectBankViewModel : BaseViewModel() {

    private val _bankListLiveData = MutableLiveData<List<Bank>?>()
    val bankListLiveData: LiveData<List<Bank>?> = _bankListLiveData

    fun getBankList() = viewModelScope.launch(exceptionhandler) {
        val response = repository.getBankList()

        if (response.isSuccessful) {
            val result = response.body()
            _bankListLiveData.value = result?.map { bankEntity ->

                Bank(
                    uid = bankEntity.hashCode().toLong(),
                    type = CellType.BANK,
                    bank_idx = bankEntity.bank_idx,
                    bank_name = bankEntity.bank_name,
                    bank_img = bankEntity.bank_img
                )
            }

        } else {
            _bankListLiveData.value = null

        }

    }

}