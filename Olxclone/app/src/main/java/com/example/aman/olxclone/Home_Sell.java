package com.example.aman.olxclone;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class Home_Sell extends AppCompatActivity {
ImageView image1,image2,image3;
    Button add1,add2,add3;int butt=0;
    String titl;
    String desc;
    String catg;
    String U_id;
    String A_id;
    String Price;
    String Views="0";
    String Likes="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__sell);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
         add1=(Button)findViewById(R.id.add1) ;
        add2=(Button)findViewById(R.id.add2) ;
        add3=(Button)findViewById(R.id.add3) ;
        image1=(ImageView)findViewById(R.id.image1) ;
        image2=(ImageView)findViewById(R.id.image2) ;
        image3=(ImageView)findViewById(R.id.image3) ;


        View.OnClickListener x=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intnd=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (Intnd.resolveActivity(getPackageManager()) != null){
                if(v==add1)butt=1 ;else if(v==add2)butt=2;else if(v==add3)butt=3;
                startActivityForResult(Intnd,butt);}
            }
                    };

        add1.setOnClickListener(x);add2.setOnClickListener(x);add3.setOnClickListener(x);

        View.OnClickListener y=new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        };

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap=(Bitmap)data.getExtras().get("data");
        if (requestCode==1)
        image1.setImageBitmap(bitmap);
        else if (requestCode==2)
            image2.setImageBitmap(bitmap);
        else if (requestCode==3)
            image3.setImageBitmap(bitmap);


    }


}
