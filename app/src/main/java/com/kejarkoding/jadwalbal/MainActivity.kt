package com.kejarkoding.jadwalbal

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.*
import com.google.gson.Gson
import com.kejarkoding.jadwalbal.R.color.colorAccent
import com.kejarkoding.jadwalbal.config.adapter.MainAdapter
import com.kejarkoding.jadwalbal.config.invisible
import com.kejarkoding.jadwalbal.config.service.ApiRepository
import com.kejarkoding.jadwalbal.config.visible
import com.kejarkoding.jadwalbal.model.Schedules
import com.kejarkoding.jadwalbal.presenter.MainPresenter
import com.kejarkoding.jadwalbal.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.onRefresh

class MainActivity : AppCompatActivity(), MainView {
    lateinit var progressBar: ProgressBar
    lateinit var swipeRefresh: SwipeRefreshLayout
    var i:Int? = 0

    var schedulePrevMatche: MutableList<Schedules> = mutableListOf()
    lateinit var presenter: MainPresenter
    lateinit var adapter: MainAdapter

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                i=0
                getscghedule()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                i=1
               getscghedule()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        adapter = MainAdapter(schedulePrevMatche)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        listTeam.adapter
        swipeRefresh = findViewById(R.id.swipe)
        swipeRefresh.setColorSchemeResources(
                    colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
            )
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        adapter = MainAdapter(this,schedulePrevMatche)

        schedulelist.layoutManager = LinearLayoutManager(ctx)
        schedulelist.adapter = adapter
        getscghedule()

        swipeRefresh.onRefresh {
           getscghedule()
        }


    }
     fun getscghedule(){
            if(i==0){
                presenter.getScheduleNextMAtch("4328")
            }else if(i==1){
                presenter.getSchedulePrevMatch("4328")
            }

    }
    override fun showLoading() {
        progress.visible()
    }

    override fun hideLoading() {
        progress.invisible()
    }

    override fun showSchedulePrevMatch(data: List<Schedules>) {
        swipeRefresh.isRefreshing = false
        schedulePrevMatche.clear()
        schedulePrevMatche.addAll(data)
        adapter.notifyDataSetChanged()


    }
}


