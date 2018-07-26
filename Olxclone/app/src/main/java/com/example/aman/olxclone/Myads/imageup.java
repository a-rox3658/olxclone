package com.example.aman.olxclone.Myads;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aman.olxclone.DownloadStatus;
import com.example.aman.olxclone.Myaccount.EditProfilepic;
import com.example.aman.olxclone.Myaccount.GetJsonEditnamepass;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class imageup extends AsyncTask<String,Void,String>{
    private final OnDataAvailable mCallBack;
    Bitmap btm;
    Context bb;
    String urlup="http://askrealone.com/mydealoffers/edit_user.php";

    private static final String TAG = "GetUserJsonData";
    @Override
    protected void onPostExecute(String s) {
        if(mCallBack != null) {
            mCallBack.onDataAvailable( s);
        }
    }
    public interface OnDataAvailable {
        void onDataAvailable(String data);
    }
    public imageup(imageup.OnDataAvailable callBack, Bitmap a, Context b) {


        mCallBack = callBack;
        btm=a;
         bb=b;
    }

    @Override
    protected String doInBackground(String... strings) {
        String v;
        final StringRequest srqs= new StringRequest(com.android.volley.Request.Method.POST, urlup, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(bb,"response"+response,Toast.LENGTH_LONG).show();
                Log.d(TAG, "onResponse: "+response+"   ");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(bb,"error"+error,Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onErrorResponse: "+error+error.networkResponse.statusCode);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> prams=new HashMap<>();
                String id=imagetostring(btm);
                prams.put("user_id","1");prams.put("image",id);
//                        prams.put("first_name","vv");



                return prams;
            }


        };
        RequestQueue requestQueue = Volley.newRequestQueue(bb);
        requestQueue.add(srqs);


        return null;
    }

    private String imagetostring(Bitmap btm){

        ByteArrayOutputStream outputStream =new ByteArrayOutputStream();
        btm.compress(Bitmap.CompressFormat.JPEG,50,outputStream);
        byte[] imageBytes=outputStream.toByteArray();
        String encoded= Base64.encodeToString(imageBytes,Base64.DEFAULT);
        Log.d(TAG, "imagetostring: "+encoded);
        return encoded;
    }
}
