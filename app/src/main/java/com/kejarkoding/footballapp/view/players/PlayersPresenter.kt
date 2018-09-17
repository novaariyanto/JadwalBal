package com.kejarkoding.footballapp.view.players

import com.kejarkoding.footballapp.data.ApiRepository


class PlayersPresenter(private val mView: PlayersView,
                       private val mApi: ApiRepository) {

    suspend fun fetchPlayers(teamId: String) {
        mView.showLoading()

        val players = mApi.players(teamId).await().players
        if (players != null && players.isNotEmpty()) {
            mView.showPlayers(players)
            mView.hideLoading()
        } else {
            mView.showPlaceholder()
        }
    }

}