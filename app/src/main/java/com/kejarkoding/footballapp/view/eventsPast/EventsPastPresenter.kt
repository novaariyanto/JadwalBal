package com.kejarkoding.footballapp.view.eventsPast

import com.kejarkoding.footballapp.data.ApiRepository


class EventsPastPresenter(private val mView: EventsPastView,
                          private val mApi: ApiRepository) {

    suspend fun fetchLeagues() {
        mView.showLoading()

        val leagues = mApi.leagues().await().leagues
        if (leagues != null && leagues.isNotEmpty()) {
            mView.showLeagues(leagues)
            mView.hideLoading()

            fetchEvents(leagues[0].id)
        } else {
            mView.showPlaceholder()
        }
    }

    suspend fun fetchEvents(leagueId: String) {
        mView.showLoading()

        val events = mApi.pastEvents(leagueId).await().events
        if (events != null && events.isNotEmpty()) {
            mView.showEvents(events)
            mView.hideLoading()
        } else {
            mView.showPlaceholder()
        }
    }

}