package com.example.semana5_2.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.semana5_2.model.User
import com.example.semana5_2.model.openhelper.OpenHelper

class UserViewModel(): ViewModel() {
    var listUser_ : MutableList<User> by mutableStateOf(arrayListOf())

    /*
    * Includes a new user in the database
    */
    fun addUser(context: Context, user: User){
        var dbHelper = OpenHelper(context)
        dbHelper.newUser(user)
    }

    /*
    * Reads all users from the database and updates the listUser_ variable
    */
    fun showUsers(context: Context){
        var dbHelper = OpenHelper(context)
        listUser_ = dbHelper.readUsers()
    }

}