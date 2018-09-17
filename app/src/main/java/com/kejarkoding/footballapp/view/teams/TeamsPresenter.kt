package com.kejarkoding.footballapp.view.teams

import com.kejarkoding.footballapp.data.ApiRepository


class TeamsPresenter(private val mView: TeamsView,
                     private val mApi: ApiRepository) {

    suspend fun fetchLeagues() {
        mView.showLoading()

        val leagues = mApi.leagues().await().leagues
        if (leagues != null && leagues.isNotEmpty()) {
            mView.showLeagues(leagues)
            mView.hideLoading()

            fetchTeams(leagues[0].name)
        } else {
            mView.showPlaceholder()
        }
    }

    suspend fun fetchTeams(leagueName: String) {
        mView.showLoading()

        val teams = mApi.teams(leagueName).await().teams
        if (teams != null && teams.isNotEmpty()) {
            mView.showTeams(teams)
            mView.hideLoading()
        } else {
            mView.showPlaceholder()
        }
    }

}