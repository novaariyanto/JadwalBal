package com.kejarkoding.footballapp.data


import com.kejarkoding.footballapp.data.remote.response.EventResponse
import com.kejarkoding.footballapp.data.remote.response.LeagueResponse
import com.kejarkoding.footballapp.data.remote.response.PlayerResponse
import com.kejarkoding.footballapp.data.remote.response.TeamResponse
import kotlinx.coroutines.experimental.Deferred

interface ApiRepository {

    fun leagues(type: String = "soccer"): Deferred<LeagueResponse>

    fun pastEvents(leagueId: String): Deferred<EventResponse>
    fun nextEvents(leagueId: String): Deferred<EventResponse>
    fun searchEvents(keywords: String): Deferred<EventResponse>
    fun detailEvent(eventId: String): Deferred<EventResponse>

    fun teams(leagueName: String): Deferred<TeamResponse>
    fun searchTeams(keywords: String): Deferred<TeamResponse>
    fun detailTeam(teamId: String): Deferred<TeamResponse>

    fun players(teamId: String): Deferred<PlayerResponse>
    fun detailPlayer(playerId: String): Deferred<PlayerResponse>

}