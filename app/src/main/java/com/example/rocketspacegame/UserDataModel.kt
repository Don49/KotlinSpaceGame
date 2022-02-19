package com.example.rocketspacegame

import android.util.Log

// Class that is the template for data that will be accessible from fragments. This class contains the users name and ClassId
class UserDataModel(name:String , classID : String)  {
  var name = ""
  var classId = ""

    init {
        this.name = name
        this.classId = classID
    }
}