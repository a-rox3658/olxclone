package com.example.aman.olxclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.aman.olxclone.DummyData.Ads;
import com.example.aman.olxclone.DummyData.Data;

public class ProductDesc extends AppCompatActivity {
    private TextView title ;
    private TextView desc;
    private TextView Postedon;
    private TextView price;
    private TextView views;
    private TextView likes;
//    private TextView categ;
    private TextView loc;
    private TextView Postedby;
    private ImageButton liker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_desc);
        title=(TextView)findViewById(R.id.textView14);
        Postedby=(TextView)findViewById(R.id.textView15);
        Postedon=(TextView)findViewById(R.id.textView20);
        desc =(TextView)findViewById(R.id.prod_desc);
        price=(TextView)findViewById(R.id.textView44);
        views=(TextView)findViewById(R.id.textView21);
        likes=(TextView)findViewById(R.id.textView22);
        loc=(TextView)findViewById(R.id.textView23);
        liker=(ImageButton)findViewById(R.id.imageButton5);
liker.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        liker.setImageResource(R.mipmap.fav_hrt_check);

    }
});

        Data a=(Data)getApplicationContext();


        Intent intent = getIntent();
        Ads ph = (Ads) intent.getSerializableExtra("AD_TRANSFER");
        title.setText(ph.getTitle());
//        Postedon.append();
        Postedby.append(a.users.get(Integer.parseInt(ph.getUser_id())).getProfilename());
        desc.setText(ph.getDescription());
        price.append(ph.getPrice());
        views.append(ph.getViews().toString());
        likes.append(ph.getLikes().toString());
       if(ph.getLocation()!=null) loc.setText(ph.getLocation());



    }
}
