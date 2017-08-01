package com.example.ahmet.erasmusapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.example.ahmet.erasmusapp.Model.ArkadasEkleModel;
import com.example.ahmet.erasmusapp.Model.ArkadasEkleModelCB;
import com.example.ahmet.erasmusapp.Model.ArkadaslıktanCıkarModel;
import com.example.ahmet.erasmusapp.Model.ArkadaslıktanCıkarModelCB;
import com.example.ahmet.erasmusapp.Model.BaskasınınProfileniGecModel;
import com.example.ahmet.erasmusapp.Service.ArkadasEkleServis;
import com.example.ahmet.erasmusapp.Service.ArkadasliktanCıkarServis;
import com.example.ahmet.erasmusapp.Service.BaskaProfileGecServis;
import com.example.ahmet.erasmusapp.picasso.CircleTransformation;
import com.squareup.picasso.Picasso;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class BaskaProfileGec extends AppCompatActivity  {
int BaskaProfilid ;
    public ImageView profilresmi;
    public TextView Adsoyad;
    private TextView progsesbarText;
    public TextView profilyazisi;
    public ImageView btnArk;
    public static String email;
    private ProgressBar _progressBar;
    private ImageView Arkekle;
    private ImageView Arkcikar;
    private Button btnMesajGönder;
    private  String KullaniciAdi;
    private  String KullaniciProfilResmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baska_profile_gec);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        BaskaProfilid = extras.getInt("BaskaId");
        Arkekle =(ImageView)findViewById(R.id.arkekle);
        btnMesajGönder = (Button)findViewById(R.id.MesajgönderButton);
        btnMesajGönder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
