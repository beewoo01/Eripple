package com.codebros.eripple.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.data.repository.DefaultRepository
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    private val _loginState = MutableLiveData<Int>()
    //private val repository : DefaultRepository = DefaultRepository()

    val loginState : LiveData<Int> = _loginState

    fun postLoginState() : Job = viewModelScope.launch {



    }

}