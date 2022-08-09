package com.shanshan.eyepetizer.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BaseViewModel:ViewModel() {

    val  loadState by lazy {
        MutableLiveData<Lifecycle.State>()
    }

    override fun onCleared() {
        super.onCleared()
    }
}