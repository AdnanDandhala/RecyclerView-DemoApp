package com.example.test_kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch(Dispatchers.IO) {
            delay(3000L)
            withContext(Dispatchers.Main) {
                repeat(1) {
                    setData()
                }
                delay(3000L)
            }
        }
    }

    private fun setData() {
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.add().observe(this, Observer {
            if (it != null) {
                val adapter = LayoutHolderAdapter(applicationContext, it)
                binding.mainRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
                binding.mainRecyclerView.adapter = adapter
            } else {
                Toast.makeText(this, "Some Error Occur", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.setDataUsingViewModel()
    }

}
