package com.shanshan.eyepetizer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private val arg: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return modelClass.getConstructor(Int::class.java).newInstance(arg)
    }
}