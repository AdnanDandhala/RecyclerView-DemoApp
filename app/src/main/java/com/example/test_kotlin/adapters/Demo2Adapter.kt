package com.example.test_kotlin.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_kotlin.databinding.RecyclerviewDemo2Binding
import com.example.test_kotlin.models.ModelDemo2

class Demo2Adapter(val list: ArrayList<ModelDemo2>, var calculateTotal: CalculateTotal) :
    RecyclerView.Adapter<Demo2Adapter.Demo2ViewHolder>() {

    interface CalculateTotal {
        fun setTotal(total: Int)
    }

    inner class Demo2ViewHolder(private val binding: RecyclerviewDemo2Binding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(position: Int) {
            val modelDemo2 = list[position]
            binding.postDemo2 = modelDemo2
            binding.imgBtnAdd.setOnClickListener(this)
            binding.imgBtnSub.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            when (p0?.id) {
                binding.imgBtnAdd.id -> {
                    if (!TextUtils.isEmpty(binding.etNumber.text)) {
                        var total = 0
                        val num = binding.etNumber.text.toString().toInt()
                        val resultText: String = (num + 1).toString()
                        binding.etNumber.setText(resultText)
                        for (i in 0 until list.size) {
                            total += list[i].num.toInt()
                        }
                        calculateTotal.setTotal(total)
                    } else {
                        binding.etNumber.setText("0")
                    }
                }
                binding.imgBtnSub.id -> {
                    if (!TextUtils.isEmpty(binding.etNumber.text)) {
                        var total = 0
                        val num = binding.etNumber.text.toString().toInt()
                        val resultText: String
                        if (num > 0) {
                            resultText = (num - 1).toString()
                            binding.etNumber.setText(resultText)
                            for (i in 0 until list.size) {
                                total += list[i].num.toInt()
                            }
                        }
                    } else {
                        binding.etNumber.setText("0")
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Demo2ViewHolder {
        return Demo2ViewHolder(
            RecyclerviewDemo2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Demo2ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}