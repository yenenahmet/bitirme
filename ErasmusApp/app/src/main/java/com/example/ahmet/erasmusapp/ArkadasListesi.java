package com.example.ahmet.erasmusapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.Adapter.ArkListesiAdapter;
import com.example.ahmet.erasmusapp.Adapter.ArkListesiAdapterModel;
import com.example.ahmet.erasmusapp.Model.ArkdasListesiModel;
import com.example.ahmet.erasmusapp.Service.ArkadasListesiService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ArkadasListesi extends AppCompatActivity {
    private List<ArkListesiAdapterModel> list = new ArrayList<ArkListesiAdapterModel>();
    private ListView listview;
    private ArkListesiAdapter arkListesiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arkadas_listesi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        arklistesiniGetir();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            this.finish();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void arklistesiniGetir(){
        listview = (ListView)findViewById(R.id.Arklist);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ArkadasListesiService servis = retrofit.create(ArkadasListesiService.class);
        Call<ArkdasListesiModel[]> call = servis.getArkdasListesiModel(LoginAct.userid);
        call.enqueue(new Callback<ArkdasListesiModel[]>() {
            @Override
            public void onResponse(Response<ArkdasListesiModel[]> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    for(ArkdasListesiModel modelvalues:response.body()){
                        if(LoginAct.userid != modelvalues.ArkUserId){
                            ArkListesiAdapterModel items = new ArkListesiAdapterModel();
                            items.setUserId(modelvalues.UserId);
                            items.setKullaniciAdi(modelvalues.KullaniciAdi);
                            items.setProfilResmi(modelvalues.ProfilResmi);
                            items.setArkUserId(modelvalues.ArkUserId);
                            list.add(items);
                        }
                    }
                    arkListesiAdapter = new ArkListesiAdapter(getApplicationContext(),list);
                    listview.setAdapter(arkListesiAdapter);
                }else{
                    Toast.makeText(getApplicationContext(), "servisten kaynaklı bir hata oluştu", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nt = new Intent(getApplicationContext(), BaskaProfileGec.class);
                nt.putExtra("BaskaId",list.get(position).getArkUserId());
                // görevid;
                startActivity(nt);
            }
        });
    }
}
