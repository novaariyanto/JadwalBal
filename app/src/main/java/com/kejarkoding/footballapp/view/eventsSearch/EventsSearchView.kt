package com.kejarkoding.footballapp.view.eventsSearch

import com.kejarkoding.footballapp.data.remote.response.EventResponse


interface EventsSearchView {

    fun showEvents(events: List<EventResponse.Event>)

    fun showLoading()

    fun hideLoading()

    fun showPlaceholder()

    fun hidePlaceholder()

}