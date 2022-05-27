package com.codebros.eripple.screen.account.join

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.model.bank.Bank
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class JoinViewModel : BaseViewModel() {

    private val _joinState = MutableLiveData<Int?>()

    val joinState: LiveData<Int?> = _joinState

    fun postJoinState(
        name: String,
        phone: String,
        password: String,
        email: String,
        bankAccount: String,
        bank_idx: Int
    ): Job = viewModelScope.launch(exceptionhandler) {

        val response = repository.joinAccount(name, phone, password, email, bankAccount, bank_idx)

        if (response.isSuccessful) {
            val result = response.body()
            _joinState.postValue(result)

        } else {
            _joinState.postValue(-2)
        }
    }

}