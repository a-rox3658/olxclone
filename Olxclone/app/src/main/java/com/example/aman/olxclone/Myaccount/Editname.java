package com.example.aman.olxclone.Myaccount;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aman.olxclone.DownloadStatus;
import com.example.aman.olxclone.DummyData.Data;
import com.example.aman.olxclone.DummyData.User;
import com.example.aman.olxclone.R;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

public class Editname extends AppCompatActivity implements GetJsonEditnamepass.OnDataAvailable {
//    ProgressDialog loading = null;
    EditText fn;
    EditText ln;
    private static final String TAG = "lolk";
    Data x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editname);
        Intent intent = getIntent();
        User ph = (User) intent.getSerializableExtra("USER_TRANSFER");
        fn=(EditText)findViewById(R.id.editText);
       ln=(EditText)findViewById(R.id.editText3);
        fn.setText(ph.getFirstname());
        ln.setText(ph.getLastname());

    }

    @Override
    protected void onResume() {
        super.onResume();
       x =(Data)getApplicationContext();
        Button savename=(Button)findViewById(R.id.button5);
        savename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetJsonEditnamepass getUserRawData=new GetJsonEditnamepass(Editname.this,"http://askrealone.com/mydealoffers/edit_user.php",x.my_id,null,fn.getText().toString(),ln.getText().toString());
                getUserRawData.execute(" ");

            }
        });
    }

    @Override
    public void onDataAvailable(String data, DownloadStatus status) {
        Toast.makeText(Editname.this,data,Toast.LENGTH_SHORT).show();
        if(data.equals("User edited successfully")){
//            Intent intent = new Intent(Editname.this, MyaccountEdit.class);
//            startActivity(intent);
            navigateUpFromSameTask(this);
        }


    }
}
