package com.example.test_kotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var liveData: MutableLiveData<ArrayList<ModelLayoutHolder>> = MutableLiveData()
    private var dataList = ArrayList<ModelLayoutHolder>()

    fun add(): MutableLiveData<ArrayList<ModelLayoutHolder>> {
        return liveData
    }

    fun setDataUsingViewModel() {
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.businessman1,
                "Jimmy",
                "Chow"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.bussinessman2,
                "Raj",
                "Jain"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.businessman1,
                "John",
                "Dao"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.bussinessman2,
                "Men",
                "Sent"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.bussinessman2,
                "Rahul",
                "Kumar"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.bussinessman2,
                "Johny",
                "Deep"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.man,
                "Rahul",
                "Kumar"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.bussinessman2,
                "Nikhil",
                "Sharma"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.man,
                "Sample",
                "Pro"
            )
        )
        liveData.value = dataList
    }
}