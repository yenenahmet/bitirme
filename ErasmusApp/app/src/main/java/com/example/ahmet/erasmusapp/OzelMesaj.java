package com.example.ahmet.erasmusapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.Adapter.ArkadaslikistekleriAdapterModel;
import com.example.ahmet.erasmusapp.Adapter.ÖzelmesajDenemeAdapter;
import com.example.ahmet.erasmusapp.Model.MesajKutusuGidenModel;
import com.example.ahmet.erasmusapp.Model.MesajKutusuGidenModelCB;
import com.example.ahmet.erasmusapp.Model.MesajÖzelDenemeModel;
import com.example.ahmet.erasmusapp.Service.MesajKutusuGönderServis;
import com.example.ahmet.erasmusapp.TabFragmntlar.FragmentD;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class OzelMesaj extends AppCompatActivity {
    public  static List<MesajÖzelDenemeModel> list = new ArrayList<MesajÖzelDenemeModel>();
    private ÖzelmesajDenemeAdapter özelmesajDenemeAdapter;
    public static int BaskaProfilid;
    public static ListView mesajlist;
    public static String KullaniciAdi;
    public static String profilresmBaska;
    public static ImageView btngonder;
    public static Context OzelmesajContext;
    public static SQL_MesajVeritabanı dbHelper;
    private ProgressDialog progressDialog;
     EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ozel_mesaj);
        dbHelper = new SQL_MesajVeritabanı(getApplicationContext());
        OzelmesajContext = getApplicationContext();
        Bundle extras = getIntent().getExtras();
        BaskaProfilid = extras.getInt("BaskaId");
        KullaniciAdi = extras.getString("Adi");
        profilresmBaska =extras.getString("profilResmiBaska");
        getSupportActionBar().setTitle(KullaniciAdi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         btngonder = (ImageView) findViewById(R.id.btnözelmesajgönder);
        text = (EditText)findViewById(R.id.özelgöndertext);
        mesajlist = (ListView)findViewById(R.id.ozelmesajlist);
        MyFirebaseMessagingService.özelmesajList = (ListView)findViewById(R.id.ozelmesajlist);
        assert btngonder != null;
        btngonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MesajGönder(String.valueOf(BaskaProfilid).toString(),text.getText().toString());


            }
        });
        özelmesajDenemeAdapter = new ÖzelmesajDenemeAdapter(getApplicationContext(),dbHelper.getKullanıcıOZelmesajlarıçek(BaskaProfilid));
        mesajlist.setAdapter(özelmesajDenemeAdapter);
        özelmesajDenemeAdapter.notifyDataSetChanged();
    }
public  void MesajGeldi(String Kullanıcı){
    if(mesajlist != null){
        if(Integer.parseInt(Kullanıcı) == BaskaProfilid ){
            final ÖzelmesajDenemeAdapter özelmesajDenemeAdapter = new ÖzelmesajDenemeAdapter(OzelmesajContext,dbHelper.getKullanıcıOZelmesajlarıçek(BaskaProfilid));
            mesajlist.setAdapter(özelmesajDenemeAdapter);
            özelmesajDenemeAdapter.notifyDataSetChanged();
            mesajlist.refreshDrawableState();
            mesajlist.post(new Runnable() {
                @Override
                public void run() {
                    mesajlist.setSelection(özelmesajDenemeAdapter.getCount() - 1);
                }
            });
        }
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
    public void MesajGönder(String gönderilenuserid,String mesaj){
        progressDialog = new ProgressDialog(OzelMesaj.this);
        progressDialog.setMessage("Gönderiliyor...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MesajKutusuGönderServis serviss = retrofit.create(MesajKutusuGönderServis.class);
        MesajKutusuGidenModel model = new MesajKutusuGidenModel(String.valueOf(LoginAct.userid).toString(),gönderilenuserid,mesaj);
        Call<MesajKutusuGidenModelCB> call =  serviss.mesajgonderservis(model);
        call.enqueue(new Callback<MesajKutusuGidenModelCB>() {
            @Override
            public void onResponse(Response<MesajKutusuGidenModelCB> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    Toast.makeText(getApplicationContext(), "Gönderildi..", Toast.LENGTH_LONG).show();
                    SQL_Veritabanı_MesajModel model = new SQL_Veritabanı_MesajModel();
                    model.setBaskaUserid(BaskaProfilid);
                    model.setKullanıcıAdi(FragmentD.Ad);
                    model.setMesaj(text.getText().toString());
                    model.setTarihZaman(response.body().getUserId());
                    dbHelper.insertCountry(model);
                    özelmesajDenemeAdapter = new ÖzelmesajDenemeAdapter(getApplicationContext(),dbHelper.getKullanıcıOZelmesajlarıçek(BaskaProfilid));
                    mesajlist.setAdapter(özelmesajDenemeAdapter);
                    özelmesajDenemeAdapter.notifyDataSetChanged();
                    text.setText("");
                    progressDialog.cancel();
                }else{
                    Toast.makeText(getApplicationContext(), "Serviste Hata oluştu", Toast.LENGTH_LONG).show();
                    progressDialog.cancel();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), " Hata oluştu...", Toast.LENGTH_LONG).show();
                Log.e("OzelmesajHataolustu=",t.toString());
                progressDialog.cancel();
            }
        });
        mesajlist.post(new Runnable() {
            @Override
            public void run() {
                mesajlist.setSelection(özelmesajDenemeAdapter.getCount() - 1);
            }
        });
    }
}
