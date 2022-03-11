package com.example.test_kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_kotlin.api.UsersItem
import com.example.test_kotlin.databinding.RecyclerviewDemo4Binding

class Demo4Adapter(myList: ArrayList<UsersItem>) :
    RecyclerView.Adapter<Demo4Adapter.Demo4ItemViewHolder>() {
    private val tempList = myList

    inner class Demo4ItemViewHolder(private val binding: RecyclerviewDemo4Binding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(position: Int) {
            val apiDataShow = tempList[position]
            binding.userData = apiDataShow
            if (binding.imgDropUpAddressRecyclerviewDemo4.visibility == View.GONE) {
                binding.imgDropDownAddressRecyclerviewDemo4.setOnClickListener(this)
            }
            binding.imgDropUpAddressRecyclerviewDemo4.setOnClickListener(this)
            if (binding.imgDropDownGeoRecyclerviewDemo4.visibility == View.GONE) {
                binding.imgDropGeoUpRecyclerviewDemo4.setOnClickListener(this)
            }
            binding.imgDropDownGeoRecyclerviewDemo4.setOnClickListener(this)
            binding.imgDropGeoUpRecyclerviewDemo4.setOnClickListener(this)
            binding.imgDropUpCompanyRecyclerviewDemo4.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            when (p0?.id) {
                binding.imgDropDownAddressRecyclerviewDemo4.id -> {
                    if (binding.layoutAddressExpandableRecyclerviewDemo4.visibility == View.GONE) {
                        binding.imgDropDownAddressRecyclerviewDemo4.visibility = View.GONE
                        binding.imgDropUpAddressRecyclerviewDemo4.visibility = View.VISIBLE
                        binding.layoutAddressExpandableRecyclerviewDemo4.visibility = View.VISIBLE
                        if (binding.layoutGeoExpandableRecyclerviewDemo4.visibility == View.VISIBLE) {
                            binding.layoutGeoExpandableRecyclerviewDemo4.visibility = View.GONE
                            binding.imgDropDownGeoRecyclerviewDemo4.visibility = View.VISIBLE
                            binding.imgDropGeoUpRecyclerviewDemo4.visibility = View.GONE
                        }
                    } else {
                        binding.imgDropDownAddressRecyclerviewDemo4.visibility = View.VISIBLE
                        binding.imgDropUpAddressRecyclerviewDemo4.visibility = View.GONE
                        binding.layoutAddressExpandableRecyclerviewDemo4.visibility = View.GONE
                    }
                }
                binding.imgDropUpAddressRecyclerviewDemo4.id -> {
                    binding.layoutAddressExpandableRecyclerviewDemo4.visibility = View.GONE
                    binding.imgDropUpAddressRecyclerviewDemo4.visibility = View.GONE
                    binding.imgDropDownAddressRecyclerviewDemo4.visibility = View.VISIBLE
                }
                binding.imgDropDownGeoRecyclerviewDemo4.id -> {
                    if (binding.layoutGeoExpandableRecyclerviewDemo4.visibility == View.GONE) {
                        binding.imgDropDownGeoRecyclerviewDemo4.visibility = View.GONE
                        binding.imgDropGeoUpRecyclerviewDemo4.visibility = View.VISIBLE
                        binding.layoutGeoExpandableRecyclerviewDemo4.visibility = View.VISIBLE
                    } else {
                        binding.imgDropDownGeoRecyclerviewDemo4.visibility = View.VISIBLE
                        binding.imgDropGeoUpRecyclerviewDemo4.visibility = View.GONE
                        binding.layoutGeoExpandableRecyclerviewDemo4.visibility = View.GONE
                    }
                }
                binding.imgDropGeoUpRecyclerviewDemo4.id -> {
                    binding.layoutGeoExpandableRecyclerviewDemo4.visibility = View.GONE
                    binding.imgDropGeoUpRecyclerviewDemo4.visibility = View.GONE
                    binding.imgDropDownGeoRecyclerviewDemo4.visibility = View.VISIBLE

                }
                binding.imgDropUpCompanyRecyclerviewDemo4.id -> {
                    if (binding.layoutCompanyExpandableRecyclerviewDemo4.visibility == View.GONE) {
                        binding.imgDropDownCompanyRecyclerviewDemo4.visibility = View.GONE
                        binding.imgDropUpCompanyRecyclerviewDemo4.visibility = View.VISIBLE
                        binding.layoutCompanyExpandableRecyclerviewDemo4.visibility = View.VISIBLE
                    } else {
                        binding.imgDropDownCompanyRecyclerviewDemo4.visibility = View.VISIBLE
                        binding.imgDropUpCompanyRecyclerviewDemo4.visibility = View.GONE
                        binding.layoutCompanyExpandableRecyclerviewDemo4.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Demo4ItemViewHolder {
        return Demo4ItemViewHolder(
            RecyclerviewDemo4Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Demo4ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return tempList.size
    }
}