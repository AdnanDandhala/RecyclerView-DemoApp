package com.example.test_kotlin.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.test_kotlin.databinding.FragmentLoginDemo3Binding


class FragmentLoginDemo3 : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentLoginDemo3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginDemo3Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (binding.tvUserLogin.isVisible || binding.btnLogout.isVisible) {
            binding.tvUserLogin.visibility = View.GONE
        }
        binding.btnLogin.setOnClickListener(this)
        binding.tvForgotPasswordLogin.setOnClickListener(this)
        binding.btnLogout.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btnLogin.id -> {
                val txtEtEmail = binding.etEmailLogin.text?.trim()
                val txtEtPassword = binding.etPasswordLogin.text
                if (TextUtils.isEmpty(txtEtEmail) || TextUtils.isEmpty(txtEtPassword)) {
                    Toast.makeText(requireContext(), "Enter All Field", Toast.LENGTH_SHORT).show()
                    binding.tvValidEmail.visibility = View.VISIBLE
                    binding.tvValidPassword.visibility = View.VISIBLE
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(txtEtEmail.toString())
                        .matches()
                ) {
                    if (!binding.tvValidEmail.isVisible) {
                        binding.tvValidEmail.visibility = View.VISIBLE
                    }
                } else {
                    if (binding.tvValidEmail.isVisible || binding.tvValidPassword.isVisible) {
                        binding.tvValidEmail.visibility = View.GONE
                        binding.tvValidPassword.visibility = View.GONE
                    }
                    p0.hideKeyboard()
                    binding.etEmailLogin.text?.clear()
                    binding.etPasswordLogin.text?.clear()
                    binding.etEmailLogin.requestFocus()
                    binding.parentLayoutLogin.visibility = View.GONE
                    binding.tvUserLogin.visibility = View.VISIBLE
                    binding.btnLogout.visibility = View.VISIBLE
                }
            }
            binding.tvForgotPasswordLogin.id -> {
                Toast.makeText(requireContext(), "Forgot Password Was Clicked", Toast.LENGTH_SHORT)
                    .show()
            }
            binding.btnLogout.id -> {
                if (!binding.parentLayoutLogin.isVisible) {
                    binding.parentLayoutLogin.visibility = View.VISIBLE
                    if (binding.tvUserLogin.isVisible && binding.btnLogout.isVisible) {
                        binding.tvUserLogin.visibility = View.GONE
                        binding.btnLogout.visibility = View.GONE
                        Toast.makeText(requireContext(), "Logout Successfully", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

}