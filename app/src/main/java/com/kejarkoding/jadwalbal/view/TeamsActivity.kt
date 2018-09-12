package com.kejarkoding.jadwalbal.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.kejarkoding.jadwalbal.R
import com.kejarkoding.jadwalbal.R.color.colorAccent
import com.kejarkoding.jadwalbal.config.invisible
import com.kejarkoding.jadwalbal.config.service.ApiRepository
import com.kejarkoding.jadwalbal.config.visible
import com.kejarkoding.jadwalbal.model.Schedules
import com.kejarkoding.jadwalbal.presenter.ScheduleMatchPresenter
import com.kejarkoding.jadwalbal.`interface`.ScheduleMatchView
import com.kejarkoding.jadwalbal.config.adapter.ScheduleMatchAdapter
import com.kejarkoding.jadwalbal.view.fragment.LastMatchFragment
import com.kejarkoding.jadwalbal.view.fragment.NextMatchFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.onRefresh

class TeamsActivity : AppCompatActivity(), ScheduleMatchView {
    lateinit var swipeRefresh: SwipeRefreshLayout
    var i:Int? = 0

    var schedulePrevMatche: MutableList<Schedules> = mutableListOf()
    lateinit var presenter: ScheduleMatchPresenter
    val request = ApiRepository()
    val gson = Gson()
    lateinit var adapter:ScheduleMatchAdapter


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        adapter = DetailAdapter(schedulePrevMatche)
        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
            item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    i = 0
                    loadTeamsFragment(savedInstanceState)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    i = 1
                    loadFavoritesFragment(savedInstanceState)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        listTeam.adapter
        swipeRefresh = findViewById(R.id.swipe)
        swipeRefresh.setColorSchemeResources(
                    colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
            )

        presenter = ScheduleMatchPresenter(this, request, gson)
        adapter = ScheduleMatchAdapter(this,schedulePrevMatche){
            startActivity<DetailActivity>(
                    "idevent" to "${it.idEvent}",
                    "idhometeam" to "${it.idhometeam}",
                    "idawayteam" to "${it.idawayteam}"
            )
        }

//        schedulelist.layoutManager = LinearLayoutManager(ctx)
//        schedulelist.adapter = adapter

        getscghedule()

        swipeRefresh.onRefresh {
           getscghedule()
        }


    }
     fun getscghedule(){
            if(i == 0){
                presenter.getSchedulePrevMatch("4328")
            }else if(i == 1){
                presenter.getScheduleNextMAtch("4328")
            }else{
                presenter.getSchedulePrevMatch("4328")
            }

    }
    override fun showLoading() {
//        schedulelist.invisible()
        progress.visible()
    }

    override fun hideLoading() {
//        schedulelist.visible()
        progress.invisible()
    }

    override fun showSchedule(data: List<Schedules>) {
        swipeRefresh.isRefreshing = false
        schedulePrevMatche.clear()
        schedulePrevMatche.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if(savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, LastMatchFragment(), LastMatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                    .commit()
        }
    }

}


