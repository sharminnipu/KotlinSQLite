package com.techhouseandroid.kotlinsqlite.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.techhouseandroid.kotlinsqlite.Model.Person
import com.techhouseandroid.kotlinsqlite.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.layout_list.view.*

/**
 * Created by Nipu on 11/1/2018.
 */
class ListPersonAdaptar(internal var activity:Activity,
                        internal var lastperson:List<Person>,
                         internal var edt_name:EditText,
                         internal var edt_email:EditText,
                        internal var edt_date:EditText): BaseAdapter() {

    internal var inflater:LayoutInflater

    init {

        inflater= activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val rowView:View

        rowView=inflater.inflate(R.layout.layout_list,null)


        rowView.text_name.text=lastperson[position].name.toString()
        rowView.text_email.text=lastperson[position].email.toString()
       // rowView.text_name.text=lastperson[position].name.toString()
        rowView.text_date.text=lastperson[position].date.toString()


        rowView.setOnClickListener{

            edt_name.setText( rowView.text_name.text.toString())
            edt_email.setText( rowView.text_email.text.toString())
            edt_date.setText( rowView.text_date.text.toString())

        }

        return rowView

    }

    override fun getItem(position: Int): Any {

        return lastperson[position]
    }

    override fun getItemId(position: Int): Long {

        return  lastperson[position].id.toLong()
    }



    override fun getCount(): Int {
        return lastperson.size
    }


}