package com.example.aman.olxclone.Home_sell;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.aman.olxclone.DownloadStatus;
import com.example.aman.olxclone.GetUserRawData;

import org.json.JSONException;
import org.json.JSONObject;

public class Home_selljsondata extends AsyncTask<String,Void,String> implements GetUserRawData.OnDownloadComplete {

    private static final String TAG = "GetUserJsonData";

    private final OnDataAvailable mCallBack;
    private boolean runningOnSameThread = false;


    private String mBaseURL;
    private String mAdtitle;
    private String mAddesc;
    private String mUserid;
    private String mCatid;
    private String mAdloc;
    private String mAdprice;
    private String userMe = null;

    interface OnDataAvailable {
        void onDataAvailable(String data, DownloadStatus status);
    }
    public Home_selljsondata(OnDataAvailable callBack, String baseURL, String adtitle,String addesc,String userId,String catid,String adloc,String adprice) {

        mBaseURL = baseURL;
        mCallBack = callBack;
        mAdtitle = adtitle;
        mAddesc=addesc;
        mUserid=userId;
        mCatid=catid;
        mAdloc=adloc;
        mAdprice=adprice;

    }

    void executeOnSameThread(String searchCriteria) {
        Log.d(TAG, "executeOnSameThread starts");
        runningOnSameThread = true;
        String destinationUri = createUri(mAdtitle, mAddesc, mUserid, mCatid, mAdloc, mAdprice);

        GetUserRawData getRawData = new GetUserRawData(this);
        getRawData.execute(destinationUri);
        Log.d(TAG, "executeOnSameThread ends");
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "onPostExecute starts");

        if(mCallBack != null) {
            mCallBack.onDataAvailable(userMe, DownloadStatus.OK);
        }
        Log.d(TAG, "onPostExecute ends");    }

    @Override
    protected String doInBackground(String... params) {
        Log.d(TAG, "doInBackground starts");
        String destinationUri = createUri(mAdtitle, mAddesc, mUserid, mCatid, mAdloc, mAdprice);
        GetUserRawData getRawData = new GetUserRawData(this);
        getRawData.runInSameThread(destinationUri);
        Log.d(TAG, "doInBackground ends");
        return userMe;
    }



    private String createUri(String mAdtitle,String mAddesc,String mUserid,String mCatid,String mAdloc,String mAdprice) {
        Log.d(TAG, "createUri starts");

        return Uri.parse(mBaseURL).buildUpon()
                .appendQueryParameter("ad_title", mAdtitle)
                .appendQueryParameter("ad_description", mAddesc)
                .appendQueryParameter("user_id", mUserid)
                .appendQueryParameter("cat_id", mCatid)
                .appendQueryParameter("location", mAdloc)
                .appendQueryParameter("price", mAdprice)
                .build().toString();
    }
    @Override
    public void onDownloadComplete(String data, DownloadStatus status) {
        Log.d(TAG, "onDownloadComplete starts. Status = " + status);

        if(status == DownloadStatus.OK) {

            try {
                JSONObject jsonData = new JSONObject(data);
                if (jsonData.getString("status").equalsIgnoreCase("true")&&jsonData.getString("result").equalsIgnoreCase("Ad Added Successfully"))
                { userMe=new String(jsonData.getString("result"));


//                    Toast.makeText(this,"fxvxdv"+jsonData.getString("result"), Toast.LENGTH_SHORT).show();
                }
                else if(jsonData.getString("status").equalsIgnoreCase("false"))
                {
                    userMe=new String(jsonData.getString("result"));

                }



                Log.d(TAG, "onDownloadComplete " );
            }
            catch(JSONException jsone) {
                jsone.printStackTrace();
                Log.e(TAG, "onDownloadComplete: Error processing Json data " + jsone.getMessage());
                status = DownloadStatus.FAILED_OR_EMPTY;
            }
        }
        if(runningOnSameThread && mCallBack != null) {
            // now inform the caller that processing is done - possibly returning null if there
            // was an error
            mCallBack.onDataAvailable(userMe, status);
        }

        Log.d(TAG, "onDownloadComplete ends");



    }
}
