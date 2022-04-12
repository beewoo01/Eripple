package com.codebros.eripple.screen.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.data.repository.Repository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    protected var stateBundle : Bundle? = null

    val repository : Repository = Repository()

    open fun fetchData() : Job = viewModelScope.launch {  }

    open fun storeState(stateBundle: Bundle) {
        this.stateBundle = stateBundle
    }
}