package com.kejarkoding.jadwalbal.view

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.kejarkoding.jadwalbal.R
import com.kejarkoding.jadwalbal.R.drawable.ic_add_favorite
import com.kejarkoding.jadwalbal.R.drawable.ic_added_favorite
import com.kejarkoding.jadwalbal.R.id.add_to_favorite
import com.kejarkoding.jadwalbal.R.menu.detail_menu
import com.kejarkoding.jadwalbal.`interface`.DetailView
import com.kejarkoding.jadwalbal.config.database.database
import com.kejarkoding.jadwalbal.config.database.model.ScheduleFavorite
import com.kejarkoding.jadwalbal.config.invisible
import com.kejarkoding.jadwalbal.config.service.ApiRepository
import com.kejarkoding.jadwalbal.config.visible
import com.kejarkoding.jadwalbal.model.Schedules
import com.kejarkoding.jadwalbal.model.Team
import com.kejarkoding.jadwalbal.presenter.DetailPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_activity.*
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh

class DetailActivity : AppCompatActivity(), DetailView {

    lateinit var presenter: DetailPresenter
    val request = ApiRepository()
    val gson = Gson()

    private lateinit var idevent: String
    var idhometeam: String? = ""
    var idawayteam: String? = ""

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var schedules: ScheduleFavorite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val intent = intent
        idevent = intent.getStringExtra("idevent")
        idhometeam = intent.getStringExtra("idhometeam")
        idawayteam = intent.getStringExtra("idawayteam")
        favoritestate()
        presenter = DetailPresenter(this, request, gson)
        presenter.getdetailschedule(idevent.toString())
        presenter.getLogohome(idhometeam.toString())
        presenter.getLogoaway(idawayteam.toString())

        swipeRefresh.onRefresh {
            presenter.getdetailschedule(idevent.toString())
            presenter.getLogohome(idhometeam.toString())
            presenter.getLogoaway(idawayteam.toString())
        }

    }

    private fun favoritestate() {
        database.use {
            val result = select(ScheduleFavorite.TABLE_FAVORITE).whereArgs("(idEvent={id})", "id" to idevent)
            val favorite = result.parseList(classParser<ScheduleFavorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun removeFromFavorite() {
        try {
            database.use {
                delete(ScheduleFavorite.TABLE_FAVORITE, "(idEvent ={id})",
                        "id" to idevent)
            }
            snackbar(swipeRefresh, "Remove from Favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(ScheduleFavorite.TABLE_FAVORITE,
                        ScheduleFavorite.idEvent to  eventids.text,
                        ScheduleFavorite.dateEvent to dateevent.text,
                        ScheduleFavorite.strAwayTeam to straway.text,
                        ScheduleFavorite.strHomeTeam to  strhome.text ,
                        ScheduleFavorite.intAwayScore to intaway.text ,
                        ScheduleFavorite.intHomeScore to   inthome.text,
                        ScheduleFavorite.idAwayTeam to idaways.text,
                        ScheduleFavorite.idHomeTeam to idhomes.text
                )
            }
            snackbar(swipeRefresh, "Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefresh, e.localizedMessage).show()
        }

    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_favorite)
    }

    override fun showLoading() {
        progressdetail.visible()
    }

    override fun hideLoading() {
        progressdetail.invisible()
    }

    override fun showlogoaway(data: List<Team>) {
        Picasso.get().load(data.get(0).strlogo).into(logoaway)
    }

    override fun showlogohome(data: List<Team>) {
        Picasso.get().load(data.get(0).strlogo).into(logohome)
    }

    override fun showDetailSchedule(data: List<Schedules>) {
        swipeRefresh.isRefreshing = false
        eventids.text = data.get(0).idEvent
        dateevent.text = data.get(0).dateevene
        idhomes.text = data.get(0).idhometeam
        idaways.text = data.get(0).idawayteam
        intaway.text = data.get(0).intAwayScore
        inthome.text = data.get(0).intHomeScore

        strhome.text = data.get(0).strhomeTeam
        straway.text = data.get(0).strAwayTeam

        strhomeformation.text = data.get(0).strhomeformation
        strawayformation.text = data.get(0).strawayformation

        strhomegoaldetails.text = data.get(0).strhomegoaldetails
        strawaygoaldetails.text = data.get(0).strawaygoaldetails

        inthomeshots.text = data.get(0).inthomeshots
        intawayshots.text = data.get(0).intawayshots
//        lineup
        strHomeLineupGoalkeeper.text = data.get(0).strhomelineupgoalkeeper
        strAwayLineupGoalkeeper.text = data.get(0).strawaylinupgoalkeeper

        strHomeLineupDefense.text = data.get(0).strhomelineupdefense
        strAwayLineupDefense.text = data.get(0).strawaylineupdefense

        strHomeLineupMidfield.text = data.get(0).strhomelineupmidfield
        strAwayLineupMidfield.text = data.get(0).strawaylineupmidfield

        strHomeLineupForward.text = data.get(0).strhomelineupforward
        strAwayLineupForward.text = data.get(0).strawaylineupforward

        strHomeLineupSubstitutes.text = data.get(0).strhomelineupsubstitutes
        strAwayLineupSubstitutes.text = data.get(0).strawaylineupsubstitutes
    }
}
