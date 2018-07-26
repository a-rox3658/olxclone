package com.example.aman.cal;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events.*

import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

import static android.provider.VoicemailContract.Status.CONTENT_URI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button set_event= findViewById(R.id.set_event);

        set_event.setOnClickListener {
            val intent=Intent(Intent.ACTION_INSERT)
                    .setData(CONTENT_URI)
                    .putExtra(TITLE,"My event")
                    .putExtra(EVENT_LOCATION,"Here")
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,System.currentTimeMillis())
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, System.currentTimeMillis()+(60*60*1000))
            startActivity(intent)

        }
    }
}
