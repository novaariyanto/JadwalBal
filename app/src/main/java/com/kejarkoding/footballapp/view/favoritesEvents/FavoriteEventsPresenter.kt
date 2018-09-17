package com.kejarkoding.footballapp.view.favoritesEvents

import com.kejarkoding.footballapp.data.DbRepository


class FavoriteEventsPresenter(private val mView: FavoriteEventsView,
                              private val mDb: DbRepository) {

    suspend fun fetchFavoriteEvents() {
        mView.showLoading()

        val events = mDb.getFavoriteEvents()
        if (events.isNotEmpty()) {
            mView.showEvents(events)
            mView.hideLoading()
        } else {
            mView.showPlaceholder()
        }
    }

}