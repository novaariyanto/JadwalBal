package com.kejarkoding.footballapp.data

import com.kejarkoding.footballapp.data.remote.response.EventResponse
import com.kejarkoding.footballapp.data.remote.response.TeamResponse


interface DbRepository {

    fun getFavoriteEvents(): List<EventResponse.Event>
    fun addToFavoriteEvent(event: EventResponse.Event)
    fun removeFromFavoriteEvent(eventId: String)
    fun isFavoriteEventExists(eventId: String): Boolean

    fun getFavoriteTeams(): List<TeamResponse.Team>
    fun addToFavoriteTeam(team: TeamResponse.Team)
    fun removeFromFavoriteTeam(teamId: String)
    fun isFavoriteTeamExists(teamId: String): Boolean

}