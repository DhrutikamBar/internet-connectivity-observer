package com.dhrutikambar.internetconnectivitymanager

import android.app.Application
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.dhrutikambar.internetconnectivitymanager.utils.NetworkConnectivityReceiver
import com.dhrutikambar.internetconnectivitymanager.viewmodel.NetworkViewModel

class MainApplication : Application() {

    private lateinit var networkConnectivityReceiver: NetworkConnectivityReceiver
    private lateinit var viewModel: NetworkViewModel
    override fun onCreate() {
        super.onCreate()
        val viewModelStore = ViewModelStore()
        val viewModelProvider = ViewModelProvider(
            viewModelStore,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        )
        viewModel = viewModelProvider[NetworkViewModel::class.java]
        networkConnectivityReceiver = NetworkConnectivityReceiver {
            // Handle connectivity change
            // For example, update a LiveData or a StateFlow that your UI observes
            viewModel.updateNetworkStatus(it)

        }

        registerReceiver(
            networkConnectivityReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onTerminate() {
        super.onTerminate()
        unregisterReceiver(networkConnectivityReceiver)
    }


}