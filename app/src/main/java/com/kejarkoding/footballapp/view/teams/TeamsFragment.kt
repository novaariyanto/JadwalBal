package com.kejarkoding.footballapp.view.teams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.kejarkoding.footballapp.adapter.teams.TeamsAdapter
import com.kejarkoding.footballapp.data.remote.SportApi
import com.kejarkoding.footballapp.data.remote.response.LeagueResponse
import com.kejarkoding.footballapp.data.remote.response.TeamResponse
import com.kejarkoding.footballapp.util.openTeamsDetail
import com.kejarkoding.footballapp.util.openTeamsSearch
import com.kejarkoding.footballapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_team.view.*
import kotlinx.android.synthetic.main.layout_base_spinner.*
import kotlinx.android.synthetic.main.layout_main_teams.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class TeamsFragment : Fragment(), TeamsView {

    private val mApi by lazy { SportApi() }
    private val mPresenter by lazy { TeamsPresenter(this, mApi) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_main_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launch(UI) { mPresenter.fetchLeagues() }

        with(toolbar) {
            inflateMenu(R.menu.search)

            val searchMenu = menu.findItem(R.id.action_search)
            searchMenu.actionView = null
            searchMenu.setOnMenuItemClickListener {
                activity?.openTeamsSearch()
                true
            }
        }
    }

    override fun showLeagues(leagues: List<LeagueResponse.League>) {
        sp_leagues?.let {
            with(sp_leagues) {
                adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, leagues.map { it.name })
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        launch(UI) { mPresenter.fetchTeams(leagues[position].name) }
                    }
                }
            }
        }
    }

    override fun showTeams(teams: List<TeamResponse.Team>) {
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
        clp_data?.visibility = View.VISIBLE
        hidePlaceholder()
    }

    override fun hideLoading() {
        rv_data?.visibility = View.VISIBLE
        clp_data?.visibility = View.GONE
        hidePlaceholder()
    }

    override fun showPlaceholder() {
        rv_data?.visibility = View.GONE
        clp_data?.visibility = View.GONE
        ph_data?.visibility = View.VISIBLE
    }

    override fun hidePlaceholder() {
        ph_data?.visibility = View.GONE
    }

}