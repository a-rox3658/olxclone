package com.example.aman.olxclone.Myaccount;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aman.olxclone.DownloadStatus;
import com.example.aman.olxclone.DummyData.Data;
import com.example.aman.olxclone.DummyData.User;
import com.example.aman.olxclone.MyWallet.Menu_MyWallet;
import com.example.aman.olxclone.R;
import com.squareup.picasso.Picasso;

public class Menu_MyAccount extends AppCompatActivity implements GetUserJsonData.OnDataAvailable{
    private static final String TAG = "cool";
    ProgressDialog loading = null;
ImageButton x ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu__myaccount);
         x =(ImageButton)findViewById(R.id.imageButton);

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_MyAccount.this, MyaccountEdit.class);

                startActivity(intent);
            }
        });




        loading = new ProgressDialog(this);
        loading.setCancelable(true);
        loading.setMessage("Loading....");
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();



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




        Data x =(Data)getApplicationContext();
//        User me =x.users.get(Integer.parseInt(x.my_id));
//        List<Ads> a_ads=x.all_ads;//all ads list
//        List<String> m_ads= me.getAd_id();//my ads list
//        Integer Views=0;
//        Integer r=m_ads.size(); // no of my ads
//
//
//        TextView intro =(TextView)findViewById(R.id.textView4);
//        intro.append(" "+me.getProfilename());
//
//        TextView name =(TextView)findViewById(R.id.textView5);
//
//        name.append(" "+me.getProfilename());
//        TextView phoneno =(TextView)findViewById(R.id.textView6);
//        phoneno.append(" "+me.getPhoneno());
//        TextView tads =(TextView)findViewById(R.id.textView7);
//        tads.append(" "+r);
//        TextView nviews =(TextView)findViewById(R.id.textView8);
//        for (int i =0 ;i<r;i++){
//            Views+=a_ads.get(Integer.parseInt(m_ads.get(i))).views;
//        }
//        nviews.append(" "+Views);


     GetUserJsonData getUserRawData=new GetUserJsonData(this,"http://askrealone.com/mydealoffers/user_details.php",x.my_id);
      getUserRawData.execute(" ");




    }

    @Override
    public void onDataAvailable(User data, DownloadStatus status) {
        Log.d(TAG, "onDataAvailable: "+data.toString());
        TextView intro =(TextView)findViewById(R.id.textView4);
        intro.setText("Welcome, "+data.getProfilename());

        TextView name =(TextView)findViewById(R.id.textView5);

        name.setText(" "+data.getFirstname()+" "+data.getLastname());
        TextView phoneno =(TextView)findViewById(R.id.textView6);
        phoneno.setText(" "+data.getPhoneno());
        TextView tads =(TextView)findViewById(R.id.textView7);
        tads.setText(" "+data.getAdsNo());
        TextView nviews =(TextView)findViewById(R.id.textView8);
        nviews.setText(" "+data.getAdsViews());


        TextView refecode=(TextView)findViewById(R.id.textView36) ;
        refecode.setText(" "+data.getRefercode());
        TextView createddate=(TextView)findViewById(R.id.textView46) ;
        createddate.setText(" "+data.getCreatedOn());
        ImageView im=(ImageView)findViewById(R.id.imageView);
        ImageView imver=(ImageView)findViewById(R.id.imageView9);

        if(data.getVerfied().equalsIgnoreCase("1")){
            imver.setVisibility(View.VISIBLE);
        }

        else{            imver.setVisibility(View.INVISIBLE);
        }
        if((data.getProfilephoto() == null) ) {
            im.setImageResource(R.mipmap.deft_imgt_round);
        } else {

            Picasso.with(this).load(data.getProfilephoto())
                    .error(R.mipmap.deft_imgt_round)
                    .placeholder(R.mipmap.deft_imgt_round)
                    .into(im);

        }
        loading.dismiss();


    }
}
