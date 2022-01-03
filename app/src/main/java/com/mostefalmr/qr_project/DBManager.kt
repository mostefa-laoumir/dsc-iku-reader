package com.mostefalmr.qr_project


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast

class DBManager(context: Context, factory: SQLiteDatabase.CursorFactory?)  :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
        var context:Context = context
        // below is the method for creating a database by a sqlite query
        override fun onCreate(db: SQLiteDatabase) {
            // below is a sqlite query, where column names
            // along with their data types is given
            val query = ("CREATE TABLE " + TABLE_NAME + " ("
                    + colID + " INTEGER PRIMARY KEY, " +
                    colFName + " TEXT," +
                    colLName + " TEXT," +
                    colYear + " TEXT," +
                    colSection + " TEXT," +
                    colGroup + " TEXT," +
                    colNote + " REAL DEFAULT 0," +
                    colCompt+ " INTEGER DEFAULT 0" + ")")

            val query2 = ("CREATE TABLE " + TABLE_NAME3 + " (" +
                    colIDP + " INTEGER," +
                    colPresDay + " DATE DEFAULT (datetime('now','localtime'))," +
                    "FOREIGN KEY ("+colIDP+") REFERENCES "+TABLE_NAME+" ("+colID+"));")


            // we are calling sqlite
            // method for executing our query
            db.execSQL(query)
            db.execSQL(query2)
            Toast.makeText(context,query, Toast.LENGTH_LONG).show()
            Toast.makeText(context,query2, Toast.LENGTH_LONG).show()


        }

        override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
            // this method is to check if table already exists
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3)
            onCreate(db)
        }

        // This method is for adding data in our database
        fun addStudent(id: Int, fname: String, lname: String, year: String, section: String, group: String){

            // below we are creating
            // a content values variable
            val values = ContentValues()

            // we are inserting our values
            // in the form of key-value pair
            values.put(colID, id)
            values.put(colFName, fname)
            values.put(colLName, lname)
            values.put(colYear, year)
            values.put(colSection, section)
            values.put(colGroup, group)

            // here we are creating a
            // writable variable of
            // our database as we want to
            // insert value in our database
            val db = this.writableDatabase

            // all values are inserted into database
            db.insert(TABLE_NAME, null, values)

            // at last we are
            // closing our database
            db.close()
        }

        // This method is for adding data in our database
        fun addPresence(id:Int){

            // below we are creating
            // a content values variable
            val values = ContentValues()

            // we are inserting our values
            // in the form of key-value pair
            values.put(colIDP, id)

            // here we are creating a
            // writable variable of
            // our database as we want to
            // insert value in our database
            val db = this.writableDatabase

            // all values are inserted into database
            db.insert(TABLE_NAME3, null, values)

            // at last we are
            // closing our database
            db.close()
        }
        // This method is for adding data in our database
        fun addNote(id:Int, note : Float){

            // below we are creating
            // a content values variable
            val values = ContentValues()

            // we are inserting our values
            // in the form of key-value pair
            values.put(colID, id)
            values.put(colNote, note)

            val db = this.writableDatabase

            // all values are inserted into database
            db.insert(TABLE_NAME, null, values)

            // at last we are
            // closing our database
            db.close()
        }


        // below method is to get
        // all data from our database
        fun getStudent(id:String): Cursor? {

            // here we are creating a readable
            // variable of our database
            // as we want to read value from it
            val db = this.readableDatabase

            // below code returns a cursor to
            // read data from the database
            return db.rawQuery("SELECT * FROM " + TABLE_NAME+" WHERE "+colID+"= ?", arrayOf(id))


        }
        fun getPresence(id: String): Cursor? {

            // here we are creating a readable
            // variable of our database
            // as we want to read value from it
            val db = this.readableDatabase

            // below code returns a cursor to
            // read data from the database
            return db.rawQuery("SELECT "+ colPresDay+" FROM " + TABLE_NAME3+" WHERE "+ colIDP+"= ?", arrayOf(id))

        }
        fun getNote(id:String): Cursor? {

            // here we are creating a readable
            // variable of our database
            // as we want to read value from it
            val db = this.readableDatabase

            // below code returns a cursor to
            // read data from the database
            return db.rawQuery("SELECT * FROM " + TABLE_NAME+" WHERE StudID = ?", arrayOf(id))

        }


        // Get the count
        fun getCount(id: Int): Int {
            val database = this.readableDatabase
            var cursor = database.rawQuery("SELECT "+ colCompt+" FROM "+ TABLE_NAME+" WHERE "+ colID+"="+id,null)
            cursor.moveToFirst()
            return cursor!!.getInt(0)
        }


        // Increment  by 1
        fun updateCount(id: Int, count:Int) {
            val database = this.writableDatabase

            val values = ContentValues()
            values.put(colCompt, count)
            database.update(
                TABLE_NAME,
                values,
                colID + " = ?",
                arrayOf(id.toString())
            )
            database.close()
        }


        fun updateNote(id: Int, note:Float) {
            val database = this.writableDatabase
            val values = ContentValues()
            values.put(colNote, note)
            database.update(
                TABLE_NAME,
                values,
                colID + " = ?",
                arrayOf(id.toString())
            )
            database.close()
        }

        companion object{



            // here we have defined variables for our database

            // below is variable for database name
            private val DATABASE_NAME = "bdd"

            // below is the variable for database version
            private val DATABASE_VERSION = 1

            // below is the variable for table name
            val TABLE_NAME = "Student"
            val TABLE_NAME3 = "Presence"


            val colID = "StudID" //primary key
            val colFName = "FName"//TEXT
            val colLName = "LName" // TEXT
            val colYear = "Annee" // TEXT
            val colSection = "Section" // TEXT
            val colGroup = "Groupe" // TEXT
            val colCompt = "PresCount"
            val colNote = "Note"
            // DB Table "Presence" columns

            val colIDP = "ID" //foreign key
            val colPresDay = "PresDate"
            // Integer





            // DB Table "Student" columns
        }
}