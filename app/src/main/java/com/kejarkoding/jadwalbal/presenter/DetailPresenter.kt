package com.kejarkoding.jadwalbal.presenter

import com.google.gson.Gson
import com.kejarkoding.jadwalbal.`interface`.DetailView
import com.kejarkoding.jadwalbal.config.service.ApiRepository
import com.kejarkoding.jadwalbal.config.service.`object`.TheSportDbApi
import com.kejarkoding.jadwalbal.model.ScheduleList
import com.kejarkoding.jadwalbal.model.TeamLogo
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(
        private val view: DetailView,
        private val apiRepository: ApiRepository,
        private val gson: Gson) {

    fun getdetailschedule(id: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDbApi.getDetailSchedule(id)),
                    ScheduleList::class.java
            )
            uiThread {
                view.hideLoading()
                view.showDetailSchedule(data.events)
            }
        }
    }

    fun getLogohome(id: String) {
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDbApi.getLogo(id))
                    , TeamLogo::class.java
            )
            uiThread {
                view.showlogohome(data.teams)
            }
        }
    }
    fun getLogoaway(id: String) {
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDbApi.getLogo(id))
                    , TeamLogo::class.java
            )
            uiThread {
                view.showlogoaway(data.teams)
            }
        }
    }
}