package com.codebros.eripple.screen.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel() {

    private val _loginState = MutableLiveData<Int>()

    val loginState: LiveData<Int> = _loginState

    fun autoLogin(id: String, psw: String, token: String?): Job =
        viewModelScope.launch(exceptionhandler) {

            val response = repository.loginAccount(id, psw, token)

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