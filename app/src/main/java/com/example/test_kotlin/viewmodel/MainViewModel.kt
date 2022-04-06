package com.example.test_kotlin.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.test_kotlin.FirebaseHelper
import com.example.test_kotlin.R
import com.example.test_kotlin.adapters.LayoutHolderAdapter
import com.example.test_kotlin.models.FirestoreModelItems
import com.example.test_kotlin.models.ModelDemo2
import com.example.test_kotlin.models.ModelLayoutHolder
import com.example.test_kotlin.room.UserRepository
import com.example.test_kotlin.room.Users
import com.example.test_kotlin.room.UsersDatabase
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var liveData: MutableLiveData<ArrayList<ModelLayoutHolder>> = MutableLiveData()
    private var demo2LiveData: MutableLiveData<ArrayList<ModelDemo2>> = MutableLiveData()
    private val readAllData: LiveData<List<Users>>
    private val repository: UserRepository
    private var tempList: ArrayList<FirebaseHelper> = ArrayList()
    private val firebaseData: MutableLiveData<ArrayList<FirebaseHelper>> = MutableLiveData()
    val fireStoreData: MutableLiveData<ArrayList<FirestoreModelItems>> = MutableLiveData()

    init {
        val userDao = UsersDatabase.getDatabaseObj(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun getDataFireStore() {
        val tempList: ArrayList<FirestoreModelItems> = ArrayList()
        val db = Firebase.firestore
        tempList.clear()
        db.collection("data").get()
            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.i("Final", "${document.id}=>${document.data}")
//                    Log.i("Final", "The Data Is ${document.data}")
//                    Log.i("Final", "The Date Is ${document.data["date"].toString()}")
//                    val data = document.toObject(FirestoreModelItems::class.java)
//                    Log.i("MAIN_DATA",data.toString())
//                    tempList.add(data)
//                }
                val data = result.toObjects(FirestoreModelItems::class.java)
                tempList.addAll(data)
                Log.i("Final", result.toString())
                fireStoreData.value = tempList
            }.addOnFailureListener {
                Log.d("Final", "get failed with ", it)
            }
    }

    fun getDataFirebase(): MutableLiveData<ArrayList<FirebaseHelper>> {
        val dataBase = FirebaseDatabase.getInstance()
        val databaseReference: DatabaseReference = dataBase.getReference("Date")
        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val yourDataDateValue = dataSnapShot.child("date").value.toString()
                    val yourDataDateKey = dataSnapShot.key.toString()
                    Log.i("BEFORE", dataSnapShot.key.toString())
                    tempList.add(
                        FirebaseHelper(
                            yourDataDateKey,
                            yourDataDateValue
                        )
                    )
                    firebaseData.value = tempList
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(getApplication(), "CanCelled", Toast.LENGTH_SHORT).show()
            }
        })
        return firebaseData
    }

    fun setDataDemo2UsingViewModel(): MutableLiveData<ArrayList<ModelDemo2>> {
        val dataList = ArrayList<ModelDemo2>()
        dataList.add(
            ModelDemo2("1")
        )
        dataList.add(
            ModelDemo2("2")
        )
        dataList.add(
            ModelDemo2("3")
        )
        dataList.add(
            ModelDemo2("4")
        )
        dataList.add(
            ModelDemo2("5")
        )
        dataList.add(
            ModelDemo2("6")
        )
        dataList.add(
            ModelDemo2("7")
        )
        demo2LiveData.value = dataList
        return demo2LiveData
    }

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
                R.drawable.businessman1,
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
                R.drawable.man1,
                "Jimmy",
                "Chow"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.man1,
                "Raj",
                "Jain"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.man1,
                "John",
                "Dao"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.person,
                "Jimmy",
                "Chow"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.person,
                "Raj",
                "Jain"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.person,
                "John",
                "Dao"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.man1,
                "Jimmy",
                "Chow"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.man1,
                "Raj",
                "Jain"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.man1,
                "John",
                "Dao"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_FIRST_VIEW,
                R.drawable.woman,
                "John",
                "Dao"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_SECOND_VIEW,
                R.drawable.woman,
                "John",
                "Dao"
            )
        )
        dataList.add(
            ModelLayoutHolder(
                LayoutHolderAdapter.THE_THIRD_VIEW,
                R.drawable.woman,
                "John",
                "Dao"
            )
        )
        liveData.value = dataList
        return liveData
    }


    fun addUser(users: Users) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(users)
        }
    }

    fun checkEmail(emailAddress: String): Boolean {
        return repository.checkEmail(emailAddress)
    }

    fun checkUser(emailAddress: String, password: String): Boolean {
        return repository.checkUser(emailAddress, password)
    }

    fun getDetails(): LiveData<List<Users>> {
        return repository.getDetails()
    }

    fun getRequested(ID: Int): LiveData<Users> {
        return repository.getRequested(ID)
    }

    fun updateData(users: Users) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(users)
        }
    }

    fun deleteUser(users: Users) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(users)
        }
    }

}