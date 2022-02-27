package com.hfad.speednet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.speednet.databinding.SpeedNetInfoItemBinding

class SpeedNetAdapter : RecyclerView.Adapter<SpeedNetAdapter.SpeedNetHolder>() {

    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeedNetHolder {
        return SpeedNetHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SpeedNetHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class SpeedNetHolder(val binding: SpeedNetInfoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): SpeedNetHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = SpeedNetInfoItemBinding.inflate(inflater, parent, false)
                return SpeedNetHolder(binding)
            }
        }

        fun bind(str: String) {
            binding.typeInfo.text = "LTE"
            binding.dateInfo.text = "26/02/2020 \n12:55"
            binding.downloadSpeedInfo.text = "42.88"
            binding.uploadSpeedInfo.text = "35.09"
            binding.pingInfo.text = "60"
        }
    }
}