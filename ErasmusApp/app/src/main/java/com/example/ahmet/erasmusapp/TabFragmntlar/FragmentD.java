package com.example.ahmet.erasmusapp.TabFragmntlar;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.ArkadasListesi;
import com.example.ahmet.erasmusapp.ArkadaslikistekleriGoster;
import com.example.ahmet.erasmusapp.KisiselProfilDuzenleme;
import com.example.ahmet.erasmusapp.LoginAct;
import com.example.ahmet.erasmusapp.Model.CikisyapModel;
import com.example.ahmet.erasmusapp.Model.CikisyapModelCB;
import com.example.ahmet.erasmusapp.Model.KullaniciProfil;
import com.example.ahmet.erasmusapp.MyApi;
import com.example.ahmet.erasmusapp.MyFirebaseCloud;
import com.example.ahmet.erasmusapp.OzelMesaj;
import com.example.ahmet.erasmusapp.R;
import com.example.ahmet.erasmusapp.SQL_MesajVeritabanı;
import com.example.ahmet.erasmusapp.Service.CikisyapServis;
import com.example.ahmet.erasmusapp.Service.KullaniciProfliService;
import com.example.ahmet.erasmusapp.TabbedActivity;
import com.example.ahmet.erasmusapp.gpsCheck;
import com.example.ahmet.erasmusapp.picasso.CircleTransformation;
import com.squareup.picasso.Picasso;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by Ahmet on 5.9.2016.
 */
public class FragmentD extends Fragment {

    public ImageView profilresmi;
    public TextView Adsoyad;
    private TextView progsesbarText;
    public TextView profilyazisi;
    public ImageView btnArk;
    public static String profilresmi1;
    public static String Ad;
    public static String email;
    public static String profilyazısı;
    private ProgressBar _progressBar;
    private ImageView btn_cikisyap;
    private ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_d_yeni, container, false);
        TabbedActivity.useridTanımla(getActivity());
        btn_cikisyap = (ImageView) view.findViewById(R.id.cikisyap_kullaniciprofil);
        Adsoyad = (TextView) view.findViewById(R.id.textView7);
        profilresmi = (ImageView) view.findViewById(R.id.imageView);
        profilyazisi = (TextView) view.findViewById(R.id.textView8);
        btnArk = (ImageView) view.findViewById(R.id.button7);
        _progressBar = (ProgressBar) view.findViewById(R.id.circularProgressBar);
        progsesbarText = (TextView) view.findViewById(R.id.progsesbarText);

        ImageView profilDüzenle = (ImageView) view.findViewById(R.id.profilidüzenlegec);
        profilDüzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profil = new Intent(getActivity(), KisiselProfilDuzenleme.class);
                startActivity(profil);
            }
        });
        profilidüzenle();
        btnArk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent arklist = new Intent(getActivity(), ArkadasListesi.class);
                startActivity(arklist);
            }
        });
        btn_cikisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setMessage("Çıkış yapmak istiyormusunuz ?");
                alertDialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog = new ProgressDialog(getActivity());
                        progressDialog.setMessage("Çıkış yapılıyor lütfen biraz bekleyin...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        // sqllite boşalt
                        SQL_MesajVeritabanı dbHelper = new SQL_MesajVeritabanı(getActivity());
                        dbHelper.cikis_VeritabanıTemizle();
                        // msssql serveri boşalt
                        SQl_Kullanici_Cikis();
                        dialog.dismiss();
                    }
                });
                alertDialog.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getActivity(), "Çıkış işlemi  iptal edildi !", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                alertDialog.show();

            }
        });

        return view;
        // return  true;
    }





public void SQl_Kullanici_Cikis(){
    // servise yolla Aktifliği false yap
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MyApi.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    CikisyapServis servis = retrofit.create(CikisyapServis.class);
    CikisyapModel model = new CikisyapModel(String.valueOf(LoginAct.userid).toString());
    Call<CikisyapModelCB> call = servis.cikisYapServis(model);
    call.enqueue(new Callback<CikisyapModelCB>() {
        @Override
        public void onResponse(Response<CikisyapModelCB> response, Retrofit retrofit) {
            if(response.isSuccess()){
                Log.e("BAŞARILI","SİLMEİŞLEMİ");
                MyFirebaseCloud cl = new MyFirebaseCloud();
                cl.TokeniTemizle(response.body().getRegid());
                // preferance boşalt
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("login");
                editor.remove("userid");
                editor.commit();
                progressDialog.cancel();
                Intent intent = new Intent(getActivity(), LoginAct.class);
                startActivity(intent);
                LoginAct.userid = 0;
            }else{
                Log.e("Hata oluştu","SERVERDA");
                progressDialog.cancel();
            }
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(getActivity(), "Silme İşleminde Hata oluştu !!!", Toast.LENGTH_LONG).show();
            Log.e("Hata oluştu","bELİRSİZ");
            progressDialog.cancel();
        }
    });
}

    public void profilidüzenle() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        KullaniciProfliService srv = retrofit.create(KullaniciProfliService.class);
        Call<KullaniciProfil[]> call = srv.getKullaniciProfili(LoginAct.userid);
        call.enqueue(new Callback<KullaniciProfil[]>() {
            @Override
            public void onResponse(Response<KullaniciProfil[]> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    for (KullaniciProfil modelValues: response.body()){
                        Adsoyad.setText(modelValues.getKullaniciAdi());
                        Ad = modelValues.getKullaniciAdi();
                        email = modelValues.getEmail();
                        profilresmi1 = modelValues.getProfilResmi();
                        profilyazisi.setText(modelValues.getProfilYazısı());
                        profilyazısı = modelValues.getProfilYazısı();
                        Context c = getActivity().getApplicationContext();
                        Picasso.with(c).load(MyApi.URL_IMAGES+modelValues.getProfilResmi()).transform(new CircleTransformation()).into(profilresmi);
                        _progressBar.setProgress(modelValues.getBitengörevSayis());
                        double x = ((modelValues.getBitengörevSayis() * 5) / 2);
                        progsesbarText.setText("% "+ String.valueOf(x).toString());
                    }
                }else {
                    Toast.makeText(getActivity(), "Hata Oluştu !", Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "Hata Oluştu !", Toast.LENGTH_LONG).show();
            }
        });
    }

}
