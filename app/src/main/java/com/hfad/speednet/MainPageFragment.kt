package com.hfad.speednet

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hfad.speednet.CheckConnection.Companion.isOnline
import com.hfad.speednet.databinding.FragmentMainPageBinding

class MainPageFragment : Fragment() {

    private var _binding: FragmentMainPageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onResume() {
        super.onResume()
        binding.buttonStart.setOnClickListener {
            checkSpeedConnection()
        }
    }

    fun checkSpeedConnection() {
        val checkConnection = context?.let { ctx -> isOnline(ctx) }
        Log.i("sasha", "onResume: $checkConnection")
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        if (checkConnection != "NoConnection" && cm != null) {
            val nc =
                cm.getNetworkCapabilities(cm.activeNetwork)
            val downSpeed = nc?.linkDownstreamBandwidthKbps?.toDouble()?.div(1000)
            val upSpeed = nc?.linkUpstreamBandwidthKbps?.toDouble()?.div(1000)
            binding.downloadInfo.text = downSpeed.toString()
            binding.uploadInfo.text = upSpeed.toString()
        } else {
            Toast.makeText(context, "no connection, sorry", Toast.LENGTH_LONG).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}