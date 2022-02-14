package com.example.test_kotlin.adapters

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_kotlin.databinding.RecyclerviewDemo2Binding
import com.example.test_kotlin.models.ModelDemo2

class Demo2Adapter(list: ArrayList<ModelDemo2>, var calculateTotal: CalculateTotal) :
    RecyclerView.Adapter<Demo2Adapter.Demo2ViewHolder>() {
    var finalList = list

    interface CalculateTotal {
        fun setTotal(total: Int)
    }

    inner class Demo2ViewHolder(private val binding: RecyclerviewDemo2Binding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(position: Int) {
            val modelDemo2 = finalList[position]
            binding.postDemo2 = modelDemo2
            binding.imgBtnAdd.setOnClickListener(this)
            binding.imgBtnSub.setOnClickListener(this)
            binding.etNumber.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    var total = 0
                    for (i in 0 until finalList.size) {
                        total += finalList[i].num.toInt()
                    }
                    calculateTotal.setTotal(total)
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })
        }

        override fun onClick(p0: View?) {
            when (p0?.id) {
                binding.imgBtnAdd.id -> {
                    if (!TextUtils.isEmpty(binding.etNumber.text)) {
                        val num = binding.etNumber.text.toString().toInt()
                        val resultText: String = (num + 1).toString()
                        binding.etNumber.setText(resultText)
                    } else {
                        binding.etNumber.setText("0")
                    }
                }
                binding.imgBtnSub.id -> {
                    if (!TextUtils.isEmpty(binding.etNumber.text)) {
                        val num = binding.etNumber.text.toString().toInt()
                        val resultText: String
                        if (num > 0) {
                            resultText = (num - 1).toString()
                            binding.etNumber.setText(resultText)
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
        return finalList.size
    }
}