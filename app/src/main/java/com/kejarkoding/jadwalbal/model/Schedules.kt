package com.kejarkoding.jadwalbal.model

import com.google.gson.annotations.SerializedName

data class Schedules(

        @SerializedName("dateEvent")
        var dateevene:String? = null,

//namateam
        @SerializedName("strAwayTeam")
        var strAwayTeam:String? = null,

        @SerializedName("strHomeTeam")
        var strhomeTeam: String? = null,

//        goal
        @SerializedName("intHomeScore")
        var intHomeScore:String? = null,

        @SerializedName("intAwayScore")
        var intAwayScore:String? = null,
//    tendangan
        @SerializedName("intHomeShots")
        var inthomeshots:String? = null,
        @SerializedName("intAwayShots")
        var intawayshots:String? = null,
//        goal
        @SerializedName("strHomeGoalDetails")
        var strhomegoaldetails:String? = null,
        @SerializedName("strAwayGoalDetails")
        var strawaygoaldetails:String? = null,
//        Lineup
//        kepeer
        @SerializedName("strHomeLineupGoalkeeper")
        var strhomelineupgoalkeeper:String? = null,
        @SerializedName("strAwayLineupGoalkeeper")
        var strawaylinupgoalkeeper:String? = null,
//        defense
        @SerializedName("strHomeLineupDefense")
        var strhomelineupdefense:String? = null,
        @SerializedName("strAwayLineupDefense")
        var strawaylineupdefense:String? = null,
//        midfield
        @SerializedName("strHomeLineupMidfield")
        var strhomelineupmidfield:String? = null,
        @SerializedName("strAwayLineupMidfield")
        var strawaylineupmidfield:String? = null,
//forward
        @SerializedName("strHomeLineupForward")
        var strhomelineupforward:String? = null,
        @SerializedName("strAwayLineupForward")
        var strawaylineupforward:String? = null,
//substitutes
        @SerializedName("strHomeLineupSubstitutes")
        var strhomelineupsubstitutes:String? = null,
        @SerializedName("strAwayLineupSubstitutes")
        var strawaylineupsubstitutes:String? = null



)