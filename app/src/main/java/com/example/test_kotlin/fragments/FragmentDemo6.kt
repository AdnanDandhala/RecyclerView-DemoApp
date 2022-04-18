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
import com.example.test_kotlin.viewmodel.MainViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
class Demo6 : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentDemo6Binding
    private val calendar: Calendar = Calendar.getInstance()
    private var df: DateFormat? = SimpleDateFormat("d-M-yyyy")
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
        val millis = System.currentTimeMillis()
        db.collection("data").add(
            FirestoreModelItems(
                millis,
                tittle,
                day,
            )
        ).addOnCompleteListener { result ->
            if (result.isSuccessful) {
                Log.i("FirestoreData", "$result")
                Log.i("FirestoreData", "Data Added Successfully")
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
            Log.i("DATE_PICKER", "PositiveButton Was Clicked ${datePicker.selection.toString()}")
            searchUserByDay(getDayName(datePicker.headerText, "MMM dd,yyyy", "EEEE"))
        }
        datePicker.addOnNegativeButtonClickListener {
            Log.i("DATE_PICKER", "Negative Button Was Clicked ${datePicker.selection}")
        }
    }

    private fun searchUserByDay(day: String) {
        val tempList: ArrayList<FirestoreModelItems> = ArrayList()
        val dayRef = db.collection("data")
        tempList.clear()
        dayRef.whereEqualTo("day", day).get().addOnSuccessListener { documents ->
            for (document in documents) {
                Log.d("SearchData", "${document.id} => ${document.data}")
                val data = documents.toObjects(FirestoreModelItems::class.java)
                tempList.addAll(data)
            }
            binding.recyclerViewDemo6.adapter = Demo6Adapter(tempList)
        }.addOnFailureListener {
            Log.w("SearchData", "Error Getting Documents $it")
        }
        binding.recyclerViewDemo6.layoutManager = LinearLayoutManager(requireContext())
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
            searchUserByYear(getDayName(datePicker.headerText, "MMM dd,yyyy", "yyyy"))
        }
        datePicker.addOnNegativeButtonClickListener {
            Log.i("DATE_PICKER", "Negative Button Was Clicked ${datePicker.selection}")
        }
    }

    private fun searchUserByYear(year: String) {
        val tempList: ArrayList<FirestoreModelItems> = ArrayList()
        Log.i("SearchData", year)
        val dayRef = db.collection("data")
        dayRef.get().addOnSuccessListener { documents ->
            for (document in documents) {
                Log.d("SearchData", "${document.id} => ${document.data}")
                val allDate = document.getString("date")
                if (allDate != null) {
                    if (allDate.contains(year)) {
                        Log.i(
                            "YEAR_DATA",
                            "${document.getString("date")!!} ${document.getString("day")} ${
                                document.getString("tittle")
                            } ${document.id}"
                        )
                        val data = document.toObject(FirestoreModelItems::class.java)
                        tempList.add(data)
                    }
                }
            }
            binding.recyclerViewDemo6.adapter = Demo6Adapter(tempList)
        }.addOnFailureListener {
            Log.w("SearchData", "Error Getting Documents $it")
        }
        binding.recyclerViewDemo6.layoutManager = LinearLayoutManager(requireContext())
    }


    private fun getDayName(headerText: String, inputFormat: String, outputFormat: String): String {
        Log.e("TAG", " --- $headerText")
        try {
            val sdf = SimpleDateFormat("MMM dd,yyyy")
            val date = sdf.parse(headerText)
            val startDate = date?.time
            Log.i("TAG_TIME", startDate.toString())
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val inFormat = SimpleDateFormat(inputFormat)
        val date = inFormat.parse(headerText)
        val outFormat = SimpleDateFormat(outputFormat)
        val goal = outFormat.format(date!!)
        Log.e("TAG", "out put --- $date --- $goal")
        return goal
    }

    private fun fetchData() {
        val myViewModel =
            ViewModelProvider(this)[MainViewModel::class.java]
        myViewModel.getDataFireStore().observe(requireActivity()) {
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
                fetchData()
                addDatePickerDay()
            }
            binding.tvFilterWeek.id -> {
                binding.imgDashWeek.visibility = View.VISIBLE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashMonth.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
                fetchData()
                addDatePickerWeek()
            }
            binding.tvFilterMonth.id -> {
                binding.imgDashMonth.visibility = View.VISIBLE
                binding.imgDashWeek.visibility = View.GONE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
                fetchData()
                addDatePickerMonth()
            }
            binding.tvFilterYear.id -> {
                binding.imgDashYear.visibility = View.VISIBLE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashMonth.visibility = View.GONE
                binding.imgDashWeek.visibility = View.GONE
                fetchData()
                addDatePickerYear()
            }
        }
    }
}