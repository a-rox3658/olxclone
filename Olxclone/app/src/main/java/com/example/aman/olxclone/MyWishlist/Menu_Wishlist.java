package com.example.aman.olxclone.MyWishlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.aman.olxclone.DummyData.Data;
import com.example.aman.olxclone.DummyData.User;
import com.example.aman.olxclone.ProductDesc;
import com.example.aman.olxclone.R;

import java.util.List;

public class Menu_Wishlist extends AppCompatActivity implements MyWishListener.OnRecyclerClickListener{
    private MyWishAdapter myAdsrvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu__wishlist);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvwish);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new MyWishListener(this, recyclerView, this));

        Data aa=(Data)getApplicationContext();
        User bb=aa.users.get(Integer.parseInt(aa.my_id));
        List<String> cc=bb.getWish_id();

        myAdsrvAdapter = new MyWishAdapter(this,cc,getApplicationContext());
        recyclerView.setAdapter(myAdsrvAdapter);


    }

    @Override
    public void onItemClick(View view, int position){ Intent intent = new Intent(this, ProductDesc.class);
     intent.putExtra("AD_TRANSFER", myAdsrvAdapter.getAd(position));
        startActivity(intent);};
    @Override
    public void onItemLongClick(View view, int position){
        Toast.makeText(Menu_Wishlist.this, "long at position " + position, Toast.LENGTH_SHORT).show();


}
}
