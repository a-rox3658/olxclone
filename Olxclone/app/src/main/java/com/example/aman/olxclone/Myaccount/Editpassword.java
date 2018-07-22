package com.example.aman.olxclone.Myaccount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Editpassword extends AppCompatActivity implements GetJsonEditnamepass.OnDataAvailable{
    User ph;
    EditText oldp;
    EditText newp;
    Data x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editpassword);
        Intent intent = getIntent();
         ph = (User) intent.getSerializableExtra("USER_TRANSFER");
        Button setp=(Button)findViewById(R.id.button11);
        oldp=(EditText)findViewById(R.id.editText6);
        newp=(EditText)findViewById(R.id.editText7);
        x =(Data)getApplicationContext();
        setp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oldp.getText().toString().equals(ph.getPassword())){
                    if(newp.getText().toString().length()>=5){

                        GetJsonEditnamepass getUserRawData=new GetJsonEditnamepass(Editpassword.this,"http://askrealone.com/mydealoffers/edit_user.php",x.my_id,newp.getText().toString(),null,null);
                        getUserRawData.execute(" ");
                    }
                    else{Toast.makeText(Editpassword.this,"Password Length At Least 5",Toast.LENGTH_SHORT).show();}

                }
                else{
                    Toast.makeText(Editpassword.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    @Override
    public void onDataAvailable(String data, DownloadStatus status) {
        Toast.makeText(Editpassword.this,data,Toast.LENGTH_SHORT).show();
        if(data.equals("User edited successfully")){
//            Intent intent = new Intent(Editname.this, MyaccountEdit.class);
//            startActivity(intent);
            navigateUpFromSameTask(this);
        }

    }
}
