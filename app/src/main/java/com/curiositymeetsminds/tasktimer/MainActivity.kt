package com.curiositymeetsminds.tasktimer

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val sortColumn = TasksContract.Columns.TASK_SORT_ORDER
        val projection = arrayOf(TasksContract.Columns.TASK_NAME, TasksContract.Columns.TASK_SORT_ORDER)

//        val cursor = contentResolver.query(TasksContract.CONTENT_URI, projection, null, null, sortColumn)
        val cursor = contentResolver.query(TasksContract.buildUriFromId(2), projection, null, null, sortColumn)
        Log.d(TAG, "******************************************************")

        cursor.use {
            while (it!!.moveToNext()) {
                with(it) {
//                    val id = getLong(0)
                    val name = getString(0)
//                    val description = getString(2)
                    val sortOrder = getString(1)
                    val result = "ID:, Name: $name, Description:, Sort Order: $sortOrder"
                    Log.d(TAG, result)
                }
            }

        }

        Log.d(TAG, "******************************************************")


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
