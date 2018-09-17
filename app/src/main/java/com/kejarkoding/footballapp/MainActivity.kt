package com.kejarkoding.footballapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kejarkoding.footballapp.view.events.EventsFragment
import com.kejarkoding.footballapp.view.favorites.FavoritesFragment
import com.kejarkoding.footballapp.view.teams.TeamsFragment
import kotlinx.android.synthetic.main.layout_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main)

        if (savedInstanceState == null) {
            showEvents()
        }

        nav_bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_events -> {
                    showEvents()
                    true
                }
                R.id.nav_teams -> {
                    showTeams()
                    true
                }
                R.id.nav_favorites -> {
                    showFavorites()
                    true
                }
                else -> false
            }
        }
    }

    private fun showEvents() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_main, EventsFragment())
                .commit()
    }

    private fun showTeams() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_main, TeamsFragment())
                .commit()
    }

    private fun showFavorites() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_main, FavoritesFragment())
                .commit()
    }

}
