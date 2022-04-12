package com.codebros.eripple.screen.account.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    private val _loginState = MutableLiveData<Int>()
    //private val repository : DefaultRepository = DefaultRepository()

    val loginState : LiveData<Int> = _loginState

    fun postLoginState(id : String, psw: String) : Job = viewModelScope.launch {

        val response = repository.loginAccount(id, psw)

        if (response.isSuccessful) {
            val result = response.body()
            result?.let {

                _loginState.postValue(it)

            } ?: kotlin.run {
                _loginState.postValue(-1)
            }


        } else {
            _loginState.postValue(-1)
        }

    }

}