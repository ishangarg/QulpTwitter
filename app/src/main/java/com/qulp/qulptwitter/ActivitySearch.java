package com.qulp.qulptwitter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qulp.qulptwitter.Model.AuthorizationModel;
import com.qulp.qulptwitter.Request.Authorize;

import java.io.UnsupportedEncodingException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ishan-3306 on 7/6/2016.
 */
public class ActivitySearch extends AppCompatActivity {

    EditText searchText;
    Button searchBtn;
    String c_key = "y0bVmBb6qDQ9LAhJFIOJB5rxX";
    String c_secret = "XfWT0dt7uU3eA2VqoNsK5XXiiNPeJnzBjc76pFbHYzRQqIDCH3";

    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sp = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        if(!sp.contains("access_token") && !sp.contains("type")){
            authorizeApp();
        }

        // Toast.makeText(getApplicationContext(),"Auhtorized:  " + sp.getString("access_token",null) ,Toast.LENGTH_SHORT).show();


        searchText = (EditText) findViewById(R.id.et_search);
        searchBtn = (Button) findViewById(R.id.btn_search);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchTerm = searchText.getText().toString();

                Intent i = new Intent(ActivitySearch.this,ActivityTweets.class);
                i.putExtra("term",searchTerm);
                startActivity(i);
                //finish();
            }
        });
    }

    private void authorizeApp(){
        Authorize authorizeApp = ServiceGenerator.createService(Authorize.class,c_key,c_secret);
        authorizeApp.authorizeApp("client_credentials",new Callback<AuthorizationModel>() {
            @Override
            public void success(AuthorizationModel authorizatonModel, Response response) {
                String token = authorizatonModel.getAccessToken();
                String type = authorizatonModel.getTokenType();

                SharedPreferences sp = ActivitySearch.this.getSharedPreferences("MyPrefs",MODE_PRIVATE);
                SharedPreferences.Editor e = sp.edit();
                Log.d("TOken","token = " + token);
                e.putString("access_token",token);
                e.putString("access_type",type);
                e.commit();
               // Toast.makeText(getApplicationContext() , "Successfuly authroized app", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.d("RetrofitError",retrofitError.getMessage());
                Toast.makeText(getApplicationContext(),"Retorfit Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
