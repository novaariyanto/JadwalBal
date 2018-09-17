package com.kejarkoding.footballapp.view.favoritesTeams

import com.kejarkoding.footballapp.data.DbRepository


class FavoriteTeamsPresenter(private val mView: FavoriteTeamsView,
                             private val mDb: DbRepository) {

    suspend fun fetchFavoriteTeams() {
        mView.showLoading()

        val teams = mDb.getFavoriteTeams()
        if (teams.isNotEmpty()) {
            mView.showFavoriteTeams(teams)
            mView.hideLoading()
        } else {
            mView.showPlaceholder()
        }
    }

}