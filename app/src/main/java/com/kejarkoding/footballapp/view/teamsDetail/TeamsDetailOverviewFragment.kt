package com.kejarkoding.footballapp.view.teamsDetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kejarkoding.footballapp.R
import kotlinx.android.synthetic.main.layout_base_overview.*

class TeamsDetailOverviewFragment : Fragment() {

    private lateinit var mOverview: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_base_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tv_overview.text = mOverview
    }

    fun setOverview(text: String) {
        mOverview = text
    }

}