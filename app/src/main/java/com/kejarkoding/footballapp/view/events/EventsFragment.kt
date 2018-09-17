package com.kejarkoding.footballapp.view.events

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kejarkoding.footballapp.util.openEventsSearch
import com.kejarkoding.footballapp.view.eventsNext.EventsNextFragment
import com.kejarkoding.footballapp.view.eventsPast.EventsPastFragment
import com.kejarkoding.footballapp.R
import kotlinx.android.synthetic.main.layout_main_events.*

class EventsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_main_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vp_events.adapter = EventsPagerAdapter(childFragmentManager)
        tab_events.setupWithViewPager(vp_events)

        with(toolbar) {
            inflateMenu(R.menu.search)

            val searchMenu = menu.findItem(R.id.action_search)
            searchMenu.actionView = null
            searchMenu.setOnMenuItemClickListener {
                activity?.openEventsSearch()
                true
            }
        }
    }

}

internal class EventsPagerAdapter(fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

    private val mPages by lazy { listOf(EventsNextFragment(), EventsPastFragment()) }
    private val mTitles by lazy { listOf("Next", "Past") }

    override fun getItem(position: Int): Fragment {
        return mPages[position]
    }

    override fun getCount(): Int {
        return mTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }

}