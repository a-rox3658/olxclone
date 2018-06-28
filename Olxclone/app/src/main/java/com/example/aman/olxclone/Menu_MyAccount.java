package com.example.aman.olxclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class Menu_MyAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu__myaccount);
        Data x =(Data)getApplicationContext();
        User me =x.users.get(Integer.parseInt(x.my_id));
        List<Ads> a_ads=x.all_ads;//all ads list
        List<String> m_ads= me.getAd_id();//my ads list
        Integer Views=0;
        Integer r=m_ads.size(); // no of my ads


        TextView intro =(TextView)findViewById(R.id.textView4);
        intro.append(" "+me.getProfilename());

        TextView name =(TextView)findViewById(R.id.textView5);

        name.append(" "+me.getProfilename());
        TextView phoneno =(TextView)findViewById(R.id.textView6);
        phoneno.append(" "+me.getPhoneno());
        TextView tads =(TextView)findViewById(R.id.textView7);
        tads.append(" "+r);
        TextView nviews =(TextView)findViewById(R.id.textView8);
        for (int i =0 ;i<r;i++){
            Views+=a_ads.get(Integer.parseInt(m_ads.get(i))).views;
        }
        nviews.append(" "+Views);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Data x =(Data)getApplicationContext();
//        User me =x.users.get(Integer.parseInt(x.my_id));
//        List<Ads> a_ads=x.all_ads;
//        Integer Views=0;
//        TextView intro =(TextView)findViewById(R.id.textView4);
//        intro.append(" "+me.getProfilename());
//
//        TextView name =(TextView)findViewById(R.id.textView5);
//
//        name.append(" "+me.getProfilename());
//        TextView phoneno =(TextView)findViewById(R.id.textView6);
//        phoneno.append(" "+me.getPhoneno());
//        TextView tads =(TextView)findViewById(R.id.textView7);
//        tads.append(" "+me.getAdd_id().size());
//        TextView nviews =(TextView)findViewById(R.id.textView8);
//        for (int i =0 ;i<me.getAdd_id().size();i++){
//            Views+=a_ads.get(Integer.parseInt(me.getAd_id().get(i))).views;
//        }
//        nviews.append(" "+Views);
    }
}
