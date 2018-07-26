package com.example.aman.caliiii

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events.*

import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        set_eventx.setOnClickListener {
            val intent= Intent(Intent.ACTION_INSERT)
                    .setData(CONTENT_URI)
                    .putExtra(TITLE,"My event")
                    .putExtra(EVENT_LOCATION,"Here")
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,System.currentTimeMillis())
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, System.currentTimeMillis()+(60*60*1000))
            startActivity(intent)

        }
    }
}
