package com.hfad.speednet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


class CheckConnection {
    companion object {
        fun isOnline(context: Context): String {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            val capabilities =
                connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {

                        return "Cellular"
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return "Wifi"
                    }
                    /* capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                         result(true, ConnectionType.Wifi)
                     }*/
                }
            }
            return "NoConnection"
        }
    }
}