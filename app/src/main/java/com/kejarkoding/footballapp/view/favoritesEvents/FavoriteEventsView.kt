package com.kejarkoding.footballapp.view.favoritesEvents

import com.kejarkoding.footballapp.data.remote.response.EventResponse


interface FavoriteEventsView {

    fun showEvents(events: List<EventResponse.Event>)

    fun showLoading()

    fun hideLoading()

    fun showPlaceholder()

    fun hidePlaceholder()

}