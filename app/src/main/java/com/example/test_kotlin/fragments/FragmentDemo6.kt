package com.example.test_kotlin.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.adapters.Demo6Adapter
import com.example.test_kotlin.databinding.FragmentDemo6Binding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class Demo6 : Fragment() {
    private lateinit var binding: FragmentDemo6Binding

    @SuppressLint("SimpleDateFormat")
    private val dateFormat: DateFormat = SimpleDateFormat("hh.mm aa")
    private val dateString: String = dateFormat.format(Date()).toString()
    private val user = hashMapOf(
        "time" to dateString,
        "first" to "Alan",
        "middle" to "Mathisson",
        "last" to "Turing",
        "born" to 1912
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
        fetchData()
        binding.etSearchDataFireStore.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                demo6Adapter.filter.filter(p0)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
//        val key: DocumentReference = db.collection("users").document()
//        val id = key.id

//        db.collection("11.34 AM").get().addOnSuccessListener { result ->
//            for (document in result) {
//                Log.i("Final", "${document.id}=>${document.data}")
//            }
////        }
//        }.addOnFailureListener {
//            Log.d("Final", "get failed with ", it)
//        }


    }

    private fun addUser() {
        val userReference1 = db.collection("users").document("1").set(user)
        db.collection("users").document("8").set(user)
        db.collection("users").document("9").set(user)
//        db.collection("users").document("10").set(user)
//        db.collection("users").document("11").set(user)
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
                binding.etSearchDataFireStore.visibility = View.GONE
                binding.recyclerViewDemo6.visibility = View.GONE
            } else if (it.isNotEmpty()) {
                binding.recyclerViewDemo6.visibility = View.VISIBLE
                binding.tvFireStore.visibility = View.GONE
                binding.etSearchDataFireStore.visibility = View.VISIBLE
                demo6Adapter = Demo6Adapter(it)
                binding.recyclerViewDemo6.adapter = demo6Adapter
                binding.recyclerViewDemo6.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerViewDemo6.setHasFixedSize(true)
                binding.pbLoadDataDemo6.visibility = View.GONE
                binding.tvProgressTextDemo6.visibility = View.GONE
            }

        }
    }
}