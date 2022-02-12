package com.example.test_kotlin.adapters

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.test_kotlin.R
import com.example.test_kotlin.databinding.RecyclerviewLayoutBottomBinding
import com.example.test_kotlin.databinding.RecyclerviewLayoutMiddleBinding
import com.example.test_kotlin.databinding.RecyclerviewLayoutTopBinding
import com.example.test_kotlin.models.ModelLayoutHolder


class LayoutHolderAdapter(
    private val context: Context,
    var list: ArrayList<ModelLayoutHolder>,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val THE_FIRST_VIEW = 1
        const val THE_SECOND_VIEW = 2
        const val THE_THIRD_VIEW = 3
    }

    private inner class LayoutHold1ViewHolder(val binding: RecyclerviewLayoutTopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val layoutHolder = list[position]
            binding.postTop = layoutHolder
            binding.postTop = layoutHolder
            binding.postTop = layoutHolder
            binding.postTop = layoutHolder
        }
    }


    inner class LayoutHold2ViewHolder(private val binding: RecyclerviewLayoutMiddleBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(position: Int) {
            val layoutHolder = list[position]
            binding.postMiddle = layoutHolder
            binding.postMiddle = layoutHolder
            binding.tvText1.setOnClickListener(this)
            binding.tvText2.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            when (p0?.id) {
                binding.tvText1.id -> {
                    val navController: NavController =
                        Navigation.findNavController(context as Activity, R.id.fragment_host)
                    val text1 = binding.tvText1.text.toString()
                    val arguments = Bundle()
                    arguments.putString("KEY_1", text1)
                    Log.i("TAG", arguments.toString())
                    navController.navigate(R.id.dataShowFragment, arguments)
                }
                binding.tvText2.id -> {
                    val navController: NavController =
                        Navigation.findNavController(context as Activity, R.id.fragment_host)
                    val text1 = binding.tvText2.text.toString()
                    val arguments = Bundle()
                    arguments.putString("KEY_1", text1)
                    Log.i("TAG", arguments.toString())
                    navController.navigate(R.id.dataShowFragment, arguments)
                }
            }
        }
    }

    private inner class LayoutHold3ViewHolder(val binding: RecyclerviewLayoutBottomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val layoutHolder = list[position]
            binding.postBottom = layoutHolder
            binding.postBottom = layoutHolder
            binding.postBottom = layoutHolder
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            THE_FIRST_VIEW -> {
                return LayoutHold1ViewHolder(
                    RecyclerviewLayoutTopBinding.inflate(
                        LayoutInflater.from(context),
                        parent, false
                    )
                )
            }
            THE_SECOND_VIEW -> {
                return LayoutHold2ViewHolder(
                    RecyclerviewLayoutMiddleBinding.inflate(
                        LayoutInflater.from(
                            context
                        ),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return LayoutHold3ViewHolder(
                    RecyclerviewLayoutBottomBinding.inflate(
                        LayoutInflater.from(context), parent, false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (list[position].viewType) {
            THE_FIRST_VIEW -> {
                (holder as LayoutHold1ViewHolder).bind(position)
            }
            THE_SECOND_VIEW -> {
                (holder as LayoutHold2ViewHolder).bind(position)
            }
            else -> {
                (holder as LayoutHold3ViewHolder).bind(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }
}