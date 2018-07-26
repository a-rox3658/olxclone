package com.example.aman.olxclone.Myaccount;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aman.olxclone.Back_pop;
import com.example.aman.olxclone.DownloadStatus;
import com.example.aman.olxclone.DummyData.Data;
import com.example.aman.olxclone.DummyData.User;
import com.example.aman.olxclone.R;
import com.squareup.picasso.Picasso;


public class MyaccountEdit extends AppCompatActivity implements GetUserJsonData.OnDataAvailable {
    ProgressDialog loading = null;
User trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount_edit);
        loading = new ProgressDialog(this);
        loading.setCancelable(false);
        loading.setCanceledOnTouchOutside(false);
        loading.setMessage("Loading....");
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
       Data x =(Data)getApplicationContext();
        GetUserJsonData getUserRawData=new GetUserJsonData(this,"http://askrealone.com/mydealoffers/user_details.php",x.my_id);
        getUserRawData.execute(" ");

        Button pp=(Button)findViewById(R.id.button4);
        Button en=(Button)findViewById(R.id.button8);
        Button ep=(Button)findViewById(R.id.button6);
        pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyaccountEdit.this, EditProfilepic.class);
                intent.putExtra("USER_TRANSFER",trans);

                startActivity(intent);
            }
        });
        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyaccountEdit.this, Editname.class);
                intent.putExtra("USER_TRANSFER",trans);
                startActivity(intent);

            }
        });
        ep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyaccountEdit.this, Editpassword.class);
                intent.putExtra("USER_TRANSFER",trans);
                startActivity(intent);

            }
        });
    }

    public void onDataAvailable(User data, DownloadStatus status) {
//        Log.d(TAG, "onDataAvailable: "+data.toString());
        trans=data;
        TextView fn=(TextView)findViewById(R.id.Text11);
        TextView ln=(TextView)findViewById(R.id.Text3);
        fn.setText(data.getFirstname());
        ln.setText(data.getLastname());
       ImageView im= (ImageView)findViewById(R.id.imageView10);
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        Back_pop cdd = new Back_pop(MyaccountEdit.this);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();

    }
}
