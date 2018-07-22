package com.example.aman.olxclone.MyWishlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.aman.olxclone.DummyData.Ads;
import com.example.aman.olxclone.DummyData.Data;
import com.example.aman.olxclone.R;

import java.util.List;

public class MyWishAdapter extends RecyclerView.Adapter<MyWishAdapter.MyViewHolder> {
    private Context mContext;
    private List<String>mwAds;
    private List<Ads>Adss;
    private static final String TAG = "MainActivity";


    public MyWishAdapter(Context mContext, List<String> mwAds,Context mApp) {
        this.mContext = mContext;
        this.mwAds = mwAds;
        this.Adss=((Data) mApp).all_ads;
//        Log.d(TAG, "MyAdsrvAdapter: "+Adss.toString());

    }

    @NonNull
    @Override
    public MyWishAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_wishlist_rvlayout,parent,false);
        Log.d(TAG, "createview ");

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if((mwAds == null) || (mwAds.size() == 0)) {

        } else {
            Ads aItem = Adss.get(Integer.parseInt(mwAds.get(position)));
            Log.d(TAG, "MyAdsrvAdapter: "+aItem.toString());

            holder.Title.setText(aItem.getTitle());
            holder.Views.append(aItem.getViews().toString());
            holder.Likes.append(aItem.getLikes().toString());

//            holder.AmountEarned.setText(aItem.getAmountEarned());

        }
    }


    @Override
    public int getItemCount() {
        return ((mwAds != null) && (mwAds.size() !=0) ? mwAds.size() : 1);
    }

    void loadNewData(List<String> newmywads) {
        mwAds = newmywads;
        notifyDataSetChanged();
    }
    public Ads getAd(int position) {
        return ((mwAds != null) && (mwAds.size() !=0) ? Adss.get(Integer.parseInt(mwAds.get(position))): null);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Title=null;
        TextView Views=null;
        TextView Likes=null;
        Button Remove=null;


        MyViewHolder(View itemView)
        {super(itemView);
            this.Title=(TextView)itemView.findViewById(R.id.textView17);
            this.Views=(TextView)itemView.findViewById(R.id.textView2);
            this.Remove=(Button)itemView.findViewById(R.id.button3);
            this.Likes=(TextView)itemView.findViewById(R.id.textView16);
        }


    }
}
