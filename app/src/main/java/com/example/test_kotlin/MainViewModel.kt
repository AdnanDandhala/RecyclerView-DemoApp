package com.example.test_kotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_kotlin.adapters.LayoutHolderAdapter
import com.example.test_kotlin.models.ModelLayoutHolder

class MainViewModel : ViewModel() {
    private var liveData: MutableLiveData<ArrayList<ModelLayoutHolder>> = MutableLiveData()

    fun setDataUsingViewModel(): MutableLiveData<ArrayList<ModelLayoutHolder>> {
        val dataList = ArrayList<ModelLayoutHolder>()
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
        liveData.value = dataList
        return liveData
    }
}