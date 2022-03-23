package com.example.test_kotlin.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.test_kotlin.api.UsersItem
import com.example.test_kotlin.databinding.RecyclerviewDemo4Binding
import com.example.test_kotlin.fragments.FragmentDemo4Directions

class Demo4Adapter(myList: ArrayList<UsersItem>, private val navController: NavController) :
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
            binding.imgDropDownCompanyRecyclerviewDemo4.setOnClickListener(this)
            binding.tvGeoRecyclerviewDemo4.setOnClickListener(this)
            binding.tvWebsiteRecyclerviewDemo4.setOnClickListener(this)
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
                        binding.imgDropDownCompanyRecyclerviewDemo4.visibility = View.VISIBLE
                        binding.imgDropUpCompanyRecyclerviewDemo4.visibility = View.GONE
                        binding.layoutCompanyExpandableRecyclerviewDemo4.visibility = View.VISIBLE
                    } else {
                        binding.imgDropDownCompanyRecyclerviewDemo4.visibility = View.VISIBLE
                        binding.imgDropUpCompanyRecyclerviewDemo4.visibility = View.GONE
                        binding.layoutCompanyExpandableRecyclerviewDemo4.visibility = View.VISIBLE
                    }
                }
                binding.imgDropDownCompanyRecyclerviewDemo4.id -> {
                    binding.imgDropDownCompanyRecyclerviewDemo4.visibility = View.GONE
                    binding.layoutCompanyExpandableRecyclerviewDemo4.visibility = View.GONE
                    binding.imgDropUpCompanyRecyclerviewDemo4.visibility = View.VISIBLE
                }
                binding.tvGeoRecyclerviewDemo4.id -> {
                    val textLatitude =
                        binding.tvLatitudeRecyclerviewDemo4.text.toString().toFloat()
                    val textLongitude =
                        binding.tvLongitudeRecyclerviewDemo4.text.toString().toFloat()
                    Log.i("MAP", textLatitude.toString())
                    Log.i("MAP", textLongitude.toString())
                    val action = FragmentDemo4Directions.actionFragmentDemo4ToMapsFragment(
                        latitude = textLatitude,
                        longitude = textLongitude
                    )
                    navController.navigate(action)
                }
                binding.tvWebsiteRecyclerviewDemo4.id -> {
                    val url = binding.tvWebsiteRecyclerviewDemo4.text.toString()
                    val action = FragmentDemo4Directions.actionFragmentDemo4ToWebViewFragment(url)
                    navController.navigate(action)
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
        holder.bind(holder.adapterPosition)
    }

    override fun getItemCount(): Int {
        return tempList.size
    }
}