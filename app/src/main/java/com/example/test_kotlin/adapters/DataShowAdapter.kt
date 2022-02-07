package com.example.test_kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test_kotlin.R
import com.example.test_kotlin.models.DataShowModel


class DataShowAdapter(private var list: ArrayList<DataShowModel>) :
    RecyclerView.Adapter<DataShowAdapter.DataShowViewHolder>() {

    inner class DataShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvShow: TextView = itemView.findViewById(R.id.tv_recyclerview_data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_data_show, parent, true)
        return DataShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataShowViewHolder, position: Int) {
        holder.tvShow.text = list[position].listOfDummyText.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}