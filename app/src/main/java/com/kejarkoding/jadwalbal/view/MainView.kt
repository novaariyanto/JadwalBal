package com.kejarkoding.jadwalbal.view

import com.kejarkoding.jadwalbal.model.Schedules

interface MainView{
    fun showLoading()
    fun hideLoading()
    fun showSchedulePrevMatch(data:List<Schedules>)
}