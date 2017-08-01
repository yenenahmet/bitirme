package com.example.ahmet.erasmusapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.FoursquareApi.foursquare;
import com.example.ahmet.erasmusapp.QrKodKontrolet;
import android.view.animation.Interpolator;
import com.example.ahmet.erasmusapp.Adapter.GörevAdimlariAModel;
import com.example.ahmet.erasmusapp.Adapter.GörevAdimlariAdapter;
import com.example.ahmet.erasmusapp.Model.GörevAdimleriModel;
import com.example.ahmet.erasmusapp.Service.GörevAdimlari;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
public class GorevAdimlari extends AppCompatActivity   {
    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<GörevAdimlariAModel> list = new ArrayList<GörevAdimlariAModel>();
    public static int görevid;
    public static RecyclerView recyclerView;
    public static GörevAdimlariAdapter adapter;
    public String qrdanDönen;
    public static int Görevid,Adimid;
    public static Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gorev_adimlari);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
       görevid = extras.getInt("görevid");
        mContext = getApplicationContext();
        WifiManager wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        boolean enabled = wifiManager.setWifiEnabled(true);
        Log.e("wifiDurum", String.valueOf(enabled));
      // Location Servis
        // Adimlari getir
        recyclerView = (RecyclerView)findViewById(R.id.görevRView);
        adimlarigöster();
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.Refreshlayoutgörevadimlari);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        // burada ise Swipe Reflesh olduğunda ne yapacaksanız onu eklemeniz yeterlidir. Örneğin bir listeyi clear edebilir yada yeniden veri doldurabilirsiniz.
                        list.clear();
                        adimlarigöster();
                        recyclerView.refreshDrawableState();
                    }
                }, 1500);
            }
        });
    }
    public void KontrolQrkod(Context mcontext,RecyclerView recyclerView,GörevAdimlariAdapter adapter){
        this.adapter = adapter;
       this.recyclerView = recyclerView;
        Log.e("adapatenGeldiQrokuma==","Geldiiiiiiiiiiiiiii");
        IntentIntegrator integrator=new IntentIntegrator((Activity) mcontext);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Tarama");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result= IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "iptal", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Başarılı"+ result, Toast.LENGTH_SHORT).show();
                    QrKodKontrolet qr = new QrKodKontrolet();
                Log.e("Görevvid =",qr.adimikontrolet(Görevid).toString());
                    if (result.getContents().trim().toString().equals(qr.adimikontrolet(Görevid).trim().toString())) {
                        ProgressDialog progressDialog = new ProgressDialog(GorevAdimlari.this);
                        progressDialog.setMessage("Görev Tamamlanıyor...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        foursquare model1 = new foursquare(TabbedActivity._curLat,TabbedActivity._curLng,GorevAdimlari.this);
                        model1.ServisiDoldurPost(Adimid,Görevid);
                        progressDialog.cancel();
                        adimlarigöster();
                    }else{
                        Log.e("Kamera açıldı.qrdönen=", "okunamadı");
                    }
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){

            this.finish();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void adimlarigöster(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GörevAdimlari servis = retrofit.create(GörevAdimlari.class);
        Call<GörevAdimleriModel[]> call = servis.getGörevAdimlariModel(LoginAct.userid, görevid);
        call.enqueue(new Callback<GörevAdimleriModel[]>() {
            @Override
            public void onResponse(Response<GörevAdimleriModel[]> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    for(GörevAdimleriModel modelvalues:response.body()){
                        GörevAdimlariAModel items = new GörevAdimlariAModel();
                        items.setAdımId(modelvalues.AdımId);
                        items.setAdimAdi(modelvalues.AdimAdi);
                        items.setAdimResmi(modelvalues.AdimResmi);
                        items.setTamamlanmaDurumu(modelvalues.TamamlanmaDurumu);
                        list.add(items);
                    }
                    adapter = new GörevAdimlariAdapter(GorevAdimlari.this, list,görevid,recyclerView,adapter);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 1);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    recyclerView.refreshDrawableState();
                }else {
                    Toast.makeText(getApplicationContext(), "Servise Baglanamadi !!", Toast.LENGTH_LONG).show();
                }


            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "Servise Baglanamadi !!", Toast.LENGTH_LONG).show();
            }
        });
    }


}
