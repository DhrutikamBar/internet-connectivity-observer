package com.dhrutikambar.internetconnectivitymanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NetworkViewModel : ViewModel() {
    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected: LiveData<Boolean> get() = _isConnected


    fun updateNetworkStatus(isConnected: Boolean) {
        _isConnected.postValue(isConnected)

    }

}