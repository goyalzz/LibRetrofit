package com.goyalzz.controler.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.goyalzz.R;
import com.goyalzz.core.BaseActivity;
import com.goyalzz.helper.RoundedTransformation;
import com.goyalzz.model.ResponseModel;
import com.goyalzz.utils.CustomCallBacks;
import com.goyalzz.utils.UtilitySingleton;
import com.squareup.picasso.Picasso;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class UserInfoForm extends BaseActivity implements OnMapReadyCallback {

    public SupportMapFragment fragment;
    public GoogleMap googlemap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();


        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ImageView image = (ImageView) findViewById(R.id.image);
        Picasso.with(getApplicationContext()).load("http://www.sun-softtech.com/images/itsme.jpg").transform(new RoundedTransformation(100, 1)).error(R.drawable.user).into(image);

        final EditText name = (EditText) findViewById(R.id.name);
        final EditText email = (EditText) findViewById(R.id.email);
        Button submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UtilitySingleton.getInstance(getApplicationContext()).validateEmail(email)) {
                    getRestService().postUserDetails(name.getText().toString(), email.getText().toString(), new CustomCallBacks<ResponseModel>(UserInfoForm.this, true) {
                        @Override
                        public void onSucess(ResponseModel arg0, Response arg1) {
                            Toast.makeText(getApplicationContext(), arg0.message, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(RetrofitError arg0) {
                            Toast.makeText(getApplicationContext(), arg0.getKind().toString() + " ERROR: " + arg0.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        fragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        fragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googlemap = googleMap;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_info_form, menu);
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
}
