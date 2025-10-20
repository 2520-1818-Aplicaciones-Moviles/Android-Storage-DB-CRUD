package com.example.semana5_2.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.semana5_2.model.User
import com.example.semana5_2.model.openhelper.OpenHelper

class UserViewModel(): ViewModel() {
    // Usar List<User> inmutable para evitar mutaciones inesperadas en estado
    var listUser_: List<User> by mutableStateOf(listOf())

    /*
    * Includes a new user in the database
    */
    fun addUser(context: Context, user: User){
        val dbHelper = OpenHelper(context)
        dbHelper.newUser(user)
        showUsers(context)
    }

    /*
    * Reads all users from the database and updates the listUser_ variable
    */
    fun showUsers(context: Context){
        val dbHelper = OpenHelper(context)
        listUser_ = dbHelper.readUsers() // MutableList es asignable a List
    }

    /*
    * Updates an existing user in the database
    */
    fun updateUser(context: Context, user: User){
        val dbHelper = OpenHelper(context)
        if (dbHelper.updateUser(user)) {
            showUsers(context)
        }
    }

    /*
    * Deletes a user by id from the database
    */
    fun deleteUser(context: Context, id: Int){
        val dbHelper = OpenHelper(context)
        if (dbHelper.deleteUser(id)) {
            showUsers(context)
        }
    }
}