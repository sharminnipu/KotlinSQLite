package com.techhouseandroid.kotlinsqlite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.techhouseandroid.kotlinsqlite.Adapter.ListPersonAdaptar
import com.techhouseandroid.kotlinsqlite.DBHelper.DBHelper
import com.techhouseandroid.kotlinsqlite.Model.Person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    internal lateinit var db:DBHelper

    internal var lastperson:List<Person> = ArrayList<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        db = DBHelper(this)

        refreshData()


        add.setOnClickListener{

            val person=Person(

                    user_name.text.toString(),
                    user_email.text.toString(),
                    user_password.text.toString(),
                    user_date.text.toString()


            )

            db.addPerson(person)

            refreshData()


        }



        update.setOnClickListener{

            val person=Person(

                    user_name.text.toString(),
                    user_email.text.toString(),
                    user_password.text.toString(),
                    user_date.text.toString()


            )

            db.updatePerson(person)

            refreshData()


        }


        delete.setOnClickListener{

            val person=Person(

                    user_name.text.toString(),
                    user_email.text.toString(),
                    user_password.text.toString(),
                    user_date.text.toString()


            )

            db.deletePerson(person)

            refreshData()


        }
    }

    private fun refreshData() {
        lastperson=db.allPerson

        val adapter = ListPersonAdaptar(this@MainActivity,lastperson,user_name,user_email,user_date)

        listView.adapter= adapter
    }
}
