package com.hfad.speednet

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hfad.speednet.CheckConnection.Companion.isOnline
import com.hfad.speednet.databinding.FragmentMainPageBinding
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainPageFragment : Fragment() {

    private var _binding: FragmentMainPageBinding? = null
    var appDataBase: InfosRoomDatabase? = null
    var infosDao: InfosDao? = null
    var speedNetDimension = Infos()
    var downSpeed: Double? = null
    var upSpeed: Double? = null
    var checkConnection: String? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        val root: View = binding.root
        appDataBase = context?.let { InfosRoomDatabase.getInMemoryDatabase(it) }
        infosDao = appDataBase?.getInfosDao()
        return root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onResume() {
        super.onResume()
        binding.buttonStart.setOnClickListener {
            checkSpeedConnection()
            pullDataBase()
        }
    }

    private fun pullDataBase() {
        speedNetDimension.dateInfo = getDateAndTime()
        speedNetDimension.pingInfo = ping().toString()
        speedNetDimension.typeInfo = if (checkConnection == "Wifi") "Wifi" else "Mob"
        speedNetDimension.download_speedInfo = downSpeed.toString()
        speedNetDimension.upload_speedInfo = upSpeed.toString()
        if (downSpeed != null && upSpeed != null) {
            infosDao?.insert(speedNetDimension)
        }
    }

    private fun getDateAndTime(): String {
        val current = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val time = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd \n HH:mm:ss")
            val formatted = time.format(formatter)
            formatted
        } else {
            val calendar = Calendar.getInstance()
            val date = SimpleDateFormat("yyyy-MM-dd \n hh:mm:ss")
            val t = date.format(calendar.time)
            t
        }
        return current
    }


    fun checkSpeedConnection() {
        checkConnection = context?.let { ctx -> isOnline(ctx) }

        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        if (checkConnection != "NoConnection" && cm != null) {
            val nc =
                cm.getNetworkCapabilities(cm.activeNetwork)
            downSpeed = nc?.linkDownstreamBandwidthKbps?.toDouble()?.div(1000)
            upSpeed = nc?.linkUpstreamBandwidthKbps?.toDouble()?.div(1000)
            binding.downloadInfo.text = (downSpeed ?: 0.0).toString()
            binding.uploadInfo.text = (upSpeed ?: 0.0).toString()
        } else {
            Toast.makeText(context, "no connection, sorry", Toast.LENGTH_LONG).show()
        }
    }

    private fun ping(): Int {
        var exitValue = 0
        try {
            val process = Runtime.getRuntime().exec(
                "/system/bin/ping -c 1 google.com"
            )
            exitValue = process.waitFor()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return exitValue
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}