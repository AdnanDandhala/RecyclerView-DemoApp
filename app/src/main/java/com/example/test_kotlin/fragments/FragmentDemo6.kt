package com.example.test_kotlin.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.R
import com.example.test_kotlin.adapters.Demo6Adapter
import com.example.test_kotlin.databinding.FragmentDemo6Binding
import com.example.test_kotlin.models.ModelDemo6
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

val spinnerItemsDays = arrayOf(
    "Select Days",
    "Sunday",
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday"
)
val spinnerItemsMonth = arrayOf(
    "Select Month",
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec"
)
val spinnerItemsYear = arrayOf(
    "Select Year",
    "2015",
    "2016",
    "2017",
    "2018",
    "2019",
    "2020",
    "2021",
    "2022"
)

class Demo6 : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentDemo6Binding

    @SuppressLint("SimpleDateFormat")
    private var df: DateFormat? = SimpleDateFormat("d MMM yyyy, HH:mm")
    private var date = df?.format(Calendar.getInstance().time)
    private val user = hashMapOf(
        "date" to date.toString(),
        "tittle" to "It has survived not only five centuries, but also the leap into electronic"
    )
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
        binding.tvFilterTime.setOnClickListener(this)
        binding.tvFilterMonth.setOnClickListener(this)
        binding.tvFilterYear.setOnClickListener(this)
        binding.spinnerSearchDataDayFireStore.onItemSelectedListener = this
        binding.spinnerSearchDataMonthFireStore.onItemSelectedListener = this
        binding.spinnerSearchDataYearFireStore.onItemSelectedListener = this
        val adapter = object : ArrayAdapter<String>(
            requireContext(), android.R.layout.simple_list_item_1,
            spinnerItemsDays
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
        binding.spinnerSearchDataDayFireStore.adapter = adapter

        Log.i("Time", date.toString())
//        addUser()
        fetchData()
//        binding.etSearchDataFireStore.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
////                demo6Adapter.filter.filter(p0)
//                val list = ArrayList<ModelDemo6>()
//                val query = db.collection("users")
//                    .whereEqualTo("time", binding.etSearchDataFireStore.text.toString())
//                query.get().addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        for (document: QueryDocumentSnapshot in task.result) {
//                            val demo6 = document.toObject(ModelDemo6::class.java)
//                            list.add(demo6)
//                        }
//                        binding.recyclerViewDemo6.adapter = Demo6Adapter(list)
//                    } else {
//                        Toast.makeText(
//                            requireContext(),
//                            "Query Failed. Check Logs",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//
//            }
//
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//        })
    }

    private fun searchUserByDay(day: String) {
        val list = ArrayList<ModelDemo6>()
        val query = db.collection("users")
            .whereEqualTo("day", day)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.i("MAIN_TAG", "Task Was Sucessfull")
                for (document: QueryDocumentSnapshot in task.result) {
                    val demo6 = document.toObject(ModelDemo6::class.java)
                    list.add(demo6)
                    Log.i("MAIN_TAG", list.size.toString())
                }
                binding.recyclerViewDemo6.adapter = Demo6Adapter(list)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Query Failed. Check Logs",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun addUser() {
        val userReference1 = db.collection("users").document("1").set(user)
        db.collection("users").document("1").set(user)
        db.collection("users").document("2").set(user)
        db.collection("users").document("3").set(user)
        db.collection("users").document("4").set(user)
        db.collection("users").document("5").set(user)
        db.collection("users").document("6").set(user)
        db.collection("users").document("7").set(user)
        db.collection("users").document("8").set(user)
        db.collection("users").document("9").set(user)
        userReference1.addOnSuccessListener { document ->
            if (document != null) {
                Log.d("DataFirestore", "DocumentSnapshot data: $document")
            } else {
                Log.d("DataFirestore", "No such document")
            }
        }.addOnFailureListener { exception ->
            Log.d("DataFirestore", "get failed with ", exception)
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
                binding.tvFireStore.visibility = View.VISIBLE
                binding.recyclerViewDemo6.visibility = View.GONE
            } else if (it.isNotEmpty()) {
                binding.recyclerViewDemo6.visibility = View.VISIBLE
                binding.tvFireStore.visibility = View.GONE
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
                fetchData()
                binding.spinnerSearchDataDayFireStore.visibility = View.VISIBLE
                binding.spinnerSearchDataMonthFireStore.visibility = View.GONE
                binding.spinnerSearchDataYearFireStore.visibility = View.GONE
                binding.imgDashDay.visibility = View.VISIBLE
                binding.imgDashWeek.visibility = View.GONE
                binding.imgDashMonth.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
                binding.etStartDateFilter.visibility = View.GONE
                binding.etEndDateSearch.visibility = View.GONE
//                binding.etSearchDataFireStore.visibility = View.GONE
                val adapter = object : ArrayAdapter<String>(
                    requireContext(), android.R.layout.simple_list_item_1,
                    spinnerItemsDays
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
                binding.spinnerSearchDataDayFireStore.adapter = adapter
            }
            binding.tvFilterTime.id -> {
                fetchData()
                binding.spinnerSearchDataDayFireStore.visibility = View.GONE
                binding.spinnerSearchDataMonthFireStore.visibility = View.GONE
                binding.spinnerSearchDataYearFireStore.visibility = View.GONE
                binding.imgDashWeek.visibility = View.VISIBLE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashMonth.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
                binding.etStartDateFilter.visibility = View.VISIBLE
                binding.etEndDateSearch.visibility = View.VISIBLE
//                binding.etSearchDataFireStore.visibility = View.VISIBLE
//                binding.etSearchDataFireStore.hint = "Enter Time"
            }
            binding.tvFilterMonth.id -> {
                fetchData()
                binding.spinnerSearchDataMonthFireStore.visibility = View.VISIBLE
                binding.spinnerSearchDataDayFireStore.visibility = View.GONE
                binding.spinnerSearchDataYearFireStore.visibility = View.GONE
                binding.imgDashMonth.visibility = View.VISIBLE
                binding.imgDashWeek.visibility = View.GONE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
//                binding.etSearchDataFireStore.visibility = View.GONE
                binding.etStartDateFilter.visibility = View.GONE
                binding.etEndDateSearch.visibility = View.GONE
                val adapter = object : ArrayAdapter<String>(
                    requireContext(), android.R.layout.simple_list_item_1,
                    spinnerItemsMonth
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
                binding.spinnerSearchDataMonthFireStore.adapter = adapter
            }
            binding.tvFilterYear.id -> {
                fetchData()
                binding.spinnerSearchDataYearFireStore.visibility = View.VISIBLE
                binding.spinnerSearchDataDayFireStore.visibility = View.GONE
                binding.spinnerSearchDataMonthFireStore.visibility = View.GONE
                binding.imgDashYear.visibility = View.VISIBLE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashMonth.visibility = View.GONE
                binding.imgDashWeek.visibility = View.GONE
//                binding.etSearchDataFireStore.visibility = View.GONE
                binding.etStartDateFilter.visibility = View.GONE
                binding.etEndDateSearch.visibility = View.GONE
                val adapter = object : ArrayAdapter<String>(
                    requireContext(), android.R.layout.simple_list_item_1,
                    spinnerItemsYear
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
                binding.spinnerSearchDataYearFireStore.adapter = adapter
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p0?.id) {
            binding.spinnerSearchDataDayFireStore.id -> {
                val selectedItem = binding.spinnerSearchDataDayFireStore.selectedItem.toString()
                Log.i("SelectedItem", selectedItem)
                if (selectedItem != "Select Days") {
                    searchUserByDay(selectedItem)
                }
            }
            binding.spinnerSearchDataMonthFireStore.id -> {
                val selectedItem = binding.spinnerSearchDataMonthFireStore.selectedItem.toString()
                Log.i("SelectedItem", selectedItem)
                if (selectedItem == "Select Days") {
                    searchUserByMonth(selectedItem)
//                    Log.i("TAG", "It Was Called")
                }
            }
            binding.spinnerSearchDataYearFireStore.id -> {
                val selectedItem = binding.spinnerSearchDataYearFireStore.selectedItem.toString()
                Log.i("SelectedItem", selectedItem)
                if (selectedItem == "Select Days") {
                    searchUserByYear(selectedItem)
                }
            }
        }
    }

    private fun searchUserByYear(year: String) {
        val list = ArrayList<ModelDemo6>()
        val query = db.collection("users")
            .whereEqualTo("date", year)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document: QueryDocumentSnapshot in task.result) {
                    val demo6 = document.toObject(ModelDemo6::class.java)
                    list.add(demo6)
                    Log.i("Main", list.size.toString())
                }
                binding.recyclerViewDemo6.adapter = Demo6Adapter(list)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Query Failed. Check Logs",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    private fun searchUserByMonth(month: String) {
        val list = ArrayList<ModelDemo6>()
        val query = db.collection("users")
            .whereEqualTo("date", month)
        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document: QueryDocumentSnapshot in task.result) {
                    val demo6 = document.toObject(ModelDemo6::class.java)
                    list.add(demo6)
                    Log.i("Main", list.size.toString())
                }
                binding.recyclerViewDemo6.adapter = Demo6Adapter(list)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Query Failed. Check Logs",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


}