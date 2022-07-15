package com.example.authpage.db

import android.provider.BaseColumns
import java.sql.DataTruncation

object DbClass {
    const val TABLE_NAME = "users"
    const val COLUMN_NAME_USERNAME = "username"
    const val COLUMN_NAME_EMAIL = "email"
    const val COLUMN_NAME_PASSWORD = "password"


    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "MyDb"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER  PRIMARY KEY, $COLUMN_NAME_USERNAME TEXT, " +
            "$COLUMN_NAME_EMAIL TEXT, " +
            "$COLUMN_NAME_PASSWORD TEXT)"

    const val SQL_DELETE_TABLE = "DROP IF EXISTS $TABLE_NAME"
}