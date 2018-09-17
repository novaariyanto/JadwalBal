package com.kejarkoding.footballapp.view.teamsSearch

import com.kejarkoding.footballapp.data.remote.response.TeamResponse


interface TeamsSearchView {

    fun showTeams(teams: List<TeamResponse.Team>)

    fun showLoading()

    fun hideLoading()

    fun showPlaceholder()

    fun hidePlaceholder()

}