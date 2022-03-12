package com.example.test_kotlin.fragments

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.R
import com.example.test_kotlin.adapters.Demo4Adapter
import com.example.test_kotlin.api.ApiRepository
import com.example.test_kotlin.api.ApiViewModel
import com.example.test_kotlin.api.ApiViewModelFactory
import com.example.test_kotlin.api.UserApiInterface
import com.example.test_kotlin.databinding.FragmentDemo4Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class FragmentDemo4 : Fragment() {
    private lateinit var binding: FragmentDemo4Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDemo4Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isNetworkAvailable(requireContext())) {
            initializeData()
        } else {
            AlertDialog.Builder(requireContext()).setTitle("!No Internet")
                .setMessage("Internet Connection Required To Load Data")
                .setPositiveButton(android.R.string.yes, object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        findNavController().navigate(R.id.fragmentFirstScreen2)
                        findNavController().popBackStack(R.id.fragmentDemo4, true)
                    }
                })
                .setNegativeButton(android.R.string.no, object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        val intent = Intent(Intent.ACTION_MAIN)
                        intent.setClassName(
                            "com.android.settings",
                            "com.android.settings.Settings\$DataUsageSummaryActivity"
                        )
                        startActivity(intent)
                    }
                })
                .show()
        }
    }


    private fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }

    private fun initializeData() {
        val viewModel = ViewModelProvider(
            this,
            ApiViewModelFactory(ApiRepository(UserApiInterface.getInstance()))
        )[ApiViewModel::class.java]
        viewModel.getAllUsers()
        viewModel.usersList.observe(requireActivity()) {
            lifecycleScope.launch(Dispatchers.IO) {
                val adapter = Demo4Adapter(it)
                withContext(Dispatchers.Main) {
                    binding.mainRecyclerViewDemo4.layoutManager =
                        LinearLayoutManager(requireContext())
                    binding.mainRecyclerViewDemo4.isNestedScrollingEnabled = false
                    binding.mainRecyclerViewDemo4.setItemViewCacheSize(100)
                    binding.mainRecyclerViewDemo4.adapter = adapter
                    binding.layoutMainFragmentDemo4.setBackgroundColor(Color.parseColor("#FFFFFF"))
                }
            }
        }
    }
}