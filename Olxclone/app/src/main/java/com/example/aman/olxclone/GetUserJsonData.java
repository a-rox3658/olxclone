package com.example.aman.olxclone;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetUserJsonData extends AsyncTask<String,Void,User>implements GetUserRawData.OnDownloadComplete {

    private static final String TAG = "GetUserJsonData";

    private final OnDataAvailable mCallBack;
    private boolean runningOnSameThread = false;


    private String mBaseURL;
    private String mUserId;
    private User userMe = null;

    interface OnDataAvailable {
        void onDataAvailable(User data, DownloadStatus status);
    }
    public GetUserJsonData(OnDataAvailable callBack, String baseURL, String userId) {

        mBaseURL = baseURL;
        mCallBack = callBack;
        mUserId = userId;
    }

    void executeOnSameThread(String searchCriteria) {
        Log.d(TAG, "executeOnSameThread starts");
        runningOnSameThread = true;
        String destinationUri = createUri(mUserId);

        GetUserRawData getRawData = new GetUserRawData(this);
        getRawData.execute(destinationUri);
        Log.d(TAG, "executeOnSameThread ends");
    }

    @Override
    protected void onPostExecute(User s) {
        Log.d(TAG, "onPostExecute starts");

        if(mCallBack != null) {
            mCallBack.onDataAvailable(userMe, DownloadStatus.OK);
        }
        Log.d(TAG, "onPostExecute ends");    }

    @Override
    protected User doInBackground(String... params) {
        Log.d(TAG, "doInBackground starts");
        String destinationUri = createUri(mUserId);

        GetUserRawData getRawData = new GetUserRawData(this);
        getRawData.runInSameThread(destinationUri);
        Log.d(TAG, "doInBackground ends");
        return userMe;
    }



    private String createUri(String mUserId) {
        Log.d(TAG, "createUri starts");

        return Uri.parse(mBaseURL).buildUpon()
                .appendQueryParameter("user_id", mUserId)
                .build().toString();
    }
    @Override
    public void onDownloadComplete(String data, DownloadStatus status) {
        Log.d(TAG, "onDownloadComplete starts. Status = " + status);

        if(status == DownloadStatus.OK) {
            userMe = new User(null,null,null,null,null,null);

            try {
                  JSONObject jsonData = new JSONObject(data);
                  if (jsonData.getString("status").equalsIgnoreCase("true"))
                  {JSONObject result =jsonData.getJSONObject("result");
                      userMe.setProfilename(result.getString("user_name"));

                      userMe.setPhoneno(result.getString("mobile"));
                      userMe.setProfilephoto(result.getString("image"));
                     userMe.setVerfied(result.getString("is_verified"));
                      userMe.setAdsNo(result.getString("ads_num"));
                      userMe.setAdsViews(result.getString("ads_views"));
                      userMe.setCreatedOn(result.getString("created_on"));
                      userMe.setIsActive(result.getString("is_active"));
                      userMe.setUserType(result.getString("user_type"));
                  }

//
//                    JSONObject jsonMedia = jsonPhoto.getJSONObject("media");
//                    String photoUrl = jsonMedia.getString("m");
//
//                    String link = photoUrl.replaceFirst("_m.", "_b.");
//
//                    Photo photoObject = new Photo(title, author, authorId, link, tags, photoUrl);
//                    mPhotoList.add(photoObject);

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
