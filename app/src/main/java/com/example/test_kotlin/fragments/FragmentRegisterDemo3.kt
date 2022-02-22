package com.example.test_kotlin.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.test_kotlin.R
import com.example.test_kotlin.databinding.FragmentRegistorDemo3Binding

val spinnerItems = arrayOf(
    "Select City",
    "Ahmedabad",
    "Rajkot",
    "Mumbai",
    "Bihar",
    "America",
    "London"
)

@Suppress("NAME_SHADOWING")
class FragmentRegisterDemo3 : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentRegistorDemo3Binding

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
        binding.btnSignup.setOnClickListener(this)
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
        if (checkEmpty()) {
            Toast.makeText(requireContext(), "Enter All Field", Toast.LENGTH_SHORT).show()
        } else if (!checkPhoneNumberFormat()) {
            Toast.makeText(requireContext(), "Enter Valid Mobile Number", Toast.LENGTH_SHORT)
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
            Toast.makeText(requireContext(), "Registered Successfully", Toast.LENGTH_SHORT)
                .show()
            clearAllFields()
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
        binding.etEmailSignup.text?.clear()
        binding.etPasswordSignup.text?.clear()
        binding.etConfirmPasswordSignup.text?.clear()
        binding.etAddressSignup.text?.clear()
        binding.etPinCodeSignup.text?.clear()
        binding.cityDropDown.setSelection(0)
    }

    private fun checkEmpty(): Boolean {
        return TextUtils.isEmpty(binding.etUsernameSignup.text) || TextUtils.isEmpty(binding.etNumberSignup.text) || TextUtils.isEmpty(
            binding.etEmailSignup.text
        ) || TextUtils.isEmpty(binding.etPasswordSignup.text) || TextUtils.isEmpty(binding.etConfirmPasswordSignup.text) || TextUtils.isEmpty(
            binding.etAddressSignup.text
        ) || TextUtils.isEmpty(binding.etPinCodeSignup.text)
    }
}
