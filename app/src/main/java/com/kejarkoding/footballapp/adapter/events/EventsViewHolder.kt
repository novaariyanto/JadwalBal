package com.kejarkoding.footballapp.dapter.events

import android.support.v7.widget.RecyclerView
import android.view.View
import com.kejarkoding.footballapp.data.remote.response.EventResponse
import kotlinx.android.synthetic.main.item_list_event.view.*

class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(event: EventResponse.Event, onClick: (event: EventResponse.Event) -> Unit) {
        with(itemView) {
            tv_date.text = event.readableDate
            tv_time.text = event.readableTime
            tv_home_name.text = event.homeName
            tv_home_score.text = event.homeScore
            tv_away_name.text = event.awayName
            tv_away_score.text = event.awayScore

            setOnClickListener { onClick(event) }
        }
    }

}