package com.kejarkoding.footballapp.view.favoritesTeams

import com.kejarkoding.footballapp.data.remote.response.TeamResponse


interface FavoriteTeamsView {

    fun showFavoriteTeams(teams: List<TeamResponse.Team>)

    fun showLoading()

    fun hideLoading()

    fun showPlaceholder()

    fun hidePlaceholder()

}