package com.hfad.speednet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hfad.speednet.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    var infosRoomDatabase: InfosRoomDatabase? = null
    var infosDao: InfosDao? = null

    private val binding get() = _binding!!
    val speedNetAdapter = SpeedNetAdapter()
    var listHistory: List<Infos?>? = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        infosRoomDatabase = context?.let { InfosRoomDatabase.getInMemoryDatabase(it) }
        infosDao = infosRoomDatabase?.getInfosDao()
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onResume() {
        super.onResume()
        listHistory = infosDao?.getAllInfos()
        listHistory?.let {
            speedNetAdapter.data = it
        }

        binding.recyclerView.adapter = speedNetAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}