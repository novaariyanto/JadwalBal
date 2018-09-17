package com.kejarkoding.footballapp.view.playersDetail

import com.kejarkoding.footballapp.data.remote.response.PlayerResponse


interface PlayersDetailView {

    fun showPlayerDetail(player: PlayerResponse.Player)

    fun showLoading()

    fun hideLoading()

}