package com.kejarkoding.footballapp.view.eventsSearch


import com.kejarkoding.footballapp.data.ApiRepository
import com.kejarkoding.footballapp.data.remote.response.EventResponse
import kotlinx.coroutines.experimental.Deferred

class EventsSearchPresenter(private val mView: EventsSearchView,
                            private val mApi: ApiRepository) {

    private var mDeferredSearch: Deferred<EventResponse>? = null

    suspend fun fetchSearchEvents(keywords: String) {
        if (keywords.isBlank()) return

        mView.showLoading()

        mDeferredSearch?.cancel(IllegalStateException())
        mDeferredSearch = mApi.searchEvents(keywords)

        try {
            val events = mDeferredSearch?.await()?.eventsSearch

            if (events != null && events.isNotEmpty()) {
                mView.showEvents(events.filter { it.sport == "Soccer" })
                mView.hideLoading()
            } else {
                mView.showPlaceholder()
            }
        } catch (e: IllegalStateException) { }
    }

}