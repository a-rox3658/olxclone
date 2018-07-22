package com.example.aman.olxclone.Myads;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.aman.olxclone.DownloadStatus;
import com.example.aman.olxclone.DummyData.Ads;
import com.example.aman.olxclone.GetUserRawData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Menu_MyAdsJsondata extends AsyncTask <String,Void,List<Ads>>implements GetUserRawData.OnDownloadComplete {
    private static final String TAG = "GetFlickrJsonData";
    ProgressDialog progressDialog;
    private List<Ads> mAdsList = null;
    private String mBaseURL;
    private String mUserId;

    private final OnDataAvailable mCallBack;
    private boolean runningOnSameThread = false;


    interface OnDataAvailable {
        void onDataAvailable(List<Ads> data, DownloadStatus status);
    }

    public Menu_MyAdsJsondata(OnDataAvailable callBack, String baseURL, String userId) {
        Log.d(TAG, "GetFlickrJsonData called");
        mBaseURL = baseURL;
        mCallBack = callBack;
        mUserId = userId;

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    void executeOnSameThread(String userid) {
        Log.d(TAG, "executeOnSameThread starts");
        runningOnSameThread = true;
        String destinationUri = createUri(userid);

        GetUserRawData getRawData = new GetUserRawData(this);
        getRawData.execute(destinationUri);
        Log.d(TAG, "executeOnSameThread ends");
    }

    @Override
    protected void onPostExecute(List<Ads> photos) {
        Log.d(TAG, "onPostExecute starts");

        if(mCallBack != null) {
            mCallBack.onDataAvailable(mAdsList, DownloadStatus.OK);
        }
        Log.d(TAG, "onPostExecute ends");
    }

    @Override
    protected List<Ads> doInBackground(String... params) {
        Log.d(TAG, "doInBackground starts");
        String destinationUri = createUri(mUserId);

        GetUserRawData getRawData = new GetUserRawData(this);
        getRawData.runInSameThread(destinationUri);
        Log.d(TAG, "doInBackground ends");
        return mAdsList;
    }

    private String createUri(String userid) {
        Log.d(TAG, "createUri starts");

        return Uri.parse(mBaseURL).buildUpon()
                .appendQueryParameter("user_id", userid)
                .build().toString();
    }

    @Override
    public void onDownloadComplete(String data, DownloadStatus status) {
        Log.d(TAG, "onDownloadComplete starts. Status = " + status);

        if(status == DownloadStatus.OK) {
            mAdsList = new ArrayList<>();

            try {
                JSONObject jsonData = new JSONObject(data);
                JSONArray itemsArray = jsonData.getJSONArray("result");

                for(int i=0; i<itemsArray.length(); i++) {
                    JSONObject jsonad = itemsArray.getJSONObject(i);
                    String title = jsonad.getString("ad_title");
                    String desc = jsonad.getString("ad_description");
                    String create = jsonad.getString("created_on");
                    String cat = jsonad.getString("cat_id");
                    String loc = jsonad.getString("location");
                    String pri = jsonad.getString("price");
                    String vie = jsonad.getString("views");
                    String lik = jsonad.getString("likes");
                    String useid = jsonad.getString("user_id");
                    String adid = jsonad.getString("ad_id");


                    Ads pObject = new Ads(title,null,desc,cat,loc,useid,"",pri,Integer.parseInt(vie),Integer.parseInt(lik));
                    mAdsList.add(pObject);

                    Log.d(TAG, "onDownloadComplete " + pObject.toString());
                }
            } catch(JSONException jsone) {
                jsone.printStackTrace();
                Log.e(TAG, "onDownloadComplete: Error processing Json data " + jsone.getMessage());
                status = DownloadStatus.FAILED_OR_EMPTY;
            }
        }

        if(runningOnSameThread && mCallBack != null) {
            // now inform the caller that processing is done - possibly returning null if there
            // was an error
            mCallBack.onDataAvailable(mAdsList, status);
        }

        Log.d(TAG, "onDownloadComplete ends");
    }
}
