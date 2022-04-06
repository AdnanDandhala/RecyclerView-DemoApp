package com.example.test_kotlin.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.R
import com.example.test_kotlin.adapters.Demo6Adapter
import com.example.test_kotlin.databinding.FragmentDemo6Binding
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
    private var counter = 0


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
        binding.imgLoadData.setOnClickListener {
            binding.recyclerViewDemo6.visibility = View.VISIBLE
            binding.imgAddNew.visibility = View.VISIBLE
            binding.imgLoadData.visibility = View.GONE
            binding.childEtAddDataLayout.visibility = View.GONE
            binding.childLayoutDemo6.visibility = View.VISIBLE
            binding.pbLoadDataDemo6.visibility = View.VISIBLE
            binding.tvProgressTextDemo6.visibility = View.VISIBLE
            fetchData()
        }
        binding.imgAddNew.setOnClickListener {
            binding.recyclerViewDemo6.visibility = View.GONE
            binding.imgLoadData.visibility = View.VISIBLE
            binding.imgAddNew.visibility = View.GONE
            binding.childEtAddDataLayout.visibility = View.GONE
            binding.childLayoutDemo6.visibility = View.GONE
            binding.childEtAddDataLayout.visibility = View.VISIBLE
            binding.pbLoadDataDemo6.visibility = View.GONE
            binding.tvProgressTextDemo6.visibility = View.GONE

        }
        binding.btnAddDataFireStore.setOnClickListener {
            if (!TextUtils.isEmpty(binding.etTittleDemo6.text)) {
                val tittle = binding.etTittleDemo6.text.toString()
                addUser(tittle)
            } else {
                Toast.makeText(requireContext(), "Enter Tittle", Toast.LENGTH_SHORT).show()
            }

        }
        binding.toolbarDemo6.setNavigationOnClickListener {
            findNavController().navigate(R.id.fragmentFirstScreen2)
            findNavController().popBackStack(R.id.fragmentDemo6, inclusive = true)
        }
    }

    private fun addUser(tittle: String) {
        val dateTime = calendar.time
        val day = SimpleDateFormat("EEEE", Locale.ENGLISH).format(dateTime.time)
        db.collection("data").add(
            FirestoreModelItems(
                (Date()),
                tittle,
                day,
                counter
            )
        ).addOnCompleteListener { result ->
            if (result.isSuccessful) {
                Log.i("FirestoreData", "$result")
                Log.i("FirestoreData", "Data Added Successfully")
                counter++
                binding.etTittleDemo6.text!!.clear()
            } else {
                Log.i("FirestoreData", "Some Error Occurred")
            }
        }
    }

    private fun addDatePickerDay() {
        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        activity?.supportFragmentManager?.let { datePicker.show(it, "DataPicker") }
        datePicker.addOnPositiveButtonClickListener {
            Log.i("DATE_PICKER", "Positive Button Was Clicked ${datePicker.headerText}")
            Log.i("DATE_PICKER", "Positive Button Was Clicked ${datePicker.selection.toString()}")
            searchUserByDay(datePicker.headerText)
        }
        datePicker.addOnNegativeButtonClickListener {
            Log.i("DATE_PICKER", "Negative Button Was Clicked ${datePicker.selection}")
        }
    }

    private fun searchUserByDay(headerText: String) {
        Log.i("SearchData", headerText)
        val dayRef = db.collection("data")
        dayRef.whereEqualTo("day", "Wednesday").get().addOnSuccessListener { documents ->
            for (document in documents) {
                Log.d("SearchData", "${document.id} => ${document.data}")
            }
        }.addOnFailureListener {
            Log.w("SearchData", "Error Getting Documents $it")
        }
    }

    private fun addDatePickerWeek() {
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

    private fun addDatePickerMonth() {
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

    private fun addDatePickerYear() {
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
        val myViewModel =
            ViewModelProvider(this)[com.example.test_kotlin.viewmodel.MainViewModel::class.java]
        myViewModel.getDataFireStore()
        myViewModel.fireStoreData.observe(requireActivity()) {
            Log.i("TAG", it.size.toString())
            if (it.isEmpty()) {
                binding.recyclerViewDemo6.visibility = View.GONE
                Log.i("EMPTY", "List Is Empty")
            } else if (it.isNotEmpty()) {
                binding.recyclerViewDemo6.visibility = View.VISIBLE
                demo6Adapter = Demo6Adapter(it)
                Log.i("MAIN_DATA", it.toString())
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
                binding.imgDashDay.visibility = View.VISIBLE
                binding.imgDashWeek.visibility = View.GONE
                binding.imgDashMonth.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
                addDatePickerDay()
            }
            binding.tvFilterWeek.id -> {
                binding.imgDashWeek.visibility = View.VISIBLE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashMonth.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
                addDatePickerWeek()
            }
            binding.tvFilterMonth.id -> {
                binding.imgDashMonth.visibility = View.VISIBLE
                binding.imgDashWeek.visibility = View.GONE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
                addDatePickerMonth()
            }
            binding.tvFilterYear.id -> {
                binding.imgDashYear.visibility = View.VISIBLE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashMonth.visibility = View.GONE
                binding.imgDashWeek.visibility = View.GONE
                addDatePickerYear()
            }
        }
    }
}