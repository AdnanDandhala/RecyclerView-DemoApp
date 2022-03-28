package com.example.test_kotlin.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.test_kotlin.databinding.RecyclerviewDemo6Binding
import com.example.test_kotlin.models.ModelDemo6

class Demo6Adapter(val listData: ArrayList<ModelDemo6>) :
    RecyclerView.Adapter<Demo6Adapter.Demo6ViewHolder>(), Filterable {
    var tempList: ArrayList<ModelDemo6> = listData

    inner class Demo6ViewHolder(val binding: RecyclerviewDemo6Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val data = tempList[position]
            binding.fireStoreData = data
            Log.i("ListSize", tempList.size.toString())
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charString = p0?.toString() ?: ""
                tempList = if (charString.isEmpty()) {
                    listData
                } else {
                    val filteredList = ArrayList<ModelDemo6>()
                    listData.filter {
                        it.time!!.lowercase().contains(charString.lowercase())
                    }.forEach { filteredList.add((it)) }
                    filteredList
                }
                return FilterResults().apply { values = tempList }
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                tempList = p1?.values as ArrayList<ModelDemo6>
                notifyDataSetChanged()
            }

        }
    }
}