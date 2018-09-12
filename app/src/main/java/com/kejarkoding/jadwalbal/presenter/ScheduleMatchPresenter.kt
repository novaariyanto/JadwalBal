package com.kejarkoding.jadwalbal.presenter

import com.google.gson.Gson
import com.kejarkoding.jadwalbal.config.service.ApiRepository
import com.kejarkoding.jadwalbal.config.service.`object`.TheSportDbApi
import com.kejarkoding.jadwalbal.model.ScheduleList
import com.kejarkoding.jadwalbal.`interface`.ScheduleMatchView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ScheduleMatchPresenter(
        private val view: ScheduleMatchView,
        private val apiRepository: ApiRepository,
        private val gson: Gson) {

    fun getSchedulePrevMatch(id: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDbApi.getSchedulePrevMatch(id)),
                    ScheduleList::class.java
            )
            uiThread {
                view.hideLoading()
                view.showSchedule(data.events)
            }
        }
    }
    fun getScheduleNextMAtch(id: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDbApi.getScheduleNextMatch(id)),
                    ScheduleList::class.java
            )
            uiThread {
                view.hideLoading()
                view.showSchedule(data.events)
            }
        }
    }

}