package com.example.test_kotlin.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.test_kotlin.R
import com.example.test_kotlin.databinding.FragmentRegistorDemo3Binding
import com.example.test_kotlin.room.Users
import com.example.test_kotlin.viewmodel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val spinnerItems = arrayOf(
    "Select City",
    "Ahmedabad",
    "Rajkot",
    "Mumbai",
    "Bihar",
    "America",
    "London"
)

@Suppress("NAME_SHADOWING", "DEPRECATION")
class FragmentRegisterDemo3 : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentRegistorDemo3Binding
    private lateinit var userViewModel: UserViewModel
    private lateinit var viewPager2: ViewPager2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegistorDemo3Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding.btnSignup.setOnClickListener(this)
        viewPager2 = activity?.findViewById(R.id.demo3_viewPager)!!
        val adapter = object : ArrayAdapter<String>(
            requireContext(), android.R.layout.simple_list_item_1,
            spinnerItems
        ) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view: TextView =
                    super.getDropDownView(position, convertView, parent) as TextView
                if (position == 0) {
                    view.setTextColor(resources.getColor(R.color.gray))
                } else {
                    view.setTextColor(Color.BLACK)
                }
                return view
            }
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.cityDropDown.adapter = adapter
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btnSignup.id -> {
                if (checkEmpty()) {
                    Toast.makeText(requireContext(), "Enter All Field", Toast.LENGTH_SHORT).show()
                } else if (!checkPhoneNumberFormat()) {
                    Toast.makeText(
                        requireContext(),
                        "Enter Valid Mobile Number",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else if (!checkEmailFormat()) {
                    Toast.makeText(requireContext(), "Enter Valid Email Format", Toast.LENGTH_SHORT)
                        .show()
                } else if (!checkPassword()) {
                    Toast.makeText(
                        requireContext(),
                        "Password And Confirm Password Doesn't Matches",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val userName = binding.etUsernameSignup.text.toString()
                    val mobileNo = binding.etNumberSignup.text.toString()
                    val emailAddress = binding.etEmailSignup.text.toString()
                    val password = binding.etPasswordSignup.text.toString()
                    val address = binding.etAddressSignup.text.toString()
                    val pinCode = binding.etPinCodeSignup.text.toString()
                    val city = binding.cityDropDown.selectedItem.toString()
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            val isPresent = userViewModel.checkEmail(emailAddress)
                            withContext(Dispatchers.Main) {
                                if (isPresent) {
                                    Toast.makeText(
                                        requireContext(),
                                        "Email Already Exists",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    userViewModel.addUser(
                                        Users(
                                            userName,
                                            mobileNo,
                                            emailAddress,
                                            password,
                                            address,
                                            pinCode,
                                            city
                                        )
                                    )
                                    p0.hideKeyboard()
                                    Toast.makeText(
                                        requireContext(),
                                        "Registered Successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    viewPager2.currentItem = 0
                                    clearAllFields()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun checkPassword(): Boolean {
        return TextUtils.equals(
            binding.etPasswordSignup.text,
            binding.etConfirmPasswordSignup.text
        )
    }

    private fun checkPhoneNumberFormat(): Boolean {
        val numberFormat = "^[0-9]$"
        val number = binding.etNumberSignup.text.toString().trim()
        return !(number.length < 10 || number.length > 13 || number.matches(numberFormat.toRegex()))
    }

    private fun checkEmailFormat(): Boolean {
        val txtEtEmail = binding.etEmailSignup.text?.trim()
        return android.util.Patterns.EMAIL_ADDRESS.matcher(txtEtEmail.toString()).matches()
    }

    private fun clearAllFields() {
        binding.etUsernameSignup.text?.clear()
        binding.etNumberSignup.text?.clear()
        binding.etNumberSignup.clearFocus()
        binding.etEmailSignup.text?.clear()
        binding.etEmailSignup.clearFocus()
        binding.etPasswordSignup.text?.clear()
        binding.etPasswordSignup.clearFocus()
        binding.etConfirmPasswordSignup.text?.clear()
        binding.etConfirmPasswordSignup.clearFocus()
        binding.etAddressSignup.text?.clear()
        binding.etAddressSignup.clearFocus()
        binding.etPinCodeSignup.text?.clear()
        binding.etPinCodeSignup.clearFocus()
        binding.cityDropDown.setSelection(0)
        binding.scrollViewSignup.fullScroll(ScrollView.FOCUS_UP)
        binding.etUsernameSignup.clearFocus()
    }

    private fun checkEmpty(): Boolean {
        return TextUtils.isEmpty(binding.etUsernameSignup.text) || TextUtils.isEmpty(binding.etNumberSignup.text) || TextUtils.isEmpty(
            binding.etEmailSignup.text
        ) || TextUtils.isEmpty(binding.etPasswordSignup.text) || TextUtils.isEmpty(binding.etConfirmPasswordSignup.text) || TextUtils.isEmpty(
            binding.etAddressSignup.text
        ) || TextUtils.isEmpty(binding.etPinCodeSignup.text)
    }

    private fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
}
