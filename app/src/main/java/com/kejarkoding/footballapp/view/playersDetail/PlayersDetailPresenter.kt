package com.kejarkoding.footballapp.view.playersDetail

import com.kejarkoding.footballapp.data.ApiRepository

class PlayersDetailPresenter(private val mView: PlayersDetailView,
                             private val mApi: ApiRepository) {

    suspend fun fetchPlayerDetail(playerId: String) {
        mView.showLoading()

        val player = mApi.detailPlayer(playerId).await().playersDetail?.get(0)
        player?.let { mView.showPlayerDetail(it) }

        mView.hideLoading()
    }

}