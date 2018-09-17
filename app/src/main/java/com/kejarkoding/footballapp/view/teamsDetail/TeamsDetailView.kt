package com.kejarkoding.footballapp.view.teamsDetail

import com.kejarkoding.footballapp.data.remote.response.TeamResponse

interface TeamsDetailView {

    fun showTeamDetail(team: TeamResponse.Team)

    fun showMenuAddToFavorite()

    fun showMenuAddedToFavorite()

    fun showLoading()

    fun hideLoading()

}