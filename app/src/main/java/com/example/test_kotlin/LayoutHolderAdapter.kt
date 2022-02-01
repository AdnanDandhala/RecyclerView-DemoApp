package com.example.test_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.test_kotlin.databinding.RecyclerviewLayoutBottomBinding
import com.example.test_kotlin.databinding.RecyclerviewLayoutMiddleBinding
import com.example.test_kotlin.databinding.RecyclerviewLayoutTopBinding

class LayoutHolderAdapter(private val context: Context, var list: ArrayList<LayoutHolder>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val THE_FIRST_VIEW = 1
        const val THE_SECOND_VIEW = 2
        const val THE_THIRD_VIEW = 3
    }

    private inner class LayoutHold1(val binding: RecyclerviewLayoutTopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val layoutHolder = list[position]
            binding.postTop = layoutHolder
            binding.postTop = layoutHolder
            binding.postTop = layoutHolder
            binding.postTop = layoutHolder
        }

    }

    private inner class LayoutHold2(val binding: RecyclerviewLayoutMiddleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val layoutHolder = list[position]
            binding.postMiddle = layoutHolder
            binding.postMiddle = layoutHolder
        }
    }

    private inner class LayoutHold3(val binding: RecyclerviewLayoutBottomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val layoutHolder = list[position]
            binding.postBottom = layoutHolder
            binding.postBottom = layoutHolder
            binding.postBottom = layoutHolder
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            THE_FIRST_VIEW -> {
                return LayoutHold1(
                    RecyclerviewLayoutTopBinding.inflate(
                        LayoutInflater.from(context), parent, false
                    )
                )
            }
            THE_SECOND_VIEW -> {
                return LayoutHold2(
                    RecyclerviewLayoutMiddleBinding.inflate(
                        LayoutInflater.from(
                            context
                        ),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return LayoutHold3(
                    RecyclerviewLayoutBottomBinding.inflate(
                        LayoutInflater.from(context), parent, false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (list[position].viewType) {
            THE_FIRST_VIEW -> {
                (holder as LayoutHold1).bind(position)
            }
            THE_SECOND_VIEW -> {
                (holder as LayoutHold2).bind(position)
            }
            else -> {
                (holder as LayoutHold3).bind(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }
}