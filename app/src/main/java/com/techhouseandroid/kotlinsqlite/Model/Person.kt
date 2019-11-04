package com.techhouseandroid.kotlinsqlite.Model

import java.net.IDN

/**
 * Created by Nipu on 10/31/2018.
 */
class Person {


    var id:Int=0

    var name:String?=null

    var email:String?=null

    var password:String?=null


    var date:String?=null

    constructor(name: String?, email: String?,password: String?, date: String?) {
        this.name = name
        this.email = email

        this.password=password

        this.date = date
    }

    constructor(){
    }


}