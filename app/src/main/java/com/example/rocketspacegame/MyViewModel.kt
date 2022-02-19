package com.example.rocketspacegame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
// Class to pass information between fragments. This class gets the users name and class(secondary school)
class MyViewModel : ViewModel() {
    val myLiveModel = MutableLiveData<UserDataModel>()

    init {

        myLiveModel.value = UserDataModel("jeff" , "2b")

    }

}