package com.example.test_kotlin.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_kotlin.FirebaseHelper
import com.example.test_kotlin.databinding.RecyclerviewDemo5Binding

class Demo5Adapter(val listTime: ArrayList<FirebaseHelper>) :
    RecyclerView.Adapter<Demo5Adapter.Demo5ViewHolder>() {

    inner class Demo5ViewHolder(private val binding: RecyclerviewDemo5Binding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(position: Int) {
            val helper = listTime[position]
            binding.firebaseTime = helper
            binding.imgTimeDropDown.setOnClickListener(this)
            binding.imgTimeDropUp.setOnClickListener(this)
            Log.i("List", "The Size Is ${listTime.size}")
        }

        override fun onClick(p0: View?) {
            when (p0?.id) {
                binding.imgTimeDropDown.id -> {
                    binding.imgTimeDropDown.visibility = View.GONE
                    binding.imgTimeDropUp.visibility = View.VISIBLE
                    binding.layoutFirebaseExpandableRecyclerviewDemo5.visibility = View.VISIBLE

                }
                binding.imgTimeDropUp.id -> {
                    binding.imgTimeDropUp.visibility = View.GONE
                    binding.imgTimeDropDown.visibility = View.VISIBLE
                    binding.layoutFirebaseExpandableRecyclerviewDemo5.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Demo5ViewHolder {
        return Demo5ViewHolder(
            RecyclerviewDemo5Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Demo5ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        Log.i("List", "The Size Is ${listTime.size}")
        return listTime.size
    }
}