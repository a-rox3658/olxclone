package com.example.aman.olxclone.Home_sell;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aman.olxclone.Back_pop;
import com.example.aman.olxclone.DownloadStatus;
import com.example.aman.olxclone.MainActivity;
import com.example.aman.olxclone.Myaccount.Menu_MyAccount;
import com.example.aman.olxclone.R;

public class Home_Sell extends AppCompatActivity implements Home_selljsondata.OnDataAvailable{
ImageView image1,image2,image3;
    Button add1,add2,add3,put;int butt=0;
    EditText title,descp,pri;
    String titl;
    String desc;
    String catg;
    String U_id;
    String A_id;
    String Price;
    String Views="0";
    String Likes="0";
    private static final String TAG = "cool";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__sell);


        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
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
        title=(EditText)findViewById(R.id.editText2);
        descp=(EditText)findViewById(R.id.editText4);
        pri=(EditText)findViewById(R.id.editText5);
        put=(Button)findViewById(R.id.button7) ;
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







Home_selljsondata aaa=new Home_selljsondata(Home_Sell.this,"http://askrealone.com/mydealoffers/create_ad.php",title.getText().toString(),descp.getText().toString(),"1",String.valueOf(spinner.getSelectedItemPosition()),"delhi",pri.getText().toString());

aaa.execute(" ");

            }
        };
        put.setOnClickListener(y);

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

    @Override
    public void onDataAvailable(String data, DownloadStatus status) {
       Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
       if(data.equalsIgnoreCase("Ad Added Successfully")){
           Intent intent = new Intent(this,MainActivity.class);

           startActivity(intent);

       }
        Log.d(TAG, "onDataAvailable: "+data);

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

        Back_pop cdd = new Back_pop(Home_Sell.this);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();

    }
}
