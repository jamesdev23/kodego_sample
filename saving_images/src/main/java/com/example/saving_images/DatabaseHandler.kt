package com.example.saving_images

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler (context: Context) : SQLiteOpenHelper(context,DATABASENAME,null,DATABASEVERSION){

    companion object {
        private val DATABASEVERSION = 5
        private val DATABASENAME = "studentdatabase"

        val TABLE_IMAGES = "table_images"
        val TABLE_IMAGES_ID = "id"
        val TABLE_IMAGES_DATA = "data"

        val TABLE_IMAGES_TEXT = "table_images"
        val TABLE_IMAGES_TEXT_ID = "id"
        val TABLE_IMAGES_TEXT_NAME = "name"
        val TABLE_IMAGES_TEXT_DATA = "data"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_IMAGES =
            "CREATE TABLE $TABLE_IMAGES " +
                "($TABLE_IMAGES_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$TABLE_IMAGES_DATA BLOB)"


        db?.execSQL(CREATE_TABLE_IMAGES)

        val CREATE_TABLE_IMAGES_TEXT =
            "CREATE TABLE $TABLE_IMAGES_TEXT " +
                    "($TABLE_IMAGES_TEXT_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$TABLE_IMAGES_TEXT_NAME TEXT, " +
                    "$TABLE_IMAGES_TEXT_DATA TEXT)"
        db?.execSQL(CREATE_TABLE_IMAGES_TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_IMAGES")
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_IMAGES_TEXT")
        onCreate(db)
    }

}