package com.example.aman.olxclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Menu_MyAds extends AppCompatActivity implements MyAdsrvListener.OnRecyclerClickListener {
private MyAdsrvAdapter myAdsrvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu__myads);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myadsrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new MyAdsrvListener(this, recyclerView, this));

        Data aa=(Data)getApplicationContext();
        User bb=aa.users.get(Integer.parseInt(aa.my_id));
        List<String> cc=bb.getAd_id();

        myAdsrvAdapter = new MyAdsrvAdapter(this,cc,getApplicationContext());
        recyclerView.setAdapter(myAdsrvAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        MyAdsrvAdapter.loadNewData(cc);
    }

    @Override
   public void onItemClick(View view, int position){ Intent intent = new Intent(this, ProductDesc.class);
//      intent.putExtra("AD_TRANSFER", myAdsrvAdapter.getAd(position));
        startActivity(intent);};
    @Override
   public void onItemLongClick(View view, int position){
        Toast.makeText(Menu_MyAds.this, "long at position " + position, Toast.LENGTH_SHORT).show();

    }
}
