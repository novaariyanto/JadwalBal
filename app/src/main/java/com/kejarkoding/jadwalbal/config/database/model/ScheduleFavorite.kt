package com.kejarkoding.jadwalbal.config.database.model

import com.google.gson.annotations.SerializedName
data class ScheduleFavorite(
        val id:Long?,
        val idEvent:String?,
        val dateEvent:String?,
        val strAwayTeam:String?,
        val strHomeTeam:String?,
        val intHomeScore:String?,
        val intAwayScore:String?,
        val idHomeTeam:String?,
        val idAwayTeam:String?
){
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val idEvent: String = "idEvent"
        const val dateEvent: String = "dateEvent"
        const val strAwayTeam: String = "strAwayTeam"
        const val strHomeTeam: String = "strHomeTeam"
        const val intAwayScore: String = "intAwayScore"
        const val intHomeScore: String = "intHomeScore"
        const val idHomeTeam: String = "idHomeTeam"
        const val idAwayTeam: String = "idAwayTeam"
    }

}