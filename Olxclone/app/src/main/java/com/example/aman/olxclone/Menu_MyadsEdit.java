package com.example.aman.olxclone;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Menu_MyadsEdit extends AppCompatActivity {
    ImageView image1, image2, image3;
    Button add1, add2, add3;
    EditText edit_Title,edit_desc,edit_price;
    TextView edit_loc;
    int butt = 0;
    String titl;
    String desc;
    String catg;
    String U_id;
    String A_id;
    String Price;
    String Views = "0";
    String Likes = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu__myads_edit);
        Intent intent = getIntent();
        Ads ph = (Ads) intent.getSerializableExtra("AD_TRANSFER");
        add1 = (Button) findViewById(R.id.edit_add1);
        add2 = (Button) findViewById(R.id.edit_add2);
        add3 = (Button) findViewById(R.id.edit_add3);
        image1 = (ImageView) findViewById(R.id.edit_image1);
        image2 = (ImageView) findViewById(R.id.edit_image2);
        image3 = (ImageView) findViewById(R.id.edit_image3);
        edit_Title=(EditText)findViewById(R.id.edit_editText2);
        edit_desc=(EditText)findViewById(R.id.edit_editText4);
        edit_price=(EditText)findViewById(R.id.edit_editText5);
        edit_loc=(TextView)findViewById(R.id.edit_textView26);
        edit_Title.setText(ph.getTitle());
        edit_desc.setText(ph.getDescription());
        edit_price.setText(ph.getPrice());
        if(ph.getLocation()!=null)edit_loc.setText(ph.getLocation());

        Spinner spinner = (Spinner) findViewById(R.id.edit_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        if (ph.getCategory() != null) {
            int spinnerPosition = adapter.getPosition(ph.getCategory());
            spinner.setSelection(spinnerPosition);
        }




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
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        if (requestCode == 1)
            image1.setImageBitmap(bitmap);
        else if (requestCode == 2)
            image2.setImageBitmap(bitmap);
        else if (requestCode == 3)
            image3.setImageBitmap(bitmap);


    }


}