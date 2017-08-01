package com.example.ahmet.erasmusapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.Model.LogonTask;
import com.example.ahmet.erasmusapp.Model.LogonTaskcb;
import com.example.ahmet.erasmusapp.Service.LogonService;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginAct extends ActionBarActivity {
   public EditText edtemail;
    public EditText edtpassword;
    public static int userid;
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView kayit = (TextView)findViewById(R.id.Kayıtol);
        setWifiDataOn(true);

        assert kayit != null;
        kayit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), KayitAct.class);
            startActivity(intent);
        }
    });
        Button btnlgn = (Button)findViewById(R.id.button2);
        edtemail = (EditText)findViewById(R.id.editText5);
        edtpassword =(EditText)findViewById(R.id.editText6);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();


        if(preferences.getBoolean("login", false)){
            LoginAct.userid = preferences.getInt("userid",0);
           Intent intent = new Intent(getApplicationContext(), TabbedActivity.class);
           startActivity(intent);
           finish();
       }
        btnlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

public void login(){
    progressDialog = new ProgressDialog(LoginAct.this);
    progressDialog.setMessage("Bağlanıyor...");
    progressDialog.setCancelable(false);
    progressDialog.show();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MyApi.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    LogonService lgn = retrofit.create(LogonService.class);
    LogonTask task =new LogonTask(edtemail.getText().toString(), edtpassword.getText().toString());
    Call<LogonTaskcb> call = lgn.createTask(task);
    call.enqueue(new Callback<LogonTaskcb>() {
        @Override
        public void onResponse(Response<LogonTaskcb> response, Retrofit retrofit) {
           // Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();

            Boolean aktiflik = new Boolean(response.body().getName()).booleanValue();

                    if(response.body().getUserId() !=0) {
                   //     Toast.makeText(getApplicationContext(), String.valueOf(response.body().getUserId()).toString(), Toast.LENGTH_LONG).show();
                        editor.putBoolean("login", true);
                        editor.putInt("userid",response.body().getUserId());
                        editor.commit();
                        userid = response.body().getUserId();
                        progressDialog.cancel();
                        Intent intent = new Intent(getApplicationContext(), TabbedActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        progressDialog.cancel();
                        Toast.makeText(getApplicationContext(),"Kullanici Bulunamadi !!", Toast.LENGTH_LONG).show();
                    }

        }

        @Override
        public void onFailure(Throwable t) {
            String merror = t.getLocalizedMessage();
            progressDialog.cancel();
            Toast.makeText(getApplicationContext(), "Fail Connections !", Toast.LENGTH_LONG).show();
        }
    });

}

    protected void setWifiDataOn(boolean enabled) {
        WifiManager wifiMgr = (WifiManager)this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiMgr.setWifiEnabled(enabled);
    }




}
