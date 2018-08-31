package com.kejarkoding.jadwalbal.presenter

import com.google.gson.Gson
import com.kejarkoding.jadwalbal.config.service.ApiRepository
import com.kejarkoding.jadwalbal.config.service.`object`.TheSportDbApi
import com.kejarkoding.jadwalbal.model.SchedulePrevMatchResponse
import com.kejarkoding.jadwalbal.view.MainView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(
        private val view: MainView,
        private val apiRepository: ApiRepository,
        private val gson: Gson) {

    fun getSchedulePrevMatch(id: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDbApi.getSchedulePrevMatch(id)),
                    SchedulePrevMatchResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showSchedulePrevMatch(data.events)
            }
        }
    }
    fun getScheduleNextMAtch(id: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDbApi.getScheduleNextMatch(id)),
                    SchedulePrevMatchResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showSchedulePrevMatch(data.events)
            }
        }
    }

}