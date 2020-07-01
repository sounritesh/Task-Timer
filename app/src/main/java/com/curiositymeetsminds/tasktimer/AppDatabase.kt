package com.curiositymeetsminds.tasktimer

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * Basic database class for the application
 *
 * The only class that should use this is [AppProvider].
 */

private const val TAG = "AppDatabase"

private const val DATABASE_NAME = "TaskTimer.db"
private const val DATABASE_VERSION = 1

//internal keyword is used as this class is only supposed to be used by the AppProvider class which is within the app package
internal class AppDatabase private constructor (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

//    this init is not recommended and should not be added to the production app
//    it's fine for debugging purposes
    init {
        Log.d(TAG, "AppDatabase: initialising")
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "onCreate: starts")

//        CREATE TABLE Tasks (_id INTEGER PRIMARY KEY NOT NULL, Name TEXT NOT NULL, Description TEXT, SortOrder INTEGER)
        val sSQL = """CREATE TABLE ${TasksContract.TABLE_NAME} (
        ${TasksContract.Columns.ID} INTEGER PRIMARY KEY NOT NULL, 
        ${TasksContract.Columns.TASK_NAME} TEXT NOT NULL, 
        ${TasksContract.Columns.TASK_DESCRIPTION} TEXT, 
        ${TasksContract.Columns.TASK_SORT_ORDER} INTEGER
        );
        """.replaceIndent(" ")
        Log.d(TAG, sSQL)

        db.execSQL(sSQL)
        Log.d(TAG, "onCreate: ends")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}