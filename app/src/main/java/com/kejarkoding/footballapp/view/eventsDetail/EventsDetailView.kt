package com.kejarkoding.footballapp.view.eventsDetail

import com.kejarkoding.footballapp.data.remote.response.EventResponse


interface EventsDetailView {

    fun showEventDetail(event: EventResponse.Event)

    fun showMenuAddToFavorite()

    fun showMenuAddedToFavorite()

    fun showHomeBadge(badgeUrl: String)

    fun showAwayBadge(badgeUrl: String)

    fun showLoading()

    fun hideLoading()

}