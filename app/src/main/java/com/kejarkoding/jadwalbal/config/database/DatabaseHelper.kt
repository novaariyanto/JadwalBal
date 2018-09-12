package com.kejarkoding.jadwalbal.config.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.kejarkoding.jadwalbal.config.database.model.ScheduleFavorite
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "favorite_schedule.db", null, 1) {
    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(ctx.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(ScheduleFavorite.TABLE_FAVORITE, true,
                ScheduleFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                ScheduleFavorite.idEvent to TEXT + UNIQUE,
                ScheduleFavorite.dateEvent to TEXT,
                ScheduleFavorite.strAwayTeam to TEXT,
                ScheduleFavorite.strHomeTeam to TEXT,
                ScheduleFavorite.intAwayScore to TEXT,
                ScheduleFavorite.intHomeScore to TEXT,
                ScheduleFavorite.idAwayTeam to TEXT,
                ScheduleFavorite.idHomeTeam to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.dropTable(ScheduleFavorite.TABLE_FAVORITE, true)
    }

}
val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)
