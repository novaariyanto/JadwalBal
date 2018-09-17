package com.kejarkoding.footballapp.adapter.events

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kejarkoding.footballapp.R
import com.kejarkoding.footballapp.dapter.events.EventsViewHolder
import com.kejarkoding.footballapp.data.remote.response.EventResponse



class EventsAdapter(private val mEvents: List<EventResponse.Event>,
                    private val mOnClick: (event: EventResponse.Event) -> Unit)
    : RecyclerView.Adapter<EventsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EventsViewHolder(inflater.inflate(R.layout.item_list_event, parent, false))
    }

    override fun getItemCount(): Int {
        return mEvents.size
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(mEvents[position], mOnClick)
    }

}