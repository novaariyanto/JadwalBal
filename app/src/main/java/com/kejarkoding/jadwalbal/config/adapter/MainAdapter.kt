package com.kejarkoding.jadwalbal.config.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.kejarkoding.jadwalbal.R
import com.kejarkoding.jadwalbal.R.id.*
import com.kejarkoding.jadwalbal.model.Schedules
import org.jetbrains.anko.*

class MainAdapter(private val context: Context, private val teams: List<Schedules>, private val listener: (Schedules) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.item_schedule,parent,false))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position],listener)
    }


}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

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

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL

                imageView {
                    id = R.id.team_badge
                }.lparams {
                    height = dip(50)
                    width = dip(50)
                }
                textView {
                    id = R.id.team_name
                    textSize = 16f
                }.lparams {
                    margin = dip(15)
                }
                textView {
                    id = R.id.team_home
                    textSize = 16f
                }.lparams {
                    margin = dip(15)
                }
            }
        }
    }
}