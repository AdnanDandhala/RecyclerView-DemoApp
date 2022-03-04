package com.example.test_kotlin.fragments

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
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
import com.example.test_kotlin.databinding.BottomSheetDailogBinding
import com.example.test_kotlin.databinding.FragmentSearchDemo3Binding
import com.example.test_kotlin.room.Users
import com.example.test_kotlin.viewmodel.UserViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Suppress("SENSELESS_COMPARISON")
@SuppressLint("NotifyDataSetChanged", "InflateParams")
class FragmentSearchDemo3 : Fragment() {
    private lateinit var binding: FragmentSearchDemo3Binding
    private lateinit var userViewModel: UserViewModel
    private lateinit var dialogDelete: Dialog
    private lateinit var demo3Adapter: SearchDemo3Adapter
    private var user: Users? = null
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
        initObserver()
        binding.etSearchUsers.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                demo3Adapter.filter.filter(p0)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun recyclerViewSwipeFunctionality(list: List<Users>) {
        val swipeToDeleteCallBack = object : SwipeItemTouchHelper() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                user = demo3Adapter.getUsersAtPosition(viewHolder.adapterPosition)
                val position = viewHolder.adapterPosition
                val finalId = list[position].id
                if (direction == ItemTouchHelper.LEFT) {
                    finalId?.let { createBottomSheet(it) }
                } else if (direction == ItemTouchHelper.RIGHT) {
                    dialogDelete = Dialog(requireContext())
                    val viewDelete = layoutInflater.inflate(R.layout.alert_dialog_delete, null)
                    dialogDelete.setContentView(viewDelete)
                    dialogDelete.window?.setBackgroundDrawable(ColorDrawable(0))
                    dialogDelete.window?.setLayout(
                        ActionBar.LayoutParams.MATCH_PARENT,
                        ActionBar.LayoutParams.WRAP_CONTENT
                    )
                    dialogDelete.window?.setBackgroundDrawable(
                        InsetDrawable(
                            ColorDrawable(
                                resources.getColor(R.color.transparent, null)
                            ), 32, 0, 32, 0
                        )
                    )
                    dialogDelete.show()
                    demo3Adapter.notifyDataSetChanged()
                    viewDelete?.findViewById<Button>(R.id.btn_positive)?.setOnClickListener {
                        user?.let {
                            userViewModel.deleteUser(requireContext(), it)
                        }
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


    fun createBottomSheet(id: Int) {
        val dialog = BottomSheetDialog(requireContext())
        val binding = BottomSheetDailogBinding.inflate(layoutInflater)
        dialog.setCancelable(true)
        dialog.setContentView(binding.root)
        dialog.show()
        val customSpinnerItems = mutableListOf(
            "Ahmedabad",
            "Rajkot",
            "Mumbai",
            "Bihar",
            "America",
            "London"
        )
        val adapter = object : ArrayAdapter<String>(
            requireContext(), android.R.layout.simple_list_item_1,
            customSpinnerItems
        ) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                return super.getDropDownView(position, convertView, parent) as TextView
            }
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        userViewModel.getRequested(requireContext(), id).observe(requireActivity()) {
            if (adapter != null) {
                binding.cityDropDownBottomSheet.adapter = adapter
                binding.postBottomSheetRegister = it
            }
            val index: Int
            if (customSpinnerItems.contains(it.City)) {
                index = customSpinnerItems.indexOf(it.City)
                customSpinnerItems.removeAt(index)
            }
            customSpinnerItems.add(0, it.City)
            demo3Adapter.notifyDataSetChanged()
        }
        binding.btnUpdateBottomSheet.setOnClickListener {
            val updatedUserName = binding.etUsernameBottomSheet.text.toString()
            val updatedMobileNo = binding.etNumberBottomSheet.text.toString()
            val updatedEmailAddress = binding.etEmailBottomSheet.text.toString()
            val updatedPassword = binding.etPasswordBottomSheet.text.toString()
            val updatedAddress = binding.etAddressBottomSheet.text.toString()
            val updatedPinCode = binding.etPinCodeBottomSheet.text.toString()
            val updatedCity = binding.cityDropDownBottomSheet.selectedItem.toString()
            val updatedUser =
                Users(
                    updatedUserName,
                    updatedMobileNo,
                    updatedEmailAddress,
                    updatedPassword,
                    updatedAddress,
                    updatedPinCode,
                    updatedCity,
                    user?.id
                )
            userViewModel.updateData(requireContext(), updatedUser)
            demo3Adapter.notifyDataSetChanged()
            dialog.dismiss()
        }
    }

    private fun initObserver() {
        userViewModel.getDetails(requireContext()).observe(requireActivity()) {
            if (it.isNotEmpty()) {
                binding.toolbarSearchDemo3.visibility = View.GONE
                binding.parentLayoutSearch.visibility = View.VISIBLE
                binding.imageViewNoDataPresentGallery.visibility = View.GONE
                binding.tvNoDataPresent.visibility = View.GONE
                binding.etSearchUsers.visibility = View.VISIBLE
                binding.recyclerViewSearchDemo3.visibility = View.VISIBLE
                lifecycleScope.launch(Dispatchers.IO) {
                    demo3Adapter = SearchDemo3Adapter(ArrayList(it))
                    withContext(Dispatchers.Main) {
                        binding.recyclerViewSearchDemo3.layoutManager =
                            LinearLayoutManager(requireContext())
                        binding.recyclerViewSearchDemo3.adapter = demo3Adapter
                        demo3Adapter.notifyDataSetChanged()
                        (binding.recyclerViewSearchDemo3.itemAnimator as SimpleItemAnimator).supportsChangeAnimations =
                            false
                        recyclerViewSwipeFunctionality(it)
                    }
                }
            } else {
                binding.toolbarSearchDemo3.visibility = View.VISIBLE
                binding.parentLayoutSearch.visibility = View.GONE
                binding.recyclerViewSearchDemo3.visibility = View.GONE
                binding.imageViewNoDataPresentGallery.visibility = View.VISIBLE
                binding.tvNoDataPresent.visibility = View.VISIBLE
            }
        }

    }
}
