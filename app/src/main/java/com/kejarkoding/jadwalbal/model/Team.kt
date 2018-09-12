package com.kejarkoding.jadwalbal.model

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("idTeam")
        var idteam: String? = null,

        @SerializedName("strTeamBadge")
        var strlogo: String? = null
)