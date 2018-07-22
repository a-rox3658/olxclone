package com.example.aman.olxclone.Myaccount;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.aman.olxclone.DownloadStatus;
import com.example.aman.olxclone.DummyData.User;
import com.example.aman.olxclone.GetUserRawData;

import org.json.JSONException;
import org.json.JSONObject;

public class GetJsonEditnamepass  extends AsyncTask<String,Void,String> implements GetUserRawData.OnDownloadComplete{
private static final String TAG = "GetUserJsonData";

private final OnDataAvailable mCallBack;
private boolean runningOnSameThread = false;


private String mBaseURL;
private String mUserId;
private String userMe = null;
    private String mPassword;
    private String mFname;
    private String mLname;
interface OnDataAvailable {
    void onDataAvailable(String data, DownloadStatus status);
}
    public GetJsonEditnamepass(GetJsonEditnamepass.OnDataAvailable callBack, String baseURL, String userId,String password,String fname,String lname) {

        mBaseURL = baseURL;
        mCallBack = callBack;
        mUserId = userId;
        mPassword=password;
        mFname=fname;
        mLname=lname;
    }

    void executeOnSameThread(String searchCriteria) {
        Log.d(TAG, "executeOnSameThread starts");
        runningOnSameThread = true;
        String destinationUri = createUri(mUserId,mPassword,mFname,mLname);

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
        String destinationUri = createUri(mUserId,mPassword,mFname,mLname);
        if (destinationUri!=null){
        GetUserRawData getRawData = new GetUserRawData(this);
        getRawData.runInSameThread(destinationUri);
        Log.d(TAG, "doInBackground ends");
        return userMe;}
        return "Error";
    }



    private String createUri(String mUserId,String mPassword,String mFname,String mLname) {
        Log.d(TAG, "createUri starts");
        Log.d(TAG, "createUri: "+ Uri.parse(mBaseURL).buildUpon().appendQueryParameter("user_id", mUserId).build().toString());
        if (mPassword!=null){
            return Uri.parse(mBaseURL).buildUpon().appendQueryParameter("password",mPassword)
                    .appendQueryParameter("user_id", mUserId)
                    .build().toString();
        }
        else if(mFname!=null&&mLname!=null){
            return Uri.parse(mBaseURL).buildUpon().appendQueryParameter("first_name",mFname).appendQueryParameter("last_name",mLname)
                    .appendQueryParameter("user_id", mUserId)
                    .build().toString();
        }

 return null;

    }
    @Override
    public void onDownloadComplete(String data, DownloadStatus status) {
        Log.d(TAG, "onDownloadComplete starts. Status = " + status);

        if(status == DownloadStatus.OK) {
            userMe = new String();

            try {
                JSONObject jsonData = new JSONObject(data);
                if (jsonData.getString("status").equalsIgnoreCase("true"))
                {String result =jsonData.getString("result");
                    userMe=result;
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
