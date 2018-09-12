package com.kejarkoding.jadwalbal.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.kejarkoding.jadwalbal.R
import com.kejarkoding.jadwalbal.R.color.colorAccent
import com.kejarkoding.jadwalbal.`interface`.ScheduleMatchView
import com.kejarkoding.jadwalbal.config.adapter.ScheduleMatchAdapter
import com.kejarkoding.jadwalbal.config.invisible
import com.kejarkoding.jadwalbal.config.service.ApiRepository
import com.kejarkoding.jadwalbal.config.visible
import com.kejarkoding.jadwalbal.model.Schedules
import com.kejarkoding.jadwalbal.presenter.ScheduleMatchPresenter
import com.kejarkoding.jadwalbal.view.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class LastMatchFragment : Fragment(), AnkoComponent<Context>, ScheduleMatchView {
    lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar

    var scheduleLastMatch: MutableList<Schedules> = mutableListOf()
    lateinit var presenter: ScheduleMatchPresenter
    val request = ApiRepository()
    val gson = Gson()
    lateinit var adapter: ScheduleMatchAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = ScheduleMatchPresenter(this, request, gson)
        adapter = ScheduleMatchAdapter(ctx, scheduleLastMatch) {
            startActivity<DetailActivity>(
                    "idevent" to "${it.idEvent}",
                    "idhometeam" to "${it.idhometeam}",
                    "idawayteam" to "${it.idawayteam}"
            )
        }
        listMatch.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = ScheduleMatchPresenter(this, request, gson)
        presenter.getSchedulePrevMatch("4328")


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)


            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                        colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                )
                relativeLayout {
                    lparams(width = matchParent, height = matchParent)
                    listMatch = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                    progressBar = progressBar {
                    }.lparams { centerHorizontally() }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
           }

    override fun hideLoading() {
        progressBar.invisible()
          }

    override fun showSchedule(data: List<Schedules>) {
        swipeRefresh.isRefreshing = false
        scheduleLastMatch.clear()
        scheduleLastMatch.addAll(data)
        adapter.notifyDataSetChanged()
    }

}