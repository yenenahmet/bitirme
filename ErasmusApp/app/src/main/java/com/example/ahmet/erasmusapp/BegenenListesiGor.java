package com.example.ahmet.erasmusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.Adapter.BegenenListesiGorAModel;
import com.example.ahmet.erasmusapp.Adapter.BegeniListesiGorAdapter;
import com.example.ahmet.erasmusapp.Adapter.PostYorumlarıGetirAdapater;
import com.example.ahmet.erasmusapp.Adapter.PostYorumlarıGetirAdapaterModel;
import com.example.ahmet.erasmusapp.Model.BegenenListesiGorModel;
import com.example.ahmet.erasmusapp.Model.YorumlariGetirModel;
import com.example.ahmet.erasmusapp.Service.BegeniListesiGor;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class BegenenListesiGor extends AppCompatActivity {
    private List<BegenenListesiGorAModel> list = new ArrayList<BegenenListesiGorAModel>();
    private BegeniListesiGorAdapter begeniListesiGorAdapter;
    private ListView listview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begenen_listesi_gor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Beğenenler Listesi");
        int userid,görevid,adimid;
        Bundle extras = getIntent().getExtras();
        userid = extras.getInt("Userid");
        görevid = extras.getInt("Görevid");
        adimid = extras.getInt("Adimid");
        ListeyiGetir(adimid,görevid,userid);
        listview = (ListView)findViewById(R.id.Listview_begeniListesi);
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
    public void ListeyiGetir(int adimid,int gorevid,int gonderiuserid){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BegeniListesiGor servis = retrofit.create(BegeniListesiGor.class);
        Call<BegenenListesiGorModel[]> call = servis.getBegeniDoldurYorumlarListesiModel(adimid, gorevid, gonderiuserid);
        call.enqueue(new Callback<BegenenListesiGorModel[]>() {
            @Override
            public void onResponse(Response<BegenenListesiGorModel[]> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    for(BegenenListesiGorModel modelvlues: response.body()){
                        BegenenListesiGorAModel model = new BegenenListesiGorAModel();
                        model.setUserId(modelvlues.getUserId());
                        model.setKullaniciAdi(modelvlues.getKullaniciAdi());
                        model.setProfilResmi(modelvlues.getProfilResmi());
                        list.add(model);
                    }
                    listview = (ListView)findViewById(R.id.Listview_begeniListesi);
                    begeniListesiGorAdapter = new BegeniListesiGorAdapter(getApplicationContext(),list);
                    listview.setAdapter(begeniListesiGorAdapter);
                    listview.refreshDrawableState();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "Hata Oluştu !!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
