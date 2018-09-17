package com.kejarkoding.footballapp.view.players

import com.kejarkoding.footballapp.data.remote.response.PlayerResponse


interface PlayersView {

    fun showPlayers(players: List<PlayerResponse.Player>)

    fun showLoading()

    fun hideLoading()

    fun showPlaceholder()

    fun hidePlaceholder()

}