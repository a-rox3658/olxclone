package com.example.aman.olxclone.Myaccount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.aman.olxclone.DummyData.Ads;
import com.example.aman.olxclone.DummyData.User;
import com.example.aman.olxclone.R;
import com.squareup.picasso.Picasso;

public class EditProfilepic extends AppCompatActivity {
    User ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profilepic);
        Intent intent = getIntent();
         ph = (User) intent.getSerializableExtra("USER_TRANSFER");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageView im= (ImageView)findViewById(R.id.imageView11);
        if((ph.getProfilephoto() == null) ) {
            im.setImageResource(R.mipmap.deft_imgt);
        } else {

            Picasso.with(this).load(ph.getProfilephoto())
                    .error(R.mipmap.deft_imgt)
                    .placeholder(R.mipmap.deft_imgt)
                    .into(im);

        }
    }
}
