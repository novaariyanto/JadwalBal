package com.kejarkoding.jadwalbal.`interface`

import com.kejarkoding.jadwalbal.model.Schedules
import com.kejarkoding.jadwalbal.model.Team

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showlogohome(data:List<Team>)
    fun showlogoaway(data:List<Team>)
    fun showDetailSchedule(data: List<Schedules>)
}