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
import com.example.test_kotlin.room.Users
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
    }

    private fun recyclerViewSwipeFunctionality(list: List<Users>) {
        val swipeToDeleteCallBack = object : SwipeItemTouchHelper() {
            @SuppressLint("InflateParams")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val finalP = list[position].id
                if (direction == ItemTouchHelper.LEFT) {
                    refreshRecyclerView()
                    finalP?.let { getRequestedData(it) }
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
                    dialogDelete.window?.setLayout(1000, 500)
                    viewDelete?.findViewById<Button>(R.id.btn_positive)?.setOnClickListener {
                        val user = adapter.getUsersAtPosition(position)
                        userViewModel.deleteUser(requireContext(), user)
                        Toast.makeText(requireContext(), "User Deleted", Toast.LENGTH_SHORT).show()
                        dialogDelete.dismiss()
                    }
                    viewDelete?.findViewById<Button>(R.id.btn_negative)?.setOnClickListener {
                        dialogDelete.dismiss()
                    }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewSearchDemo3)
    }

    private fun getRequestedData(position: Int) {
        Log.i("TAG", "$position")
        userViewModel.getRequested(requireContext(), position).observe(requireActivity()) {
            lifecycleScope.launch(Dispatchers.Main) {
                Log.i("TAG", it.UserName)
            }
        }

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun refreshRecyclerView() {
        userViewModel.getDetails(requireContext()).observe(requireActivity()) {
            if (it.isNotEmpty()) {
                binding.imageViewNoDataPresentGallery.visibility = View.GONE
                binding.tvNoDataPresent.visibility = View.GONE
                binding.recyclerViewSearchDemo3.visibility = View.VISIBLE
                lifecycleScope.launch(Dispatchers.IO) {
                    adapter = SearchDemo3Adapter(it)
                    withContext(Dispatchers.Main) {
                        binding.recyclerViewSearchDemo3.layoutManager =
                            LinearLayoutManager(requireContext())
                        binding.recyclerViewSearchDemo3.adapter = adapter
                        adapter.notifyDataSetChanged()
                        (binding.recyclerViewSearchDemo3.itemAnimator as SimpleItemAnimator).supportsChangeAnimations =
                            false
                        recyclerViewSwipeFunctionality(it)
                    }
                }
            } else {
                Log.i("TAG", it?.size.toString())
                binding.recyclerViewSearchDemo3.visibility = View.GONE
                binding.imageViewNoDataPresentGallery.visibility = View.VISIBLE
                binding.tvNoDataPresent.visibility = View.VISIBLE
            }
        }

    }
}
