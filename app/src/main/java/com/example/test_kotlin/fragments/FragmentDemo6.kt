package com.example.test_kotlin.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.R
import com.example.test_kotlin.adapters.Demo6Adapter
import com.example.test_kotlin.databinding.FragmentDemo6Binding
import com.example.test_kotlin.models.FirestoreModel
import com.example.test_kotlin.models.FirestoreModelItems
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
class Demo6 : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentDemo6Binding
    private val calendar: Calendar = Calendar.getInstance()
    private var df: DateFormat? = SimpleDateFormat("d MMM yyyy, HH:mm")
    private var date = df?.format(Calendar.getInstance().time)
    private val db = Firebase.firestore
    private lateinit var demo6Adapter: Demo6Adapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDemo6Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvFilterDay.setOnClickListener(this)
        binding.tvFilterWeek.setOnClickListener(this)
        binding.tvFilterMonth.setOnClickListener(this)
        binding.tvFilterYear.setOnClickListener(this)
        Log.i("Time", date.toString())
        addUser()
//        fetchData()
        binding.toolbarDemo6.setNavigationOnClickListener {
            findNavController().navigate(R.id.fragmentFirstScreen2)
            findNavController().popBackStack(R.id.fragmentDemo6, inclusive = true)
        }
    }

    private fun addUser() {
        val dateTime = calendar.time
        val time = SimpleDateFormat("EEEE", Locale.ENGLISH).format(dateTime.time)
        val user1 =
            FirestoreModel(
                FirestoreModelItems(
                    (Date()),
                    "This Is Demo For Testing Firestore",
                    time,
                    0
                )
            )
        val user2 =
            FirestoreModelItems(
                (Date()),
                "This Is Demo For Testing Firestore",
                time,
                1
            )
        val user3 =
            FirestoreModelItems(
                (Date()),
                "This Is Demo For Testing Firestore",
                time,
                2
            )
        val user4 =
            FirestoreModelItems(
                (Date()),
                "This Is Demo For Testing Firestore",
                time,
                3
            )
        val user5 =
            FirestoreModelItems(
                (Date()),
                "This Is Demo For Testing Firestore",
                time,
                4
            )
        val collection =
            db.collection("user").add(user1)
        db.collection("user").add(user2)
        db.collection("user").add(user3)
        db.collection("user").add(user4)
        db.collection("user").add(user5)
        collection.addOnCompleteListener { result ->
            if (result.isSuccessful) {
                Log.i("FirestoreData", "$result")
                Log.i("FirestoreData", "Data Added Successfully")
            } else {
                Log.i("FirestoreData", "Some Error Occured")
            }
        }
    }

    private fun addDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        activity?.supportFragmentManager?.let { datePicker.show(it, "DataPicker") }
        datePicker.addOnPositiveButtonClickListener {
            Log.i("DATE_PICKER", "Positive Button Was Clicked ${datePicker.headerText}")
            Log.i("DATE_PICKER", "Positive Button Was Clicked ${datePicker.selection.toString()}")
        }
        datePicker.addOnNegativeButtonClickListener {
            Log.i("DATE_PICKER", "Negative Button Was Clicked ${datePicker.selection}")
        }
    }

    private fun fetchData() {
        binding.pbLoadDataDemo6.visibility = View.VISIBLE
        binding.tvProgressTextDemo6.visibility = View.VISIBLE
        val myViewModel =
            ViewModelProvider(this)[com.example.test_kotlin.viewmodel.MainViewModel::class.java]
        myViewModel.getDataFireStore().observe(requireActivity()) {
            Log.i("TAG", it.size.toString())
            if (it.isEmpty()) {
                binding.recyclerViewDemo6.visibility = View.GONE
                Log.i("EMPTY", "List Is Empty")
            } else if (it.isNotEmpty()) {
                binding.recyclerViewDemo6.visibility = View.VISIBLE
                demo6Adapter = Demo6Adapter(it)
                binding.recyclerViewDemo6.adapter = demo6Adapter
                binding.recyclerViewDemo6.setHasFixedSize(true)
                binding.pbLoadDataDemo6.visibility = View.GONE
                binding.tvProgressTextDemo6.visibility = View.GONE
            }
            binding.recyclerViewDemo6.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.tvFilterDay.id -> {
//                fetchData()
                binding.imgDashDay.visibility = View.VISIBLE
                binding.imgDashWeek.visibility = View.GONE
                binding.imgDashMonth.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
                binding.recyclerViewDemo6.visibility = View.VISIBLE
                addDatePicker()
            }
            binding.tvFilterWeek.id -> {
//                fetchData()
                binding.imgDashWeek.visibility = View.VISIBLE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashMonth.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
                addDatePicker()
            }
            binding.tvFilterMonth.id -> {
//                fetchData()
                binding.imgDashMonth.visibility = View.VISIBLE
                binding.imgDashWeek.visibility = View.GONE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
                addDatePicker()

            }
            binding.tvFilterYear.id -> {
//                fetchData()
                binding.imgDashYear.visibility = View.VISIBLE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashMonth.visibility = View.GONE
                binding.imgDashWeek.visibility = View.GONE
                addDatePicker()
            }
        }
    }
}