package com.example.aman.olxclone.Myads;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aman.olxclone.DownloadStatus;
import com.example.aman.olxclone.DummyData.Ads;
import com.example.aman.olxclone.R;

import java.util.List;

public class Menu_MyAds extends AppCompatActivity implements MyAdsrvListener.OnRecyclerClickListener, Menu_MyAdsJsondata.OnDataAvailable {
    private MyAdsrvAdapter myAdsrvAdapter;
    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    LinearLayout linlaHeaderProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu__myads);


         recyclerView = (RecyclerView) findViewById(R.id.myadsrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new MyAdsrvListener(this, recyclerView, this));
         linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
        linlaHeaderProgress.setVisibility(View.VISIBLE);

//        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
//        setProgressBarIndeterminateVisibility(true);
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myadsrv);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        recyclerView.addOnItemTouchListener(new MyAdsrvListener(this, recyclerView, this));
//
//        Data aa=(Data)getApplicationContext();
//        User bb=aa.users.get(Integer.parseInt(aa.my_id));
//        List<String> cc=bb.getAd_id();
//
//        myAdsrvAdapter = new MyAdsrvAdapter(this,cc,getApplicationContext());
//        recyclerView.setAdapter(myAdsrvAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();


        Menu_MyAdsJsondata my = new Menu_MyAdsJsondata(this, "http://askrealone.com/mydealoffers/view_ads.php", "1");
        my.execute(" ");


//        Data aa=(Data)getApplicationContext();
//        User bb=aa.users.get(Integer.parseInt(aa.my_id));
//        List<String> cc=bb.getAd_id();

    }

    @Override
    public void onItemClick(View view, int position) {
        if (view.getId() == R.id.button) {

            Toast.makeText(Menu_MyAds.this, "long at position " + position, Toast.LENGTH_SHORT).show();
        }


//        else{
        Intent intent = new Intent(Menu_MyAds.this, MyAdDesc.class);
        intent.putExtra("AD_TRANSFER", myAdsrvAdapter.getAd(position));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {
//        Toast.makeText(Menu_MyAds.this, "long at position " + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDataAvailable(List<Ads> data, DownloadStatus status) {
        if (status == DownloadStatus.OK) {
            Log.d(TAG, "onDataAvailable: " + data);
            myAdsrvAdapter = new MyAdsrvAdapter(this, data);
            recyclerView.setAdapter(myAdsrvAdapter);
//            setProgressBarIndeterminateVisibility(false);
            linlaHeaderProgress.setVisibility(View.GONE);


        } else {
            // download or processing failed
            Log.e(TAG, "onDataAvailable failed with status " + status);
        }
    }
}
