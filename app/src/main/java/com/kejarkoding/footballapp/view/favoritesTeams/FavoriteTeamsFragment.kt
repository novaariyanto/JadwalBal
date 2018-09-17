package com.kejarkoding.footballapp.view.favoritesTeams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kejarkoding.footballapp.adapter.teams.TeamsAdapter
import com.kejarkoding.footballapp.data.db.AnkoDb
import com.kejarkoding.footballapp.data.remote.response.TeamResponse
import com.kejarkoding.footballapp.util.openTeamsDetail
import com.kejarkoding.footballapp.R
import kotlinx.android.synthetic.main.layout_base_lists.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class FavoriteTeamsFragment : Fragment(), FavoriteTeamsView {

    private val mDb by lazy { AnkoDb(context!!) }
    private val mPresenter by lazy { FavoriteTeamsPresenter(this, mDb) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_base_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launch(UI) { mPresenter.fetchFavoriteTeams() }
    }

    override fun showFavoriteTeams(teams: List<TeamResponse.Team>) {
        rv_data?.let {
            with(rv_data) {
                layoutManager = LinearLayoutManager(context)
                adapter = TeamsAdapter(teams) { team ->
                    activity?.openTeamsDetail(team.id)
                }
            }
        }
    }

    override fun showLoading() {
        rv_data?.visibility = View.GONE
        clp_data?.show()
        hidePlaceholder()
    }

    override fun hideLoading() {
        rv_data?.visibility = View.VISIBLE
        clp_data?.hide()
        hidePlaceholder()
    }

    override fun showPlaceholder() {
        rv_data?.visibility = View.GONE
        clp_data?.hide()
        ph_data?.visibility = View.VISIBLE
    }

    override fun hidePlaceholder() {
        ph_data?.visibility = View.GONE
    }

}