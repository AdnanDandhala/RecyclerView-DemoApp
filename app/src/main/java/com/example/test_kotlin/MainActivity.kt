package com.example.test_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
    }

    private fun setData() {
        val myDataList = ArrayList<LayoutHolder>()
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.businessman1,
                "John",
                "Deo"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.bussinessman2,
                "Jack",
                "B"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.businessman1,
                "Sam",
                "Set"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.bussinessman2,
                "Agro",
                "Mas"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.bussinessman2,
                "Text-1",
                "Text-2"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.bussinessman2,
                "Text-1",
                "Text-2"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.man,
                "Text-1",
                "Text-2"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.bussinessman2,
                "Text-1",
                "Text-2"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.man,
                "Text-1",
                "Text-2"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.person,
                "Text-1",
                "Text-2"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.person,
                "Text-1",
                "Text-2"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.person,
                "Text-1",
                "Text-2"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.woman,
                "Text-1",
                "Text-2"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.woman,
                "Text-1",
                "Text-2"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.woman,
                "Text-1",
                "Text-2"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.man1,
                "Text-1",
                "Text-2"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.man1,
                "Text-1",
                "Text-2"
            )
        )
        myDataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.man1,
                "Text-1",
                "Text-2"
            )
        )
        val adapter = LayoutHolderAdapter(this, myDataList)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.adapter = adapter
    }
}