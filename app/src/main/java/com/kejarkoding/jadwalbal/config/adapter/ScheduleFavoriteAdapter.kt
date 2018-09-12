package com.kejarkoding.jadwalbal.config.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kejarkoding.jadwalbal.R
import com.kejarkoding.jadwalbal.config.database.model.ScheduleFavorite

class ScheduleFavoriteAdapter(private val context: Context,private val schedulesfavorite: List<ScheduleFavorite>, private val listener: (ScheduleFavorite) -> Unit)
    : RecyclerView.Adapter<ViewHoldere>() {

    override fun onBindViewHolder(holder: ViewHoldere, position: Int) {
        holder.bindItem(schedulesfavorite[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoldere {
        return ViewHoldere(LayoutInflater.from(context).inflate(R.layout.item_schedule, parent, false))
    }

    override fun getItemCount(): Int = schedulesfavorite.size

}

class ViewHoldere(view: View) : RecyclerView.ViewHolder(view) {

    val strhomee: TextView = view.findViewById(R.id.strhome)
    val strawaye : TextView = view.findViewById(R.id.straway)
    val intawaye : TextView = view.findViewById(R.id.intaway)
    val inthomee : TextView = view.findViewById(R.id.inthome)
    val dateevente : TextView = view.findViewById(R.id.dateevent)

    fun bindItem(s: ScheduleFavorite, listener: (ScheduleFavorite) -> Unit) {
        intawaye.text = s.intAwayScore
        inthomee.text = s.intHomeScore
        dateevente.text = s.dateEvent
        strhomee.text = s.strHomeTeam
        strawaye.text = s.strAwayTeam
        itemView.setOnClickListener{
            listener(s)
        }

    }
}