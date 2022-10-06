package com.mostefalmr.qr_project


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast

class DBManager {
    //DB name
    val dbName = "uikmanagment"
    // DB tables
        val dbTable = "Student"
        val dbTable2 = "Note"
        val dbTable3 = "Presence"
    // DB Table "Student" columns
    val colID = "StudID" //primary key
    val colFName = "FName"//TEXT
    val colLName = "LName" // TEXT
    val colBday = "Bday" // DATE
    val colCycle = "Cycle" // INTEGER
    val colYear = "Year" // INTEGER
    val colMajor = "Major" // TEXT
    val colSection = "Section" // INTEGER
    val colGroup = "group" // INTEGER



    
//making crud operations 



    val dbVersion = 1
    val SQLCreateTable = "CREATE TABLE IF NOT EXISTS "+dbTable+" ("+colID+" INTEGER PRIMARY KEY , "+
            colFName+" TEXT, "+colLName+" TEXT"+colBday+" DATE"+colCycle+" INTEGER"+colYear+" INTEGER"+colMajor+" TEXT"+colSection+" INTEGER" +
            colGroup+" INTEGER);"


    val SQLCreateTable1 = "CREATE TABLE IF NOT EXISTS "+dbTable+" ("+colID+" INTEGER PRIMARY KEY , "+
            colFName+" TEXT, "+colLName+" TEXT"+colBday+" DATE"+colCycle+" INTEGER"+colYear+" INTEGER"+colMajor+" TEXT"+colSection+" INTEGER" +
            colGroup+" INTEGER);"

    val SQLCreateTable2 = "CREATE TABLE IF NOT EXISTS "+dbTable+" ("+colID+" INTEGER PRIMARY KEY , "+
            colFName+" TEXT, "+colLName+" TEXT"+colBday+" DATE"+colCycle+" INTEGER"+colYear+" INTEGER"+colMajor+" TEXT"+colSection+" INTEGER" +
            colGroup+" INTEGER);"

    var sqlDB:SQLiteDatabase?= null


    constructor(context: Context) {
        val db = DatabaseHelperNotes(context)
        sqlDB = db.writableDatabase
    }

    inner class DatabaseHelperNotes: SQLiteOpenHelper {
        var context:Context?= null
        constructor(context:Context):super(context, dbName, null, dbVersion){
            this.context = context
        }
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(SQLCreateTable)
            db!!.execSQL(SQLCreateTable1)
            db!!.execSQL(SQLCreateTable2)
            Toast.makeText(this.context,"DataBases are Created",Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table IF EXISTS "+dbTable)

        }
    }


    fun insertStd(value: ContentValues):Long{
        val ID = sqlDB!!.insert(dbTable,"",value)
        return ID
    }
    fun insertNote(value: ContentValues):Long{
        val ID = sqlDB!!.insert(dbTable2,"",value)
        return ID
    }
    fun insertPres(value: ContentValues):Long{
        val ID = sqlDB!!.insert(dbTable3,"",value)
        return ID
    }
    fun queryStd(projection:Array<String>,selection:String, selectionArg:Array<String>, sortOrder:String): Cursor {
        val q = SQLiteQueryBuilder()
        q.tables = dbTable
        val cursor = q.query(sqlDB, projection, selection,  selectionArg,null, null, sortOrder)
        return cursor
    }
    fun queryNote(projection:Array<String>,selection:String, selectionArg:Array<String>, sortOrder:String): Cursor {
        val q = SQLiteQueryBuilder()
        q.tables = dbTable2
        val cursor = q.query(sqlDB, projection, selection,  selectionArg,null, null, sortOrder)
        return cursor
    }
    fun queryPres(projection:Array<String>,selection:String, selectionArg:Array<String>, sortOrder:String): Cursor {
        val q = SQLiteQueryBuilder()
        q.tables = dbTable3
        val cursor = q.query(sqlDB, projection, selection,  selectionArg,null, null, sortOrder)
        return cursor
    }
    fun deleteStd(selection:String, selectionArg:Array<String>):Int{
        val count = sqlDB!!.delete(dbTable,selection,selectionArg)
        return count
    }
    fun deleteNote(selection:String, selectionArg:Array<String>):Int{
        val count = sqlDB!!.delete(dbTable2,selection,selectionArg)
        return count
    }
    fun deletePres(selection:String, selectionArg:Array<String>):Int{
        val count = sqlDB!!.delete(dbTable3,selection,selectionArg)
        return count
    }
    fun updateStd(values: ContentValues, selection: String, selectionArg: Array<String>):Int{
        val count = sqlDB!!.update(dbTable, values, selection, selectionArg)
        return count
    }
    fun updateNote(values: ContentValues, selection: String, selectionArg: Array<String>):Int{
        val count = sqlDB!!.update(dbTable2, values, selection, selectionArg)
        return count
    }
    fun updatePres(values: ContentValues, selection: String, selectionArg: Array<String>):Int{
        val count = sqlDB!!.update(dbTable3, values, selection, selectionArg)
        return count
    }
}