package com.kejarkoding.jadwalbal.`interface`

import com.kejarkoding.jadwalbal.model.Schedules

interface ScheduleMatchView{
    fun showLoading()
    fun hideLoading()
    fun showSchedule(data:List<Schedules>)
}