// ozelmesaja ekle
                Intent nt = new Intent(getApplicationContext(), OzelMesaj.class);
                nt.putExtra("BaskaId",BaskaProfilid);
                nt.putExtra("Adi",KullaniciAdi);
                nt.putExtra("profilResmiBaska",KullaniciProfilResmi);
                startActivity(nt);
            }
        });
        Arkekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// post atılcak
                if( arkadasekle()) {
                    Toast.makeText(getApplicationContext(), "Arkadaş olarak eklendi.", Toast.LENGTH_LONG).show();
                    Arkekle.setVisibility(View.INVISIBLE);
                    Arkcikar.setVisibility(View.VISIBLE);
                }
                profiligoster();
            }
        });
        Arkcikar =(ImageView)findViewById(R.id.arkçıkar);
        Arkcikar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BaskaProfileGec.this);
                alertDialogBuilder.setTitle("Arkadaşlıktan çıkarmak istediğinizden eminmisiniz ?");
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Evet",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                if(ArkadasliktanCikar()){
                                    Toast.makeText(getApplicationContext(), "Arkadaşlıktan çıkartıldı.", Toast.LENGTH_LONG).show();
                                    Arkekle.setVisibility(View.VISIBLE);
                                    Arkcikar.setVisibility(View.INVISIBLE);
                                }
                            }
                        })
                        .setNegativeButton("Hayır",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                profiligoster();
            }

        });
        Adsoyad = (TextView)findViewById(R.id.baskaprofil_KUllanıcıAdı);
        profilresmi = (ImageView)findViewById(R.id.baskaprofil_profilresmi);
        profilyazisi = (TextView)findViewById(R.id.baskaprofil_profilyazısı);
        btnArk = (ImageView)findViewById(R.id.baskaprofil_arkadaslar);
        _progressBar = (ProgressBar)findViewById (R.id.kullanıcıbaskaprfil_ProgressBar);
        progsesbarText =(TextView)findViewById(R.id.kullanıcıbaskaprfil_progsesbarText);
        profiligoster();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            this.finish();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void profiligoster(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaskaProfileGecServis servis = (BaskaProfileGecServis) retrofit.create(BaskaProfileGecServis.class);
        Call<BaskasınınProfileniGecModel[]> call = servis.getBaskaProfil(LoginAct.userid,BaskaProfilid);
        call.enqueue(new Callback<BaskasınınProfileniGecModel[]>() {
            @Override
            public void onResponse(Response<BaskasınınProfileniGecModel[]> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                for (BaskasınınProfileniGecModel modelvalues : response.body()) {
                    KullaniciAdi =modelvalues.getKullaniciAdi();
                    KullaniciProfilResmi = modelvalues.getProfilResmi();
                    //istekgönderilenid Null sa arkadaş değildir
                    if(modelvalues.getMyUserid() == 0 && modelvalues.getIstekGönderilenid() ==0){

                        // arkadaşlık isteği yolluyabilir acık
                        // Ne arkadaş ne istek aatmış
                        getSupportActionBar().setTitle("Arkadaşınız değil");
                        Log.e("Baskasınınprofilinegeç", "Ne arkadaş ne istek yollamış");
                        Arkekle.setVisibility(View.VISIBLE);
                        Arkcikar.setVisibility(View.INVISIBLE);
                    }else if( modelvalues.getIstekGönderilenid() ==LoginAct.userid){
                        // arkadaşlar // arkadaşsa meesaj atma buttonunu aktif yapabiliriz değilse
                        // mesaj butonu aktif olmucaktır
                        getSupportActionBar().setTitle("Arkadaşınız");
                        Log.e("Baskasınınprofilinegeç", "ARkadaşlar");
                        Arkekle.setVisibility(View.INVISIBLE);
                        Arkcikar.setVisibility(View.VISIBLE);
                        btnMesajGönder.setVisibility(View.VISIBLE);
                    }else if(modelvalues.getMyUserid() == LoginAct.userid && modelvalues.getIstekGönderilenid() ==0){
                        // Arkadaş değil fakat , arkadaşlık isteği atmış
                        getSupportActionBar().setTitle("Arkadaşlık isteği bekleniyor...");
                        Toast.makeText(getApplicationContext(), "Arkadaşlık isteği onaylanmamış !!!", Toast.LENGTH_LONG).show();
                        Log.e("Baskasınınprofilinegeç", "ARkadaşlar değiller fakat istek yollanmıştır");
                        Arkekle.setVisibility(View.INVISIBLE);
                        Arkcikar.setVisibility(View.VISIBLE);
                        finish();
                    }
                    Log.e("Baskaprofilinşeyleri = ",modelvalues.getKullaniciAdi() +" + "+String.valueOf(modelvalues.getBitengörevSayis()).toString());
                    // myuserid Nullsa istek atılmamamıştır
                    Adsoyad.setText(modelvalues.getKullaniciAdi());
                    Picasso.with(getApplicationContext()).load(MyApi.URL_IMAGES+modelvalues.getProfilResmi()).transform(new CircleTransformation()).into(profilresmi);
                    profilyazisi.setText(modelvalues.getProfilYazısı());
                    _progressBar.setProgress(modelvalues.getBitengörevSayis());
                    double x = ((modelvalues.getBitengörevSayis() * 5) / 2);
                    progsesbarText.setText("% "+ String.valueOf(x).toString());

                }
               }
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "Hata oluştu !!!", Toast.LENGTH_LONG).show();
            }
        });
    }
    boolean durum = false;
    public boolean arkadasekle(){
       // final boolean[] durum = {false};
        final ProgressDialog progressDialog = new ProgressDialog(BaskaProfileGec.this);
        progressDialog.setMessage("İstek Gönderiliyor...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ArkadasEkleServis servis = retrofit.create(ArkadasEkleServis.class);
        ArkadasEkleModel model = new ArkadasEkleModel(String.valueOf(BaskaProfilid).toString(),String.valueOf(LoginAct.preferences.getInt("userid",0)).toString());
        Call<ArkadasEkleModelCB> call = servis.arkadasekleServis(model);
        call.enqueue(new Callback<ArkadasEkleModelCB>() {
            @Override
            public void onResponse(Response<ArkadasEkleModelCB> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    Toast.makeText(getApplicationContext(), response.body().getName(), Toast.LENGTH_LONG).show();
                    Log.e("useridarkekle", String.valueOf(response.body().getUserId()).toString());
                    durum =true;

                }else {
                    Toast.makeText(getApplicationContext(), "Hata oluştu !!!", Toast.LENGTH_LONG).show();
                    durum=false;
                }
                progressDialog.cancel();
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "Hata oluştu !!!", Toast.LENGTH_LONG).show();
                durum=false;
                progressDialog.cancel();
            }
        });
        finish();
return durum;
    }
        boolean durumcikar =false;
        public boolean ArkadasliktanCikar(){
             Retrofit retrofit = new Retrofit.Builder()
              .baseUrl(MyApi.URL)
               .addConverterFactory(GsonConverterFactory.create())
              .build();
            ArkadasliktanCıkarServis servis = retrofit.create(ArkadasliktanCıkarServis.class);
            ArkadaslıktanCıkarModel model = new ArkadaslıktanCıkarModel(String.valueOf(LoginAct.userid).toString(),String.valueOf(BaskaProfilid).toString());
            Call<ArkadaslıktanCıkarModelCB> call = servis.arkadasliktanCıkarServis(model);
            call.enqueue(new Callback<ArkadaslıktanCıkarModelCB>() {
                @Override
                public void onResponse(Response<ArkadaslıktanCıkarModelCB> response, Retrofit retrofit) {
                    if(response.isSuccess()){
                        Toast.makeText(getApplicationContext(), response.body().getName(), Toast.LENGTH_LONG).show();
                        Log.e("useridarkekle", String.valueOf(response.body().getUserId()).toString());
                        durumcikar =true;
                    }else {
                        Toast.makeText(getApplicationContext(), "Hata oluştu !!!", Toast.LENGTH_LONG).show();
                        durumcikar=false;
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getApplicationContext(), "Hata oluştu !!!", Toast.LENGTH_LONG).show();
                    durumcikar=false;
                }
            });
            finish();
            return durumcikar;
    }
}
