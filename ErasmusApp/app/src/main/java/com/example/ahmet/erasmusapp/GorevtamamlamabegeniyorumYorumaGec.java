package com.example.ahmet.erasmusapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.Adapter.AndroidImageAdapter;
import com.example.ahmet.erasmusapp.Adapter.ArkListesiAdapter;
import com.example.ahmet.erasmusapp.Adapter.ArkListesiAdapterModel;
import com.example.ahmet.erasmusapp.Adapter.PostYorumlarıGetirAdapater;
import com.example.ahmet.erasmusapp.Adapter.PostYorumlarıGetirAdapaterModel;
import com.example.ahmet.erasmusapp.Model.YorumGonderModel;
import com.example.ahmet.erasmusapp.Model.YorumGonderModelCb;
import com.example.ahmet.erasmusapp.Model.YorumlariGetirModel;
import com.example.ahmet.erasmusapp.Service.GörevTamamlamaBegeniYoumService;
import com.example.ahmet.erasmusapp.Service.PostYorumlarıGetir;
import com.example.ahmet.erasmusapp.Service.YorumGonderServis;
import com.example.ahmet.erasmusapp.TabFragmntlar.FragmentD;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class GorevtamamlamabegeniyorumYorumaGec extends AppCompatActivity {
    private List<PostYorumlarıGetirAdapaterModel> list = new ArrayList<PostYorumlarıGetirAdapaterModel>();
    private ListView listview ;
    private PostYorumlarıGetirAdapater postYorumlarıGetirAdapater;
    int userid,begenisayisi,görevid,adimid;
    public  String KullanıcıAdı,GörevResmi,AdimResmi,yorum;
    ArrayList<String> arrList = new ArrayList<String>();
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gorevtamamlamayorum);
        listview  = (ListView)findViewById(R.id.listViewYorum);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(list.get(position).getYorumYapanId() != LoginAct.userid ){
                    Intent nt = new Intent(getApplicationContext(), BaskaProfileGec.class);
                    Log.e("idyorumlar =",String.valueOf(list.get(position).getYorumYapanId()).toString());
                    nt.putExtra("BaskaId",list.get(position).getYorumYapanId());
                    startActivity(nt);
                }

            }
        });
        final EditText edt = (EditText)findViewById(R.id.editText7_Yorumgonder);
        edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listview.setSelection(postYorumlarıGetirAdapater.getCount() - 1);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list.clear();
        Bundle extras = getIntent().getExtras();
        userid = extras.getInt("Userid");
        begenisayisi = extras.getInt("BegeniSayisi");
        görevid = extras.getInt("Görevid");
        adimid = extras.getInt("Adimid");
        GörevResmi = extras.getString("GörevResmi");
        AdimResmi = extras.getString("AdimResmi");
        KullanıcıAdı = extras.getString("KullanıcıAdı");
        getSupportActionBar().setTitle(KullanıcıAdı);

        arrList.add(GörevResmi);
        arrList.add(AdimResmi);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager_yorum);
        AndroidImageAdapter adapterView = new AndroidImageAdapter(getApplicationContext(),  arrList);
        mViewPager.setAdapter(adapterView);
        YorumlariGetir();
        ImageView btnyorum = (ImageView)findViewById(R.id.button9);
        assert btnyorum != null;
        btnyorum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(GorevtamamlamabegeniyorumYorumaGec.this);
                progressDialog.setMessage("Gönderiliyor..");
                progressDialog.setCancelable(false);
                progressDialog.show();
                if (edt != null) {
                    yorum = edt.getText().toString();
                }else{
                    Toast.makeText(getApplicationContext(), "Gönderme Başarısız oldu.", Toast.LENGTH_LONG).show();
                    yorum="";
                }
                YorumGonder(yorum);
                edt.getText().clear();

                Thread bekle = new Thread(){
                    public void run(){
                        try { sleep(1000);
                            YorumlariGetir();
                            listview.setSelection(postYorumlarıGetirAdapater.getCount() - 1);
                        } catch (Exception e) {
                            Log.e("hata thread",e.toString());
                         }
                    }
                };
                bekle.start();
                listview.setSelection(postYorumlarıGetirAdapater.getCount() - 1);
                progressDialog.cancel();
            }
        });
TextView begeni = (TextView)findViewById(R.id.textView16);
        begeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nt = new Intent(getApplicationContext(), BegenenListesiGor.class);
                nt.putExtra("Görevid",görevid);
                nt.putExtra("Adimid",adimid);
                nt.putExtra("Userid", userid);
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

    public void YorumlariGetir(){

        list.clear();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostYorumlarıGetir servis = retrofit.create(PostYorumlarıGetir.class);
        Call<YorumlariGetirModel[]> call = servis.getyorumlarıGetir(userid,adimid,görevid);
        Log.e("Userid =", String.valueOf(userid).toString()+String.valueOf(adimid).toString()+String.valueOf(görevid).toString());
        call.enqueue(new Callback<YorumlariGetirModel[]>() {
            @Override
            public void onResponse(Response<YorumlariGetirModel[]> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    for(YorumlariGetirModel modelvlues: response.body()){
                        PostYorumlarıGetirAdapaterModel model = new PostYorumlarıGetirAdapaterModel();
                        model.setYorum(modelvlues.getYorum());
                        model.setYorumYapanAdi(modelvlues.getYorumYapanAdi());
                        model.setYorumyapanProfilResmi(modelvlues.getYorumyapanProfilResmi());
                        model.setYorumAtilanTarih(modelvlues.getYorumAtilanTarih());
                        model.setYorumYapanId(modelvlues.getYorumYapanId());
                        list.add(model);
                    }
                    listview = (ListView)findViewById(R.id.listViewYorum);
                    postYorumlarıGetirAdapater = new PostYorumlarıGetirAdapater(getApplicationContext(),list);
                    listview.setAdapter(postYorumlarıGetirAdapater);
                    postYorumlarıGetirAdapater.notifyDataSetChanged();
                    postYorumlarıGetirAdapater.notifyDataSetInvalidated();
                    listview.invalidateViews();
                    listview.refreshDrawableState();
                }


            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "HATA !!! ", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void YorumGonder(String yorum){
        if(yorum.toString().equals(" ")){

        }else{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MyApi.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            YorumGonderServis srv = retrofit.create(YorumGonderServis.class);
            YorumGonderModel model = new YorumGonderModel(String.valueOf(userid).toString(),String.valueOf(görevid).toString(),String.valueOf(adimid).toString(),yorum,String.valueOf(LoginAct.userid).toString(),FragmentD.Ad,FragmentD.profilresmi1);
            Call<YorumGonderModelCb> call = srv.yorumtask(model);
            call.enqueue(new Callback<YorumGonderModelCb>() {
                @Override
                public void onResponse(Response<YorumGonderModelCb> response, Retrofit retrofit) {
                    if(response.isSuccess()){
                        Toast.makeText(getApplicationContext(), "Gönderildi.."+response.body().getName(), Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(getApplicationContext(), "Gönderme Başarısız oldu.", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getApplicationContext(), "Hata oluştu..", Toast.LENGTH_LONG).show();
                    Log.e("Hata ",t.toString());
                }
            });
        }
        }

}
