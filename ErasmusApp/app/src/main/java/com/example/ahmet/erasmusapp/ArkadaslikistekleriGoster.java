package com.example.ahmet.erasmusapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.Adapter.ArkListesiAdapter;
import com.example.ahmet.erasmusapp.Adapter.ArkListesiAdapterModel;
import com.example.ahmet.erasmusapp.Adapter.ArkadaslikistekleriAdapter;
import com.example.ahmet.erasmusapp.Adapter.ArkadaslikistekleriAdapterModel;
import com.example.ahmet.erasmusapp.Model.ArkadaslikStekleriOnaylaModel;
import com.example.ahmet.erasmusapp.Model.ArkadaslikStekleriOnaylaModelCb;
import com.example.ahmet.erasmusapp.Model.ArkadaslikStekleriREDCbModel;
import com.example.ahmet.erasmusapp.Model.ArkadaslikStekleriREDModel;
import com.example.ahmet.erasmusapp.Model.ArkadaslikistekleriModel;
import com.example.ahmet.erasmusapp.Service.ArkadalikStekleriRedServis;
import com.example.ahmet.erasmusapp.Service.ArkadaslikStekleriOnaylaServis;
import com.example.ahmet.erasmusapp.Service.ArkadaslikistekleriService;
import com.example.ahmet.erasmusapp.picasso.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ArkadaslikistekleriGoster extends AppCompatActivity {
    public List<ArkadaslikistekleriAdapterModel> list = new ArrayList<ArkadaslikistekleriAdapterModel>();
    public static ListView listview;
    public ArkadaslikistekleriAdapter arkadaslikistekleriAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arkadaslikistekleri_goster);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listview = (ListView)findViewById(R.id.listViewArkadaslikistekleri);
        istekleriGöster();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nt = new Intent(getApplicationContext(), BaskaProfileGec.class);
                nt.putExtra("BaskaId",list.get(position).getUserId());
                // görevid;
                startActivity(nt);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            this.finish();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void istekleriGöster(){
        if(listview == null) {
            listview = (ListView) findViewById(R.id.listViewArkadaslikistekleri);
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ArkadaslikistekleriService servis = retrofit.create(ArkadaslikistekleriService.class);
        Call<ArkadaslikistekleriModel[]>  call = servis.getArkadaslikistekleri(LoginAct.userid);
        call.enqueue(new Callback<ArkadaslikistekleriModel[]>() {
            @Override
            public void onResponse(Response<ArkadaslikistekleriModel[]> response, Retrofit retrofit) {
                for(ArkadaslikistekleriModel modelvalues: response.body()){
                    ArkadaslikistekleriAdapterModel model = new ArkadaslikistekleriAdapterModel();
                    model.setProfilResmi(modelvalues.getProfilResmi());
                    model.setKullaniciAdi(modelvalues.getKullaniciAdi());
                    model.setUserId(modelvalues.getUserId());
                    list.add(model);
                }
                arkadaslikistekleriAdapter = new ArkadaslikistekleriAdapter(getApplicationContext(),list,listview);
                listview.setAdapter(arkadaslikistekleriAdapter);
                arkadaslikistekleriAdapter.notifyDataSetChanged();
                listview.refreshDrawableState();

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "HATA OLUŞTU !!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public  void onayla(int arkuserid,final Context mcontext,final ListView listView){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ArkadaslikStekleriOnaylaServis srv = retrofit.create(ArkadaslikStekleriOnaylaServis.class);
        ArkadaslikStekleriOnaylaModel model = new ArkadaslikStekleriOnaylaModel(String.valueOf(arkuserid).toString(),String.valueOf(LoginAct.userid).toString());
        Call<ArkadaslikStekleriOnaylaModelCb> call = srv.onaylaServis(model);
        call.enqueue(new Callback<ArkadaslikStekleriOnaylaModelCb>() {
            @Override
            public void onResponse(Response<ArkadaslikStekleriOnaylaModelCb> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    Log.e("Onaylaİslemi ","Başarılı");
                    Log.e("Serverdan Dönen",response.body().getName());
                    istekleriGöster2(mcontext,listView);
                }else{
                    Log.e("Onaylaİslemi ","Başarısız");
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Log.e("Onaylaİslemi ","Hata oluştu");
                Log.e("Onaylama işlemi",t.toString());
            }
        });
    }
    public void red(int redid, final Context mcontext, final ListView listView){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ArkadalikStekleriRedServis srv = retrofit.create(ArkadalikStekleriRedServis.class);
        ArkadaslikStekleriREDModel model = new ArkadaslikStekleriREDModel(String.valueOf(LoginAct.userid).toString(),String.valueOf(redid).toString());
        Call<ArkadaslikStekleriREDCbModel> call =srv.redServis(model);
        call.enqueue(new Callback<ArkadaslikStekleriREDCbModel>() {
            @Override
            public void onResponse(Response<ArkadaslikStekleriREDCbModel> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    Log.e("redişlemi","Başarılı");
                    Log.e("Serverdan Dönen",response.body().getName());
                    istekleriGöster2(mcontext,listView);
                }else{
                    Log.e("Onaylaİslemi ","Başarısız");
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Log.e("Onaylaİslemi ","Hata oluştu");
                Log.e("Onaylama işlemi",t.toString());
            }
        });
    }
    public void istekleriGöster2(final Context mcontext, final ListView listView){
        final List<ArkadaslikistekleriAdapterModel> list2 = new ArrayList<ArkadaslikistekleriAdapterModel>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ArkadaslikistekleriService servis = retrofit.create(ArkadaslikistekleriService.class);
        Call<ArkadaslikistekleriModel[]>  call = servis.getArkadaslikistekleri(LoginAct.userid);
        call.enqueue(new Callback<ArkadaslikistekleriModel[]>() {
            @Override
            public void onResponse(Response<ArkadaslikistekleriModel[]> response, Retrofit retrofit) {
                for(ArkadaslikistekleriModel modelvalues: response.body()){
                    ArkadaslikistekleriAdapterModel model = new ArkadaslikistekleriAdapterModel();
                    model.setProfilResmi(modelvalues.getProfilResmi());
                    model.setKullaniciAdi(modelvalues.getKullaniciAdi());
                    model.setUserId(modelvalues.getUserId());
                    list2.add(model);
                }

               ArkadaslikistekleriAdapter arkadaslikistekleriAdapter = new ArkadaslikistekleriAdapter(mcontext,list2,listView);
                listView.setAdapter(arkadaslikistekleriAdapter);
                listView.refreshDrawableState();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "HATA OLUŞTU !!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
