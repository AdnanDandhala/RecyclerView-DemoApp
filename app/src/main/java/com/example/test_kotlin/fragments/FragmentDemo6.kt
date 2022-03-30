package com.example.test_kotlin.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.adapters.Demo6Adapter
import com.example.test_kotlin.databinding.FragmentDemo6Binding
import com.example.test_kotlin.models.ModelDemo6
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
class Demo6 : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentDemo6Binding
    private var cal = Calendar.getInstance()
    private var df: DateFormat? = SimpleDateFormat("d MMM yyyy, HH:mm")
    private var date = df?.format(Calendar.getInstance().time)
    private val db = Firebase.firestore
    private lateinit var demo6Adapter: Demo6Adapter
    private val user = hashMapOf(
        "date" to date.toString(),
        "tittle" to "It has survived not only five centuries, but also the leap into electronic"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDemo6Binding.inflate(layoutInflater)
        return binding.root
    }

    private val dateSetListenerDay =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDayInView(year, monthOfYear, dayOfMonth)
        }

    private fun updateDayInView(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        Log.i("Calender", "The Year Is $year")
        Log.i("Calender", "The Month Number ${monthOfYear + 1}")
        Log.i("Calender", "The Date Is $dayOfMonth")
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())
        val subStr: String = currentDate.substring(0, 2)
        Log.i("CURRENT_DATE", currentDate)
        Log.i("CURRENT_DATE", subStr)
        searchUserByDay("30 Mar 2022, 19:31")
    }

    private val dateSetListenerMonth =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateMonthInView(year, monthOfYear, dayOfMonth)
        }
    private val dateSetListenerYear =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateYearInView(year, monthOfYear, dayOfMonth)
        }

    private fun updateYearInView(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        Log.i("Calender", "The Year Is $year")
        Log.i("Calender", "The Month Number ${monthOfYear + 1}")
        Log.i("Calender", "The Date Is $dayOfMonth")
        searchUserByYear(year = year.toString())
    }

    private fun updateWeekInView(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        Log.i("Calender", "The Year Is $year")
        Log.i("Calender", "The Month Number ${monthOfYear + 1}")
        Log.i("Calender", "The Date Is $dayOfMonth")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvFilterDay.setOnClickListener(this)
        binding.tvFilterTime.setOnClickListener(this)
        binding.tvFilterMonth.setOnClickListener(this)
        binding.tvFilterYear.setOnClickListener(this)
        Log.i("Time", date.toString())
        addUser()
        fetchData()
    }

    private fun updateMonthInView(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        Log.i("Calender", "The Year Is $year")
        Log.i("Calender", "The Month Number ${monthOfYear + 1}")
        Log.i("Calender", "The Date Is $dayOfMonth")
        when (monthOfYear + 1) {
            1 -> {
                Log.i("Calender", "Jan")
                searchUserByMonth("Jan")
            }
            2 -> {
                Log.i("Calender", "Feb")
                searchUserByMonth("Feb")
            }
            3 -> {
                Log.i("Calender", "Mar")
                searchUserByMonth("Mar")
            }
            4 -> {
                Log.i("Calender", "Apr")
                searchUserByMonth("Apr")
            }
            5 -> {
                Log.i("Calender", "May")
                searchUserByMonth("May")
            }
            6 -> {
                Log.i("Calender", "Jun")
                searchUserByMonth("Jun")
            }
            7 -> {
                Log.i("Calender", "Jul")
                searchUserByMonth("Jul")
            }
            8 -> {
                Log.i("Calender", "Aug")
                searchUserByMonth("Aug")
            }
            9 -> {
                Log.i("Calender", "Sep")
                searchUserByMonth("Sep")
            }
            10 -> {
                Log.i("Calender", "Oct")
                searchUserByMonth("Oct")
            }
            11 -> {
                Log.i("Calender", "Nov")
                searchUserByMonth("Nov")
            }
            12 -> {
                Log.i("Calender", "Dec")
                searchUserByMonth("Dec")
            }
        }
        binding.recyclerViewDemo6.visibility = View.VISIBLE
    }

    private fun searchUserByDay(day: String) {
        val list = ArrayList<ModelDemo6>()
        val ref = db.collection("users")
        ref.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document: QueryDocumentSnapshot in task.result) {
                    val allDate: String? = document.getString("date")
                    if (allDate!!.contains(day)) {
                        Log.i(
                            "TAGLIST",
                            " ${document.getString("date")} : ${document.getString("day")}"
                        )
                        binding.recyclerViewDemo6.visibility = View.VISIBLE
                        binding.imageViewNoDataPresentGalleryDemo6.visibility = View.GONE
                        binding.tvNoDataPresentDemo6.visibility = View.GONE
                        val demo6 = document.toObject(ModelDemo6::class.java)
                        list.add(demo6)
                    } else {
                        binding.recyclerViewDemo6.visibility = View.GONE
                        binding.imageViewNoDataPresentGalleryDemo6.visibility = View.VISIBLE
                        binding.tvNoDataPresentDemo6.visibility = View.VISIBLE
                    }
                }
                Log.i("Main", list.size.toString())
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
        db.collection("users").document("0").set(user)
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
//                binding.tvFireStore.visibility = View.GONE
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
                binding.imgDashDay.visibility = View.VISIBLE
                binding.imgDashWeek.visibility = View.GONE
                binding.imgDashMonth.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
                binding.etStartDateFilter.visibility = View.GONE
                binding.etEndDateSearch.visibility = View.GONE
                binding.recyclerViewDemo6.visibility = View.VISIBLE
                DatePickerDialog(
                    requireContext(),
                    dateSetListenerDay,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            binding.tvFilterTime.id -> {
                fetchData()
                binding.imgDashWeek.visibility = View.VISIBLE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashMonth.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
                binding.etStartDateFilter.visibility = View.VISIBLE
                binding.etEndDateSearch.visibility = View.VISIBLE
            }
            binding.tvFilterMonth.id -> {
                fetchData()
                binding.imgDashMonth.visibility = View.VISIBLE
                binding.imgDashWeek.visibility = View.GONE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
                binding.etStartDateFilter.visibility = View.GONE
                binding.etEndDateSearch.visibility = View.GONE
                DatePickerDialog(
                    requireContext(),
                    dateSetListenerMonth,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()

            }
            binding.tvFilterYear.id -> {
                fetchData()
                binding.imgDashYear.visibility = View.VISIBLE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashMonth.visibility = View.GONE
                binding.imgDashWeek.visibility = View.GONE
                binding.etStartDateFilter.visibility = View.GONE
                binding.etEndDateSearch.visibility = View.GONE
                DatePickerDialog(
                    requireContext(),
                    dateSetListenerYear,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
    }


    private fun searchUserByYear(year: String) {
        val list = ArrayList<ModelDemo6>()
        val ref = db.collection("users")
        val query = ref.whereEqualTo("date", "20 Mar 2020, 12:00")
        //    val query = ref.whereIn("date", listOf("Jan"))
        ref.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document: QueryDocumentSnapshot in task.result) {
                    val allDate: String? = document.getString("date")
                    if (allDate!!.contains(year)) {
                        Log.i(
                            "TAGLIST",
                            " ${document.getString("date")} : ${document.getString("day")}"
                        )
                        val demo6 = document.toObject(ModelDemo6::class.java)
                        list.add(demo6)
//                        val demo6 = document.toObject(ModelDemo6::class.java)
//                        list.add(demo6)
                    }
//                    list.add(demo6)
//                    Log.i("Main", list.size.toString())
//                    Log.i("Main", document.getString("tittle").toString())
                }
                Log.i("Main", list.size.toString())
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

    private fun searchUserByMonth(month: String) {
        val list = ArrayList<ModelDemo6>()
        val ref = db.collection("users")
        val query = ref.whereEqualTo("date", "20 Mar 2020, 12:00")
        //    val query = ref.whereIn("date", listOf("Jan"))
        ref.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document: QueryDocumentSnapshot in task.result) {
                    val allDate: String? = document.getString("date")
                    if (allDate!!.contains(month)) {
                        Log.i(
                            "TAGLIST",
                            " ${document.getString("date")} : ${document.getString("day")}"
                        )
                        val demo6 = document.toObject(ModelDemo6::class.java)
                        list.add(demo6)
//                        val demo6 = document.toObject(ModelDemo6::class.java)
//                        list.add(demo6)
                    }
//                    list.add(demo6)
//                    Log.i("Main", list.size.toString())
//                    Log.i("Main", document.getString("tittle").toString())
                }
                Log.i("Main", list.size.toString())
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