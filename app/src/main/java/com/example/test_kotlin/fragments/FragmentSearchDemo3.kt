package com.example.test_kotlin.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.test_kotlin.R
import com.example.test_kotlin.SwipeItemTouchHelper
import com.example.test_kotlin.adapters.SearchDemo3Adapter
import com.example.test_kotlin.databinding.FragmentSearchDemo3Binding
import com.example.test_kotlin.viewmodel.UserViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentSearchDemo3 : Fragment() {
    private lateinit var binding: FragmentSearchDemo3Binding
    private lateinit var userViewModel: UserViewModel
    private lateinit var dialogDelete: Dialog
    private lateinit var adapter: SearchDemo3Adapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchDemo3Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        refreshRecyclerView()
        recyclerViewSwipeFunctionality()
    }

    private fun recyclerViewSwipeFunctionality() {
        val swipeToDeleteCallBack = object : SwipeItemTouchHelper() {
            @SuppressLint("InflateParams")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (direction == ItemTouchHelper.LEFT) {
                    refreshRecyclerView()
                    getRequestedData(viewHolder.adapterPosition)
                    Log.i("TAG", viewHolder.adapterPosition.toString())
                    val dialog = BottomSheetDialog(requireContext())
                    val view = layoutInflater.inflate(R.layout.bootom_sheet_dailog, null)
                    val btnUpdate = view.findViewById<Button>(R.id.btn_update_bottom_sheet)
                    btnUpdate.setOnClickListener {
                        dialog.dismiss()
                    }
                    dialog.setCancelable(true)
                    dialog.setContentView(view)
                    dialog.show()
                } else if (direction == ItemTouchHelper.RIGHT) {
                    refreshRecyclerView()
                    dialogDelete = Dialog(requireContext())
                    val viewDelete = layoutInflater.inflate(R.layout.alert_dialog_delete, null)
                    dialogDelete.setContentView(viewDelete)
                    dialogDelete.window?.setBackgroundDrawable(ColorDrawable(0))
                    dialogDelete.show()
                    viewDelete?.findViewById<Button>(R.id.btn_positive)?.setOnClickListener {
                        Log.i("TAG Adapter", position.toString())
                        val user = adapter.getUsersAtPosition(position)
                        userViewModel.deleteUser(requireContext(), user)
                        Toast.makeText(requireContext(), "User Deleted", Toast.LENGTH_SHORT).show()
                        dialogDelete.dismiss()
                    }
                    viewDelete?.findViewById<Button>(R.id.btn_negative)?.setOnClickListener {
                        Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
                        dialogDelete.dismiss()
                    }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewSearchDemo3)
    }

    private fun getRequestedData(adapterPosition: Int) {

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun refreshRecyclerView() {
        userViewModel.getDetails(requireContext()).observe(requireActivity()) {
            if (it != null) {
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        adapter = SearchDemo3Adapter(it)
                        withContext(Dispatchers.Main) {
                            binding.recyclerViewSearchDemo3.layoutManager =
                                LinearLayoutManager(requireContext())
                            binding.recyclerViewSearchDemo3.adapter = adapter
                            adapter.notifyDataSetChanged()
                            (binding.recyclerViewSearchDemo3.itemAnimator as SimpleItemAnimator).supportsChangeAnimations =
                                false
                        }
                    }
                }
            }
        }

    }
}
