package com.hfad.speednet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hfad.speednet.databinding.SpeedNetInfoItemBinding

class SpeedNetAdapter : RecyclerView.Adapter<SpeedNetAdapter.SpeedNetHolder>() {

    var data = listOf<Infos?>()
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

        fun bind(infos: Infos?) {
            Glide
                .with(binding.typeInfo)
                .load(infos?.typeInfo)
                .into(binding.typeInfo)

            binding.dateInfo.text = infos?.dateInfo
            binding.downloadSpeedInfo.text = infos?.download_speedInfo
            binding.uploadSpeedInfo.text = infos?.upload_speedInfo
            binding.pingInfo.text = infos?.pingInfo
        }
    }
}