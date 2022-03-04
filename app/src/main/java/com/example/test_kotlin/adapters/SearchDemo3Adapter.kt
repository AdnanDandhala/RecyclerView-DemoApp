package com.example.test_kotlin.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.test_kotlin.databinding.RecyclerviewLayoutSearchDemo3Binding
import com.example.test_kotlin.room.Users

@Suppress("UNCHECKED_CAST")
class SearchDemo3Adapter(var list: ArrayList<Users>) :
    RecyclerView.Adapter<SearchDemo3Adapter.SearchRecyclerViewHolder>(), Filterable {

    var tempList: ArrayList<Users> = list

    inner class SearchRecyclerViewHolder(val binding: RecyclerviewLayoutSearchDemo3Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val user = tempList[position]
            binding.postUsers = user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecyclerViewHolder {
        return SearchRecyclerViewHolder(
            RecyclerviewLayoutSearchDemo3Binding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchRecyclerViewHolder, position: Int) {
        holder.bind(position)
    }

    fun getUsersAtPosition(position: Int): Users {
        return tempList[position]
    }

    override fun getItemCount(): Int {
        return tempList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                tempList = if (charString.isEmpty()) {
                    list
                } else {
                    val filteredList = ArrayList<Users>()
                    list.filter {
                        it.UserName.lowercase()
                            .contains(charString.lowercase()) or it.EmailAddress.lowercase()
                            .contains(charString.lowercase()) or it.Address.lowercase()
                            .contains(charString.lowercase())
                    }.forEach { filteredList.add(it) }
                    filteredList
                }
                return FilterResults().apply { values = tempList }
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                tempList = results?.values as ArrayList<Users>
                notifyDataSetChanged()
            }
        }
    }
}