package com.adrict99.bestfilms.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    val loading : MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>().apply { value = false } }

    val errorMessage: MutableLiveData<Map<Int, String>> by lazy { MutableLiveData<Map<Int, String>>() }

}