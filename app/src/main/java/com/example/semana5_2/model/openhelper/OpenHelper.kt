package com.example.semana5_2.model.openhelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.semana5_2.model.User

class OpenHelper(context: Context): SQLiteOpenHelper(
    context, "DBUsers", null, 1
) {
    /*
    * This function is executed the first time the database is created
    * If the database already exists, this function will not be executed
    * */
    override fun onCreate(p0: SQLiteDatabase?) {
        val query="CREATE TABLE Users(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER, email TEXT)"
        p0!!.execSQL(query)
        //p0?.execSQL(query)
    }

    /*
    * This function is executed when the database version is updated
    * If the database version is not updated, this function will not be executed
    * */
    override fun onUpgrade(
        p0: SQLiteDatabase?,
        p1: Int,
        p2: Int
    ) {
        val query = "DROP TABLE IF EXISTS Users"
        p0?.execSQL(query)
        onCreate(p0)
    }

    /*
    * This function is used to insert a new user into the database
    * It receives a User object as a parameter
    * and the writableDatabase property to get a writable database
    * */
    fun newUser(_user: User){
        val data = ContentValues()
        data.put("name", _user.name)
        data.put("age", _user.age)
        data.put("email", _user.email)
        this.writableDatabase.insert("Users", null, data)
    }

    /*
    * This function is used to read all users from the database
    * It returns a list of User objects
    * It uses the readableDatabase property to get a readable database
    * */
    fun readUsers(): MutableList<User> {

        val db = this.readableDatabase
        val query = "SELECT * FROM Users"
        val result = db.rawQuery(query, null)
        val listUser = mutableListOf<User>()

        if (result.moveToFirst()) {
            do {
                listUser.add(
                    User(
                        result.getInt(0),
                        result.getString(1),
                        result.getInt(2),
                        result.getString(3)
                    )
                )
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return listUser
    }
}