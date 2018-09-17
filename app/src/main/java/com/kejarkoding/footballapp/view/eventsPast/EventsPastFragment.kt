package com.kejarkoding.footballapp.view.eventsPast

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.kejarkoding.footballapp.adapter.events.EventsAdapter
import com.kejarkoding.footballapp.data.remote.SportApi
import com.kejarkoding.footballapp.data.remote.response.EventResponse
import com.kejarkoding.footballapp.data.remote.response.LeagueResponse
import com.kejarkoding.footballapp.util.openEventsDetail
import com.kejarkoding.footballapp.R
import kotlinx.android.synthetic.main.item_list_event.view.*
import kotlinx.android.synthetic.main.layout_base_spinner.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class EventsPastFragment : Fragment(), EventsPastView {

    private val mApi by lazy { SportApi() }
    private val mPresenter by lazy { EventsPastPresenter(this, mApi) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_base_spinner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launch(UI) { mPresenter.fetchLeagues() }
    }

    override fun showEvents(events: List<EventResponse.Event>) {
        rv_data?.let {
            with(rv_data) {
                layoutManager = LinearLayoutManager(context)
                adapter = EventsAdapter(events) { event ->
                    activity?.openEventsDetail(event.id)
                }
            }
        }
    }

    override fun showLeagues(leagues: List<LeagueResponse.League>) {
        sp_leagues?.let {
            with(sp_leagues) {
                adapter = ArrayAdapter<String>(
                        context,
                        android.R.layout.simple_spinner_dropdown_item,
                        android.R.id.text1,
                        leagues.map { it.name })

                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        launch(UI) { mPresenter.fetchEvents(leagues[position].id) }
                    }
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