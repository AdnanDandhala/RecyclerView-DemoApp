package com.example.test_kotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_kotlin.databinding.RecyclerviewDataShowBinding
import com.example.test_kotlin.models.ModelDataShow


class DataShowAdapter(
    list: ArrayList<ModelDataShow>
) : RecyclerView.Adapter<DataShowAdapter.DataShowViewHolder>() {

    var dataList = list

    inner class DataShowViewHolder(private val binding: RecyclerviewDataShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val modelDataShow = dataList[position]
            binding.imgReceiver.setImageResource(modelDataShow.img_id_receiver)
            binding.tvMessageReceiver.text = modelDataShow.message_receiver
            binding.imgSender.setImageResource(modelDataShow.img_id_sender)
            binding.tvMessageSender.text = modelDataShow.message_sender
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataShowViewHolder {
        return DataShowViewHolder(
            RecyclerviewDataShowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DataShowViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}