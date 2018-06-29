package com.example.aman.olxclone;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyAdsrvAdapter extends RecyclerView.Adapter<MyAdsrvAdapter.MyViewHolder> {
    private Context mContext;
    private List<String>mAds;
     private List<Ads>Adss;
    private static final String TAG = "MainActivity";


    public MyAdsrvAdapter(Context mContext, List<String> mAds,Context mApp) {
        this.mContext = mContext;
        this.mAds = mAds;
        this.Adss=((Data) mApp).all_ads;
//        Log.d(TAG, "MyAdsrvAdapter: "+Adss.toString());

    }

    @NonNull
    @Override
    public MyAdsrvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_myads_rvlayout,parent,false);
                Log.d(TAG, "createview ");

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if((mAds == null) || (mAds.size() == 0)) {

        } else {
            Ads aItem = Adss.get(Integer.parseInt(mAds.get(position)));
        Log.d(TAG, "MyAdsrvAdapter: "+aItem.toString());

            holder.Title.setText(aItem.getTitle());
            holder.Views.append(aItem.getViews().toString());
//            holder.AmountEarned.setText(aItem.getAmountEarned());

        }
    }


    @Override
    public int getItemCount() {
         return ((mAds != null) && (mAds.size() !=0) ? mAds.size() : 1);
    }

    void loadNewData(List<String> newmyads) {
        mAds = newmyads;
        notifyDataSetChanged();
    }
    public Ads getAd(int position) {
        return ((mAds != null) && (mAds.size() !=0) ? Adss.get(Integer.parseInt(mAds.get(position))): null);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Title=null;
        TextView Views=null;
        TextView AmountEarned=null;
        Button Remove=null;
        Button Edit=null;

        MyViewHolder(View itemView)
        {super(itemView);
         this.Title=(TextView)itemView.findViewById(R.id.textView15);
         this.Views=(TextView)itemView.findViewById(R.id.textView3);
         this.Edit=(Button)itemView.findViewById(R.id.button);
         this.Remove=(Button)itemView.findViewById(R.id.button2);
         this.AmountEarned=(TextView)itemView.findViewById(R.id.textView14);
        }


    }
}
