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
import com.kejarkoding.jadwalbal.R.color.colorAccent
import com.kejarkoding.jadwalbal.config.adapter.ScheduleFavoriteAdapter
import com.kejarkoding.jadwalbal.config.database.database
import com.kejarkoding.jadwalbal.config.database.model.ScheduleFavorite
import com.kejarkoding.jadwalbal.view.DetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteMatchFragment : Fragment(), AnkoComponent<Context> {

    private var sfavorites: MutableList<ScheduleFavorite> = mutableListOf()
    private lateinit var adapter: ScheduleFavoriteAdapter
    private lateinit var listmf: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                        colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                listmf = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = ScheduleFavoriteAdapter(ctx,sfavorites){
            ctx.startActivity<DetailActivity>(
                    "idevent" to "${it.idEvent}",
                    "idhometeam" to "${it.idHomeTeam}",
                    "idawayteam" to "${it.idAwayTeam}"            )
        }
        listmf.adapter = adapter
        showfavorite()
        swipeRefresh.onRefresh{
            sfavorites.clear()
            showfavorite()
        }
    }
private fun showfavorite(){
    context?.database?.use {
        swipeRefresh.isRefreshing = false
        val result = select(ScheduleFavorite.TABLE_FAVORITE)
        val favorite = result.parseList(classParser<ScheduleFavorite>())
        sfavorites.addAll(favorite)
        adapter.notifyDataSetChanged()
    }
}
}