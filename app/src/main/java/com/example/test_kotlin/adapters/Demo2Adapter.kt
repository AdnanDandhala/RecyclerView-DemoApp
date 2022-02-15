package com.example.test_kotlin.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
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
            binding.etNumber.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                @SuppressLint("ResourceAsColor")
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (TextUtils.isEmpty(binding.etNumber.text)) {
                        binding.etNumber.setText("0")
                        binding.etNumber.setSelection(1)
                    } else {
                        var total = 0
                        list[position].num = p0.toString()
                        for (i in 0 until list.size) {
                            total += list[i].num.toInt()
                        }
                        checkNumber(list[position].num)
                        calculateTotal.setTotal(total)
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })
        }

        @SuppressLint("ResourceAsColor")
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

        fun checkNumber(num: String) {
            if (num == "100") {
                binding.itemLayout.setBackgroundColor(Color.RED)
            } else if (num == "200") {
                binding.itemLayout.setBackgroundColor(Color.GREEN)
            } else if (num == "300") {
                binding.itemLayout.setBackgroundColor(Color.BLUE)
            } else if (num == "400") {
                binding.itemLayout.setBackgroundColor(Color.YELLOW)
            } else if (num == "500") {
                binding.itemLayout.setBackgroundColor(Color.BLACK)
            } else if (num == "600") {
                binding.itemLayout.setBackgroundColor(Color.LTGRAY)
            } else if (num == "700") {
                binding.itemLayout.setBackgroundColor(Color.CYAN)
            } else {
                binding.itemLayout.setBackgroundColor(Color.WHITE)
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