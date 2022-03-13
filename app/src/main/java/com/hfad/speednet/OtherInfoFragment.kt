package com.hfad.speednet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hfad.speednet.databinding.FragmentOtherInfoBinding

class OtherInfoFragment : Fragment() {

    private var _binding: FragmentOtherInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOtherInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    fun initView() {
        binding.netPart.setOnClickListener {
            binding.picBase.visibility = View.GONE
            binding.changedElement.text = getString(R.string.net_part_text)
        }
        binding.downloadPart.setOnClickListener {
            binding.picBase.visibility = View.GONE
            binding.changedElement.text = getString(R.string.download_part_text)
        }
        binding.uploadPart.setOnClickListener {
            binding.picBase.visibility = View.GONE
            binding.changedElement.text = getString(R.string.upload_part_text)
        }
        binding.pingPart.setOnClickListener {
            binding.picBase.visibility = View.GONE
            binding.changedElement.text = getString(R.string.ping_part_text)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}