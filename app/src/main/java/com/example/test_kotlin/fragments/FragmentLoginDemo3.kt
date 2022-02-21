package com.example.test_kotlin.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
//        if (binding.etEmailLogin.requestFocus()) {
//            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//        }
        binding.btnLogin.setOnClickListener(this)
        binding.tvForgotPasswordLogin.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btnLogin.id -> {
                val txtEtEmail = binding.etEmailLogin.text
                if (TextUtils.isEmpty(txtEtEmail) || TextUtils.isEmpty(txtEtEmail)) {
                    Toast.makeText(requireContext(), "Enter All Field", Toast.LENGTH_SHORT).show()
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(txtEtEmail.toString())
                        .matches()
                ) {
                    Toast.makeText(requireContext(), "Enter Valid Email", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Login Successfully", Toast.LENGTH_SHORT)
                        .show()
                    binding.etEmailLogin.text?.clear()
                    binding.etPasswordLogin.text?.clear()
                    binding.etEmailLogin.requestFocus()
                }
            }
            binding.tvForgotPasswordLogin.id -> {
                Toast.makeText(requireContext(), "Forgot Password Was Clicked", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}