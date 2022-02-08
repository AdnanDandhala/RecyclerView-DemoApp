package com.example.test_kotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_kotlin.databinding.RecyclerviewDataShowBinding


class DataShowAdapter(private val list: ArrayList<String>) :
    RecyclerView.Adapter<DataShowAdapter.DataShowViewHolder>() {

    inner class DataShowViewHolder(private val binding: RecyclerviewDataShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.tvRecyclerviewData.text = list[position]
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
        return list.size
    }
}