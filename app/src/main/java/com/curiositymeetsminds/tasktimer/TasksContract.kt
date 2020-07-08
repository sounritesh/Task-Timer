package com.curiositymeetsminds.tasktimer

import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns

/**
 * This class is used to define the constants that will be needed to access the data of our Tasks table
*/
object TasksContract {

//    const keyword is valid inside an object unlike classes
//    internal keyword is used because only our app will use these to access the data
    internal const val TABLE_NAME = "Tasks"

    /**
     * URI to access the tasks table
     */
    val CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME)

    const val CONTENT_TYPE = "vnd.android.cursor.dir/vnd.$CONTENT_AUTHORITY.$TABLE_NAME"
    const val CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.$CONTENT_AUTHORITY.$TABLE_NAME"

//    an object inside an object is like a companion object but the companion keyword
//    is not applicable inside an object
    object Columns {
//    Tasks fields
        const val ID = BaseColumns._ID // we can also simply use "_id", however this constant is provided by google through BaseColumns interface
        const val TASK_NAME = "Name"
        const val TASK_DESCRIPTION = "Description"
        const val TASK_SORT_ORDER = "SortOrder"
    }

    fun getId(uri: Uri): Long {
        return ContentUris.parseId(uri)
    }

    fun buildUriFromId(id: Long): Uri {
        return  ContentUris.withAppendedId(CONTENT_URI, id)
    }
}