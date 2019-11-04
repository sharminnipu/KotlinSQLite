package com.techhouseandroid.kotlinsqlite.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.techhouseandroid.kotlinsqlite.Model.Person

/**
 * Created by Nipu on 10/31/2018.
 */
class DBHelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VAR) {



    companion object {
        private val DATABASE_VAR=1
        private val DATABASE_NAME="myDbclass"

        private val TABLE_NAME="Person"
        private val Col_ID="Id"
        private val Col_NAME="Name"
        private val Col_EMAIL="Email"
        private val COL_PASSWORD="Password"
        private val COL_DATE="Date"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY:String=("CREATE TABLE $TABLE_NAME($Col_ID INTEGER PRIMARY KEY AUTOINCREMENT,$Col_NAME TEXT,$Col_EMAIL TEXT,$COL_PASSWORD TEXT,$COL_DATE TEXT)")

        db!!.execSQL(CREATE_TABLE_QUERY);


    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }



    val allPerson:List<Person>
    get(){

        val  lastPerson=ArrayList<Person>()
        val selectQuery="SELECT * FROM $TABLE_NAME"
        val db: SQLiteDatabase =this.writableDatabase
        val cursor:Cursor=db.rawQuery(selectQuery,null)

        if(cursor.moveToFirst()) {

            do {
                val person = Person()
                 person.id=cursor.getInt(cursor.getColumnIndex(Col_ID))
                person.name = cursor.getString(cursor.getColumnIndex(Col_NAME))
                person.email = cursor.getString(cursor.getColumnIndex(Col_EMAIL))
                person.password = cursor.getString(cursor.getColumnIndex(COL_PASSWORD))
                person.date = cursor.getString(cursor.getColumnIndex(COL_DATE))

                lastPerson.add(person)


            } while (cursor.moveToNext())


        }
        return lastPerson

    }

    fun addPerson(person: Person) {

          val db:SQLiteDatabase=this.writableDatabase
           val values=ContentValues()

        values.put(Col_NAME,person.name)
        values.put(Col_EMAIL,person.email)
        values.put(COL_PASSWORD,person.password)
        values.put(COL_DATE,person.date)

        db.insert(TABLE_NAME, null,values)

        db.close()


    }


    fun updatePerson(person: Person): Int {

        val db:SQLiteDatabase=this.writableDatabase
        val values=ContentValues()

        values.put(Col_NAME,person.name)
        values.put(Col_EMAIL,person.email)
        values.put(COL_PASSWORD,person.password)
        values.put(COL_DATE,person.date)

      return  db.update(TABLE_NAME,values, "$Col_NAME=?", arrayOf(person.name))

      //  db.close()


    }

    fun deletePerson(person: Person)
    {

        val db:SQLiteDatabase=this.writableDatabase

        db.delete(TABLE_NAME, "$Col_NAME=?", arrayOf(person.name))

        db.close()


    }

}