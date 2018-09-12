package com.kejarkoding.jadwalbal.config.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kejarkoding.jadwalbal.R
import com.kejarkoding.jadwalbal.R.id.*
import com.kejarkoding.jadwalbal.model.Schedules

class ScheduleMatchAdapter(private val context: Context, private val schedules: List<Schedules>, private val listener: (Schedules) -> Unit)
    : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_schedule,parent,false))
    }

    override fun getItemCount(): Int = schedules.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(schedules[position],listener)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val strhomee: TextView = view.findViewById(strhome)
    val strawaye : TextView = view.findViewById(straway)
    val intawaye :TextView = view.findViewById(intaway)
    val inthomee :TextView = view.findViewById(inthome)
    val dateevente : TextView= view.findViewById(dateevent)

    fun bindItem(s: Schedules, listener: (Schedules) -> Unit) {
        intawaye.text = s.intAwayScore
        inthomee.text = s.intHomeScore
        dateevente.text = s.dateevene
        strhomee.text = s.strhomeTeam
        strawaye.text = s.strAwayTeam
        itemView.setOnClickListener{
            listener(s)
        }

    }
}