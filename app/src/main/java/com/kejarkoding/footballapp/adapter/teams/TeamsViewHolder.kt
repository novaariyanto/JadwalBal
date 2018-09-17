package com.kejarkoding.footballapp.adapter.teams

import android.support.v7.widget.RecyclerView
import android.view.View
import com.kejarkoding.footballapp.data.remote.response.TeamResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_team.view.*

class TeamsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(team: TeamResponse.Team, onClick: (team: TeamResponse.Team) -> Unit) {
        with(itemView) {
            Picasso.get().load(team.badgeUrl).into(iv_icon)
            tv_name.text = team.name

            setOnClickListener { onClick(team) }
        }
    }

}