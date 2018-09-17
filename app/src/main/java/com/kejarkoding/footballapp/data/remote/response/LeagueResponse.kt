package com.kejarkoding.footballapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class LeagueResponse(@SerializedName("countrys") val leagues: List<League>?) {

    data class League(@SerializedName("idLeague") val id: String,
                      @SerializedName("strLeague") val name: String)

}