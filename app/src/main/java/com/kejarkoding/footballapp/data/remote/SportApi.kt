package com.kejarkoding.footballapp.data.remote

import android.accounts.NetworkErrorException
import com.androidnetworking.AndroidNetworking
import com.kejarkoding.footballapp.data.ApiRepository
import com.kejarkoding.footballapp.data.remote.response.EventResponse
import com.kejarkoding.footballapp.data.remote.response.LeagueResponse
import com.kejarkoding.footballapp.data.remote.response.PlayerResponse
import com.kejarkoding.footballapp.data.remote.response.TeamResponse
import kotlinx.coroutines.experimental.Deferred
import org.jetbrains.anko.coroutines.experimental.bg

class SportApi : ApiRepository {

    override fun leagues(type: String): Deferred<LeagueResponse> = req(
            Endpoint.LEAGUES,
            LeagueResponse::class.java,
            "s" to type
    ) as Deferred<LeagueResponse>

    override fun pastEvents(leagueId: String): Deferred<EventResponse> = req(
            Endpoint.EVENTS_PAST,
            EventResponse::class.java,
            "id" to leagueId
    ) as Deferred<EventResponse>

    override fun nextEvents(leagueId: String): Deferred<EventResponse> = req(
            Endpoint.EVENTS_NEXT,
            EventResponse::class.java,
            "id" to leagueId
    ) as Deferred<EventResponse>

    override fun searchEvents(keywords: String): Deferred<EventResponse> {
        return req(
                Endpoint.EVENTS_SEARCH,
                EventResponse::class.java,
                "e" to keywords
        ) as Deferred<EventResponse>
    }

    override fun detailEvent(eventId: String): Deferred<EventResponse> = req(
            Endpoint.EVENTS_DETAIL,
            EventResponse::class.java,
            "id" to eventId
    ) as Deferred<EventResponse>

    override fun teams(leagueName: String): Deferred<TeamResponse> = req(
            Endpoint.TEAMS,
            TeamResponse::class.java,
            "l" to leagueName
    ) as Deferred<TeamResponse>

    override fun searchTeams(keywords: String): Deferred<TeamResponse> = req(
            Endpoint.TEAMS_SEARCH,
            TeamResponse::class.java,
            "t" to keywords
    ) as Deferred<TeamResponse>

    override fun detailTeam(teamId: String): Deferred<TeamResponse> = req(
            Endpoint.TEAMS_DETAIL,
            TeamResponse::class.java,
            "id" to teamId
    ) as Deferred<TeamResponse>

    override fun players(teamId: String): Deferred<PlayerResponse> = req(
            Endpoint.PLAYERS,
            PlayerResponse::class.java,
            "id" to teamId
    ) as Deferred<PlayerResponse>

    override fun detailPlayer(playerId: String): Deferred<PlayerResponse> = req(
            Endpoint.PLAYERS_DETAIL,
            PlayerResponse::class.java,
            "id" to playerId
    ) as Deferred<PlayerResponse>

    private fun req(url: String, type: Class<*>, vararg pairs: Pair<String, String>): Deferred<Any> {
        return bg {
            val response = AndroidNetworking
                    .get(url)
                    .apply { pairs.forEach { addQueryParameter(it.first, it.second) } }
                    .build()
                    .executeForObject(type)

            if (!response.isSuccess) {
                throw NetworkErrorException(response.error.localizedMessage)
            }

            response.result
        }
    }

}