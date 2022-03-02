package com.example.test_kotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_kotlin.databinding.RecyclerviewLayoutSearchDemo3Binding
import com.example.test_kotlin.room.Users

class SearchDemo3Adapter(var list: List<Users>) :
    RecyclerView.Adapter<SearchDemo3Adapter.SearchRecyclerViewHolder>() {

    inner class SearchRecyclerViewHolder(val binding: RecyclerviewLayoutSearchDemo3Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val user = list[position]
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
        return list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}