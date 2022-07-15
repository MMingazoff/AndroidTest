package com.example.authpage.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DbManager(context: Context) {
    val DbHelper = DbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = DbHelper.writableDatabase
    }

    fun addUser(username: String, email: String, password: String){
        val values = ContentValues().apply {
            put(DbClass.COLUMN_NAME_USERNAME, username)
            put(DbClass.COLUMN_NAME_EMAIL, email)
            put(DbClass.COLUMN_NAME_PASSWORD, password)
        }
        db?.insert(DbClass.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun getUserData(email: String): ArrayList<String> {
        val data = ArrayList<String>()
        try {
            val query =
                "select * from ${DbClass.TABLE_NAME} where ${DbClass.COLUMN_NAME_EMAIL} = '$email'"
            val cursor = db?.rawQuery(query, null)
            with(cursor) {
                this?.moveToFirst()
                val username =
                    cursor?.getString(cursor.getColumnIndex(DbClass.COLUMN_NAME_USERNAME))
                        .toString()
                val email =
                    cursor?.getString(cursor.getColumnIndex(DbClass.COLUMN_NAME_EMAIL)).toString()
                val password =
                    cursor?.getString(cursor.getColumnIndex(DbClass.COLUMN_NAME_PASSWORD))
                        .toString()
                data.add(username)
                data.add(email)
                data.add(password)
            }
        } catch (e: Exception){
            data.add("")
            data.add("")
            data.add("")
        }
        return data
    }

    fun correctPassword(email: String, password: String): Boolean{
        val data = getUserData(email)
        return password == data[2]
    }

    fun closeDb(){
        DbHelper.close()
    }
}