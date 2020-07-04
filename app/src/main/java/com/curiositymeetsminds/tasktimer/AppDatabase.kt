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
        Log.d(TAG, "onUpgrade: starts")
        when (oldVersion) {
            1 -> {
//                upgrade logic from version 1
            }
            else -> throw IllegalStateException("onUpgrade() with unknown newVersion: $newVersion")
        }
    }

//    companion object {
//        This function uses a double checked lock algorithm
//        If the instance is not null, the instance is returned
//        Else the variable is locked
//        and the function block  that follows is executed
//        wherein again null check is performed and if null, a new instance of AppDatabase is returned
//        This code however may introduce some bugs so we instead use a class to make a singleton class for us
//        this is attributed to the fact that android is a multithreaded framework and hence to keep check on all thread is difficult
//        Even experts sometimes fail to get multithreaded programming wrong

//        @Volatile
//        private var instance: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase =
//            instance?: synchronized(this) {
//                instance ?: AppDatabase(context).also { instance = it }
//            }


//    }

    companion object: SingletonHolder<AppDatabase, Context>(::AppDatabase)
}