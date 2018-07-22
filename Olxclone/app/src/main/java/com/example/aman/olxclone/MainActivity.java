package com.example.aman.olxclone;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aman.olxclone.DummyData.Data;
import com.example.aman.olxclone.DummyData.User;
import com.example.aman.olxclone.Home_sell.Home_Sell;
import com.example.aman.olxclone.MyWallet.Menu_MyWallet;
import com.example.aman.olxclone.MyWishlist.Menu_Wishlist;
import com.example.aman.olxclone.Myaccount.GetUserJsonData;
import com.example.aman.olxclone.Myaccount.Menu_MyAccount;
import com.example.aman.olxclone.Myads.Menu_MyAds;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,GetUserJsonData.OnDataAvailable{
    private static final String TAG = "MainActivity";
    public  String m_Text="0" ;
    ProgressDialog loading = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Log.d(TAG, "onCreate:"+a.users.toString()+a.all_ads.toString());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//
//
//        });


//
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
//        if(!previouslyStarted) {
//            SharedPreferences.Editor edit = prefs.edit();
//            edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
//            edit.commit();
//            box();
//        }


        loading = new ProgressDialog(this);
        loading.setCancelable(true);
        loading.setMessage("Loading....");
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();

        Log.d(TAG, "onCreate:yo1 "+m_Text);


        Log.d(TAG, "onCreate:yo2 "+m_Text);

        Button Place_ad =(Button)findViewById(R.id.putad_button);
        Place_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inten = new Intent(MainActivity.this, Home_Sell.class);

                startActivity(inten);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Data x =(Data)getApplicationContext();

        GetUserJsonData getUserRawData=new GetUserJsonData(this,"http://askrealone.com/mydealoffers/user_details.php",x.my_id);
        getUserRawData.execute(" ");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            Intent intent = new Intent(this, Menu_MyAccount.class);

            startActivity(intent);



        } else if (id == R.id.nav_gallery) {

            Intent intent = new Intent(this, Menu_MyWallet.class);

            startActivity(intent);


        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this,Menu_MyAds.class);

            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(this,Menu_Wishlist.class);

            startActivity(intent);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
            if(previouslyStarted) {
                SharedPreferences.Editor edit = prefs.edit();
                edit.putBoolean(getString(R.string.pref_previously_started), Boolean.FALSE);
                edit.commit();
                Intent intent = new Intent(this,MainActivity.class);

                startActivity(intent);
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void box(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("LOGIN");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = new String((input.getText()).toString());
                Data a=(Data)getApplicationContext();
                a.my_id=new String(m_Text);
//                Log.d(TAG, "onCreate:yo "+m_Text);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();



    }

    @Override
    public void onDataAvailable(User data, DownloadStatus status) {
        if(status == DownloadStatus.OK){


            TextView name =(TextView)findViewById(R.id.TextName);
            TextView no =(TextView)findViewById(R.id.textView);
             name.setText(data.getProfilename());
             no.setText(data.getPhoneno());
            ImageView im=(ImageView)findViewById(R.id.imageViewxx);

            if((data.getProfilephoto() == null) ) {
            im.setImageResource(R.mipmap.deft_imgt_round);
        } else {

            Picasso.with(this).load(data.getProfilephoto())
                    .error(R.mipmap.deft_imgt_round)
                    .placeholder(R.mipmap.deft_imgt_round)
                    .into(im);

        }
        loading.dismiss();

    }}
}
