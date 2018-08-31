package com.kejarkoding.jadwalbal

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.detail_activity.*
import org.jetbrains.anko.setContentView

class DetailActivity : AppCompatActivity() {
    var strhometeame :String? = ""
    var strawayteame :String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val intent = intent
        strhometeame = intent.getStringExtra("strhometeam")
        strawayteame = intent.getStringExtra("strawayteam")

        dateevent.text = intent.getStringExtra("")
        strhome.text = strhometeame
        straway.text = strawayteame



    }
}
