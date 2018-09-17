package com.kejarkoding.footballapp.view.eventsNext

import com.kejarkoding.footballapp.data.remote.response.EventResponse
import com.kejarkoding.footballapp.data.remote.response.LeagueResponse


interface EventsNextView {

    fun showEvents(events: List<EventResponse.Event>)

    fun showLeagues(leagues: List<LeagueResponse.League>)

    fun showLoading()

    fun hideLoading()

    fun showPlaceholder()

    fun hidePlaceholder()

}