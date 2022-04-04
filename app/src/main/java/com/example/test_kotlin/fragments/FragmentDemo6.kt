package com.example.test_kotlin.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
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
//        addUser()
        fetchData()
        binding.toolbarDemo6.setNavigationOnClickListener {
            findNavController().navigate(R.id.fragmentFirstScreen2)
            findNavController().popBackStack(R.id.fragmentDemo6, inclusive = true)
        }
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
        val dateFormat = SimpleDateFormat("EEE", Locale.US)
        val asWeek = dateFormat.format(dayOfMonth)
        Log.i("CURRENT_DATE", " The Day Is $asWeek")
        cal.time = Date()
        val day = cal[Calendar.DAY_OF_MONTH]
        Log.i("CURRENT_DATE", day.toString())
        searchUserByDay(asWeek)
    }

    private fun searchUserByDay(day: String) {
        val list = ArrayList<ModelDemo6>()
        Log.i("ALL_FIELD", "The Passed Day Is $day")
        val ref = db.collection("users")
        ref.whereEqualTo("day", "Monday").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document: QueryDocumentSnapshot in task.result) {
                    val demo6 = document.toObject(ModelDemo6::class.java)
                    list.add(demo6)
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

    private val dateSetListenerMonth =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateMonthInView(year, monthOfYear, dayOfMonth)
        }

    private fun updateMonthInView(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        Log.i("Calender", "The Year Is $year")
        Log.i("Calender", "The Month Number ${monthOfYear + 1}")
        Log.i("Calender", "The Date Is $dayOfMonth")
        when (monthOfYear + 1) {
            1 -> {
                Log.i("Calender", "Jan")
                searchUserByMonth("January")
            }
            2 -> {
                Log.i("Calender", "Feb")
                searchUserByMonth("February")
            }
            3 -> {
                Log.i("Calender", "Mar")
                searchUserByMonth("March")
            }
            4 -> {
                Log.i("Calender", "Apr")
                searchUserByMonth("April")
            }
            5 -> {
                Log.i("Calender", "May")
                searchUserByMonth("May")
            }
            6 -> {
                Log.i("Calender", "Jun")
                searchUserByMonth("June")
            }
            7 -> {
                Log.i("Calender", "July")
                searchUserByMonth("July")
            }
            8 -> {
                Log.i("Calender", "Aug")
                searchUserByMonth("August")
            }
            9 -> {
                Log.i("Calender", "Sep")
                searchUserByMonth("September")
            }
            10 -> {
                Log.i("Calender", "Oct")
                searchUserByMonth("October")
            }
            11 -> {
                Log.i("Calender", "Nov")
                searchUserByMonth("November")
            }
            12 -> {
                Log.i("Calender", "Dec")
                searchUserByMonth("December")
            }
        }
        binding.recyclerViewDemo6.visibility = View.VISIBLE
    }

    private fun searchUserByMonth(month: String) {
        val list = ArrayList<ModelDemo6>()
        Log.i("ALL_FIELD", "The Passed Month Is $month")
        var passedMonth = db.collection("users").document("date")
        val ref =
            db.collection("users")
                .whereLessThanOrEqualTo("date", searchUser())
        ref.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document: QueryDocumentSnapshot in task.result) {
                    val demo6 = document.toObject(ModelDemo6::class.java)
                    list.add(demo6)
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

    private fun searchUser(): Date {
        var certainDate = Date()
        val rootRef = db.collection("users")
        val usersRef = rootRef.document("date")
        usersRef.get().addOnCompleteListener { task ->
            val document = task.result
            if (document.exists()) {
                certainDate = document.getDate("date")!!
                //Do what you need to do with your Date object
            }
        }
        Log.i("ALL_FIELD","The TimeStamp Date Is $certainDate")
        return certainDate
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

    private fun searchUserByYear(year: String) {
        val list = ArrayList<ModelDemo6>()
        val ref = db.collection("users")
        Log.i("ALL_FIELD", "The Passed Year Is $year")
        ref.whereEqualTo("date", year)
            .get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document: QueryDocumentSnapshot in task.result) {
                        Log.i("TAG_YEAR", document.toString())
                        val demo6 = document.toObject(ModelDemo6::class.java)
                        list.add(demo6)
                    }
                    Log.i("TAG_YEAR", list.size.toString())
                    binding.recyclerViewDemo6.adapter = Demo6Adapter(list)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Query Failed. Check Logs",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

//        ref.get().addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                for (document: QueryDocumentSnapshot in task.result) {
//                    val allDate: String? = document.getString("date")
//                    if (allDate!!.contains("April 3,2020 at 1:20:13 PM UTC+5:30")) {
//                        Log.i(
//                            "TAG_LIST",
//                            " ${document.getString("date")} : ${document.getString("day")}"
//                        )
//                        val demo6 = document.toObject(ModelDemo6::class.java)
//                        list.add(demo6)
//                    }
//                }
//                Log.i("Main", list.size.toString())
//                binding.recyclerViewDemo6.adapter = Demo6Adapter(list)
//            } else {
//                Toast.makeText(
//                    requireContext(),
//                    "Query Failed. Check Logs",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
    }

    private val dateSetListenerWeek =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateWeekInView(dayOfMonth)
        }

    private fun updateWeekInView(dayOfMonth: Int) {
        searchUserByWeek(dayOfMonth.toString())
    }

    private fun searchUserByWeek(dayOfMonth: String) {
        val list = ArrayList<ModelDemo6>()
        val ref =
            db.collection("users")
                .whereLessThanOrEqualTo("date", dayOfMonth)
        ref.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document: QueryDocumentSnapshot in task.result) {
                    val demo6 = document.toObject(ModelDemo6::class.java)
                    list.add(demo6)
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
        val user = hashMapOf(
            "date" to Date(),
            "tittle" to "It has survived not only five centuries, but also the leap into electronic"
        )
        val userReference1 = db.collection("users").document("1").set(user)
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
                binding.recyclerViewDemo6.visibility = View.GONE
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
                fetchData()
                binding.imgDashDay.visibility = View.VISIBLE
                binding.imgDashWeek.visibility = View.GONE
                binding.imgDashMonth.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
                binding.recyclerViewDemo6.visibility = View.VISIBLE
                DatePickerDialog(
                    requireContext(),
                    dateSetListenerDay,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            binding.tvFilterWeek.id -> {
                fetchData()
                binding.imgDashWeek.visibility = View.VISIBLE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashMonth.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
                DatePickerDialog(
                    requireContext(),
                    dateSetListenerWeek,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            binding.tvFilterMonth.id -> {
                fetchData()
                binding.imgDashMonth.visibility = View.VISIBLE
                binding.imgDashWeek.visibility = View.GONE
                binding.imgDashDay.visibility = View.GONE
                binding.imgDashYear.visibility = View.GONE
                DatePickerDialog(
                    requireContext(),
                    dateSetListenerMonth,
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
                DatePickerDialog(
                    requireContext(),
                    dateSetListenerYear,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
    }
}