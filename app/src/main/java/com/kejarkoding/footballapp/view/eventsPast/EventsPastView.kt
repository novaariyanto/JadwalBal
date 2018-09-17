package com.kejarkoding.footballapp.view.eventsPast

import com.kejarkoding.footballapp.data.remote.response.EventResponse
import com.kejarkoding.footballapp.data.remote.response.LeagueResponse


interface EventsPastView {

    fun showEvents(events: List<EventResponse.Event>)

    fun showLeagues(leagues: List<LeagueResponse.League>)

    fun showLoading()

    fun hideLoading()

    fun showPlaceholder()

    fun hidePlaceholder()

}