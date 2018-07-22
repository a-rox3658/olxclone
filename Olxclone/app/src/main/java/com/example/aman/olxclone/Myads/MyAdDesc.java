package com.example.aman.olxclone.Myads;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.aman.olxclone.DummyData.Ads;
import com.example.aman.olxclone.R;

public class MyAdDesc extends AppCompatActivity {
private TextView title ;
    private TextView desc;
    private TextView Postedon;
    private TextView price;
    private TextView views;
    private TextView likes;
    private TextView categ;
    private TextView loc;

    private static final String TAG = "MainActivitypik";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_ad_desc);

        Intent intent = getIntent();
        Ads ph = (Ads) intent.getSerializableExtra("AD_TRANSFER");
//        Log.d(TAG, "onCreate: "+ph.toString());

        title=(TextView)findViewById(R.id.textView18);
        title.setText(ph.getTitle());
        title.setTypeface(null, Typeface.BOLD);
          desc=(TextView)findViewById(R.id.textView31);
          desc.setText(ph.getDescription());
         Postedon=(TextView)findViewById(R.id.textView24);
          price=(TextView)findViewById(R.id.textView33);
        if (ph.getPrice()!=null)price.append(" "+ph.getPrice());
         views=(TextView)findViewById(R.id.textView29);
        if (ph.getViews()!=null)views.append(" "+ph.getViews().toString());
         likes=(TextView)findViewById(R.id.textView30);
        if (ph.getLikes()!=null)likes.append(" "+ph.getLikes().toString());
         categ=(TextView)findViewById(R.id.textView32);
        if (ph.getCategory()!=null)categ.append(" "+ph.getCategory());
         loc=(TextView)findViewById(R.id.textView34);
          if (ph.getLocation()!=null)loc.append(ph.getLocation());
   }
}
