package com.example.test_kotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_kotlin.databinding.RecyclerviewDataShowReceiverBinding
import com.example.test_kotlin.databinding.RecyclerviewDataShowSenderBinding
import com.example.test_kotlin.models.ModelDataShow

//List Adapter And It's Usage
class DataShowAdapter(
    list: ArrayList<ModelDataShow>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataList = list

    companion object {
        const val THE_FIRST_VIEW = 1
    }

    inner class DataShowViewHolderSender(private val binding: RecyclerviewDataShowSenderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val modelDataShow = dataList[position]
            binding.postSender = modelDataShow
            binding.postSender = modelDataShow
        }
    }

    inner class DataShowViewHolderReceiver(private val binding: RecyclerviewDataShowReceiverBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val modelDataShow = dataList[position]
            binding.postReceiver = modelDataShow
            binding.postReceiver = modelDataShow
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            THE_FIRST_VIEW -> {
                return DataShowViewHolderSender(
                    RecyclerviewDataShowSenderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
            else -> {
                return DataShowViewHolderReceiver(
                    RecyclerviewDataShowReceiverBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (dataList[position].viewType) {
            THE_FIRST_VIEW -> {
                (holder as DataShowViewHolderSender).bind(position)
            }
            else -> {
                (holder as DataShowViewHolderReceiver).bind(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position].viewType
    }

}