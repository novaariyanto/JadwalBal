package com.kejarkoding.footballapp.view.teams

import com.kejarkoding.footballapp.data.remote.response.LeagueResponse
import com.kejarkoding.footballapp.data.remote.response.TeamResponse


interface TeamsView {

    fun showLeagues(leagues: List<LeagueResponse.League>)

    fun showTeams(teams: List<TeamResponse.Team>)

    fun showLoading()

    fun hideLoading()

    fun showPlaceholder()

    fun hidePlaceholder()

}