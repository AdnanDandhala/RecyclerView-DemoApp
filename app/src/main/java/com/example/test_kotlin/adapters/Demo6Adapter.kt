package com.example.test_kotlin.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_kotlin.databinding.RecyclerviewDemo6Binding
import com.example.test_kotlin.models.FirestoreModel

class Demo6Adapter(listData: ArrayList<FirestoreModel>) :
    RecyclerView.Adapter<Demo6Adapter.Demo6ViewHolder>() {
    var tempList: ArrayList<FirestoreModel> = listData

    inner class Demo6ViewHolder(val binding: RecyclerviewDemo6Binding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(position: Int) {
            val data = tempList[position]
            binding.fireStoreData = data
            Log.i("TIME", tempList[position].firebaseModelUserItems.date.toString())
            Log.i("ListSize", tempList.size.toString())
            binding.imgDropdownArrowDemo6.setOnClickListener(this)
            binding.imgDropUpArrowDemo6.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            when (p0?.id) {
                binding.imgDropdownArrowDemo6.id -> {
                    binding.imgDropdownArrowDemo6.visibility = View.GONE
                    binding.imgDropUpArrowDemo6.visibility = View.VISIBLE
                    binding.tvDateFireStore.visibility = View.VISIBLE
                    binding.tvDayFireStoreDay.visibility = View.VISIBLE
                    binding.tvFirstFireStoreTittle.visibility = View.VISIBLE
                    binding.tvFirstFireStoreUserId.visibility = View.VISIBLE
                }
                binding.imgDropUpArrowDemo6.id -> {
                    binding.imgDropdownArrowDemo6.visibility = View.VISIBLE
                    binding.imgDropUpArrowDemo6.visibility = View.GONE
                    binding.tvDateFireStore.visibility = View.GONE
                    binding.tvDayFireStoreDay.visibility = View.GONE
                    binding.tvFirstFireStoreTittle.visibility = View.GONE
                    binding.tvFirstFireStoreUserId.visibility = View.GONE
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Demo6ViewHolder {
        return Demo6ViewHolder(
            RecyclerviewDemo6Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Demo6ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        Log.i("ListSize", tempList.size.toString())
        return tempList.size
    }

//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(p0: CharSequence?): FilterResults {
//                val charString = p0?.toString() ?: ""
//                tempList = if (charString.isEmpty()) {
//                    listData
//                } else {
//                    val filteredList = ArrayList<ModelDemo6>()
//                    listData.filter {
//                        it.date!!.lowercase().contains(charString.lowercase())
//                    }.forEach { filteredList.add((it)) }
//                    filteredList
//                }
//                return FilterResults().apply { values = tempList }
//            }
//
//            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
//                tempList = p1?.values as ArrayList<ModelDemo6>
//                notifyDataSetChanged()
//            }
//
//        }
//    }
}