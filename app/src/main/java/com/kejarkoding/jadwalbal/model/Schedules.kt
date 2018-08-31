package com.kejarkoding.jadwalbal.model

import com.google.gson.annotations.SerializedName

data class Schedules(

        @SerializedName("dateEvent")
        var dateevene:String? = null,

        @SerializedName("strAwayTeam")
        var strAwayTeam:String? = null,

        @SerializedName("strHomeTeam")
        var strhomeTeam: String? = null,

        @SerializedName("intHomeScore")
        var intHomeScore:String? = null,

        @SerializedName("intAwayScore")
        var intAwayScore:String? = null

)