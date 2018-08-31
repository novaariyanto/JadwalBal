package com.kejarkoding.jadwalbal.config.service

import java.net.URL

class ApiRepository{
    fun doRequest(url:String):String{
        return URL(url).readText()
    }
}