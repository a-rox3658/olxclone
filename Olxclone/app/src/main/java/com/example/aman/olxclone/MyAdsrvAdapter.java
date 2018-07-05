package com.example.aman.olxclone;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        View view;
        if((mAds == null) || (mAds.size() == 0)) {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_blank,parent,false);
            view.setClickable(false);
        }
       else{
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_myads_rvlayout,parent,false);
//                Log.d(TAG, "createview ");

       }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder,final int position) {
        if((mAds == null) || (mAds.size() == 0)) {

        } else {
            Ads aItem = Adss.get(Integer.parseInt(mAds.get(position)));
//        Log.d(TAG, "MyAdsrvAdapter: "+aItem.toString());

            holder.Title.setText(aItem.getTitle());
            holder.Views.append(aItem.getViews().toString());
//            holder.AmountEarned.setText(aItem.getAmountEarned());
      holder.Remove.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Log.d(TAG, "\n dfgdonClickyyyyyyyy:yyyyyyyyy \n");
          Toast.makeText(mContext,"Removed",Toast.LENGTH_SHORT).show();
          Adss.set(Integer.parseInt(mAds.get(position)),null);
          mAds.remove(position);
//               Adss.remove(Adss.get(Integer.parseInt(mAds.get(position))));
          Intent intent = new Intent(mContext, MainActivity.class);
//            intent.putExtra("AD_TRANSFER", myAdsrvAdapter.getAd(position));
            v.getContext().startActivity(intent);
//          Log.d(TAG, "onClick: "+Adss.get(0).toString());


          }
      });


            holder.Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"Edit",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, Menu_MyadsEdit.class);
           intent.putExtra("AD_TRANSFER",Adss.get(Integer.parseInt(mAds.get(position))));
                    v.getContext().startActivity(intent);
                }
            });
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
         this.Edit=(Button)itemView.findViewById(R.id.button2);
         this.Remove=(Button)itemView.findViewById(R.id.button);
         this.AmountEarned=(TextView)itemView.findViewById(R.id.textView14);
        }


    }
}
