package com.example.aman.olxclone.Myaccount;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aman.olxclone.DownloadStatus;
import com.example.aman.olxclone.DummyData.User;
import com.example.aman.olxclone.Myads.imageup;
import com.example.aman.olxclone.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class EditProfilepic extends AppCompatActivity implements imageup.OnDataAvailable {
    User ph;
    ImageView im;
    Button choose;
    Button upload;
    private static final String TAG = "FragmentActivity";
    int a = 1;
    Bitmap btm;
    String urlup = "http://askrealone.com/mydealoffers/edit_user.php";
    final int CODE_GALLERY_REQUEST = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profilepic);
        Intent intent = getIntent();
        ph = (User) intent.getSerializableExtra("USER_TRANSFER");
        im = (ImageView) findViewById(R.id.imageViewsdf);
        choose = (Button) findViewById(R.id.button12);
        upload = (Button) findViewById(R.id.button14);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(EditProfilepic.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CODE_GALLERY_REQUEST);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                imageup imageup = new imageup(EditProfilepic.this, btm, EditProfilepic.this);
                imageup.execute(" ");
                //                final StringRequest srqs= new StringRequest(com.android.volley.Request.Method.POST, urlup, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(),"response"+response,Toast.LENGTH_LONG).show();
//                        Log.d(TAG, "onResponse: "+response+"   ");
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(),"error"+error,Toast.LENGTH_SHORT).show();
//                        Log.d(TAG, "onErrorResponse: "+error+error.networkResponse.statusCode);
//                    }
//                }){
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String,String> prams=new HashMap<>();
//                        String id=imagetostring(btm);
//                        prams.put("user_id","1");prams.put("image",id);
////                        prams.put("first_name","vv");
//
//
//
//                        return prams;
//                    }
//
//
//                };
//                RequestQueue requestQueue = Volley.newRequestQueue(EditProfilepic.this);
//                requestQueue.add(srqs);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CODE_GALLERY_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "choooose Image"), CODE_GALLERY_REQUEST);
            } else {
                Toast.makeText(getApplicationContext(), "sorry no permission", Toast.LENGTH_SHORT).show();
            }
            return;
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private String imagetostring(Bitmap btm) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        btm.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        Log.d(TAG, "imagetostring: size"+ btm.getRowBytes()*btm.getHeight());
        byte[] imageBytes = outputStream.toByteArray();
        String encoded = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Log.d(TAG, "imagetostring: " + encoded);
        return encoded;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_GALLERY_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri flepath = data.getData();
            InputStream ips;
            try {
//                ips = getContentResolver().openInputStream(flepath); BitmapFactory.decodeStream(ips);
                btm =getBitmap(flepath);
                Log.d(TAG, "imagetostring: size"+ btm.getByteCount());


                Log.d(TAG, "onActivityResult: yoink");
                im.setImageBitmap(btm);
                im.setImageURI(flepath);
            } catch (Exception e) {
                Log.d(TAG, "onActivityResult: ");
                e.printStackTrace();
            }


        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        im = (ImageView) findViewById(R.id.imageViewsdf);
        if (a == 1) {
            if ((ph.getProfilephoto() == null)) {
                im.setImageResource(R.mipmap.deft_imgt);
            } else {

                Picasso.with(this).load(ph.getProfilephoto())
                        .error(R.mipmap.deft_imgt)
                        .placeholder(R.mipmap.deft_imgt)
                        .into(im);

            }
            a++;
        }
    }

    @Override
    public void onDataAvailable(String data) {

    }

    private Bitmap getBitmap(Uri path) {

        Uri uri = (path);
        InputStream in = null;
        try {
            final int IMAGE_MAX_SIZE = 120000; // 1.2MP
            in = getContentResolver().openInputStream(uri);

            // Decode image size
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, options);
            in.close();


            int scale = 1;
            while ((options.outWidth * options.outHeight) * (1 / Math.pow(scale, 2)) >
                    IMAGE_MAX_SIZE) {
                scale++;
            }
            Log.d(TAG, "scale = " + scale + ", orig-width: " + options.outWidth + ",orig-height: " + options.outHeight);

            Bitmap resultBitmap = null;
            in = getContentResolver().openInputStream(uri);
            if (scale > 1) {
                scale--;
                // scale to max possible inSampleSize that still yields an image
                // larger than target
                options = new BitmapFactory.Options();
                options.inSampleSize = scale;
                resultBitmap = BitmapFactory.decodeStream(in, null, options);

                // resize to desired dimensions
                int height = resultBitmap.getHeight();
                int width = resultBitmap.getWidth();
                Log.d(TAG, "1th scale operation dimenions - width: " + width + ",height: " + height);

                double y = Math.sqrt(IMAGE_MAX_SIZE
                        / (((double) width) / height));
                double x = (y / height) * width;

                Bitmap scaledBitmap = Bitmap.createScaledBitmap(resultBitmap, (int) x,
                        (int) y, true);
                resultBitmap.recycle();
                resultBitmap = scaledBitmap;

                System.gc();
            } else {
                resultBitmap = BitmapFactory.decodeStream(in);
            }
            in.close();

            Log.d(TAG, "bitmap size - width: " + resultBitmap.getWidth() + ", height: " +
                    resultBitmap.getHeight());
            return resultBitmap;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }
}