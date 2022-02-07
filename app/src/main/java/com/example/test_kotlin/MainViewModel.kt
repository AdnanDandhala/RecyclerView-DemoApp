package com.example.test_kotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var liveData: MutableLiveData<ArrayList<LayoutHolder>> = MutableLiveData()
    private var dataList = ArrayList<LayoutHolder>()

    fun add(): MutableLiveData<ArrayList<LayoutHolder>> {
        return liveData
    }

    fun setDataUsingViewModel() {
        dataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.businessman1,
                "Jimmy",
                "Chow"
            )
        )
        dataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.bussinessman2,
                "Raj",
                "Jain"
            )
        )
        dataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.businessman1,
                "John",
                "Dao"
            )
        )
        dataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.bussinessman2,
                "Men",
                "Sent"
            )
        )
        dataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.bussinessman2,
                "Rahul",
                "Kumar"
            )
        )
        dataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.bussinessman2,
                "Johny",
                "Deep"
            )
        )
        dataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.man,
                "Rahul",
                "Kumar"
            )
        )
        dataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.bussinessman2,
                "Nikhil",
                "Sharma"
            )
        )
        dataList.add(
            LayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.man,
                "Sample",
                "Pro"
            )
        )
        liveData.value = dataList
    }
}