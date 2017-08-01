package com.example.ahmet.erasmusapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.Adapter.ArkListesiAdapterModel;
import com.example.ahmet.erasmusapp.Adapter.ArkadaslikistekleriAdapter;
import com.example.ahmet.erasmusapp.Adapter.MesajKutusuListeleAdapter;
import com.example.ahmet.erasmusapp.Adapter.MesajKutusuListeleAdapterModel;
import com.example.ahmet.erasmusapp.Model.MesajKutusuGidenModel;
import com.example.ahmet.erasmusapp.Model.MesajKutusuGidenModelCB;
import com.example.ahmet.erasmusapp.Model.MesajKutusuListeleModel;
import com.example.ahmet.erasmusapp.Model.MesajKutusu_MesajlarıSilModel;
import com.example.ahmet.erasmusapp.Model.MesajKutusu_MesajlarıSilModelCB;
import com.example.ahmet.erasmusapp.Model.Taskcb;
import com.example.ahmet.erasmusapp.Service.MesajKutusuGönderServis;
import com.example.ahmet.erasmusapp.Service.MesajKutusuListeleServis;
import com.example.ahmet.erasmusapp.Service.MesajKutusu_MesajlariSil;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MesajKutusu extends AppCompatActivity {
    private List<MesajKutusuListeleAdapterModel> list = new ArrayList<MesajKutusuListeleAdapterModel>();
    public static ListView listview;
    public MesajKutusuListeleAdapter mesajKutusuListeleAdapter;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesaj_kutusu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Mesajlar");
        listview = (ListView)findViewById(R.id.Listview_MesajKutusu);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nt = new Intent(getApplicationContext(), OzelMesaj.class);
                nt.putExtra("BaskaId",list.get(position).getMesajgelenUserid());
                nt.putExtra("Adi",list.get(position).getKullaniciAdi());
                nt.putExtra("profilResmiBaska",list.get(position).getProfilResmi());
                startActivity(nt);
                Toast.makeText(getApplicationContext(), String.valueOf(list.get(position).getMesajgelenUserid()).toString(), Toast.LENGTH_LONG).show();
            }
        });
       listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View convertView, final int position, long id) {
               final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MesajKutusu.this);
               alertDialog.setMessage("Silme işlemini gerçekleştirmek istiyormusunuz ?");
               alertDialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       progressDialog = new ProgressDialog(MesajKutusu.this);
                       progressDialog.setMessage("Siliniyor...");
                       progressDialog.setCancelable(false);
                       progressDialog.show();
                       OzelMesaj.dbHelper.MesajlariSilme(list.get(position).getMesajgelenUserid());
                       MesajKutusuMesajsilServis(LoginAct.userid,list.get(position).getMesajgelenUserid());
                       dialog.dismiss();
                   }
               });
               alertDialog.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                       Toast.makeText(MesajKutusu.this, "Silme işlemi  iptal edildi !", Toast.LENGTH_SHORT).show();
                       dialog.dismiss();
                   }
               });
               alertDialog.show();
               mesajKutusunuListele();
               return true;
           }
       });
        mesajKutusunuListele();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            this.finish();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void mesajKutusunuListele(){
        Log.e("mesaj geldi","aaa");
        if(listview!=null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MyApi.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            MesajKutusuListeleServis servis = retrofit.create(MesajKutusuListeleServis.class);
            Call<MesajKutusuListeleModel[]> call = servis.mesajkutusulisteleservis(LoginAct.userid);
            call.enqueue(new Callback<MesajKutusuListeleModel[]>() {
                @Override
                public void onResponse(Response<MesajKutusuListeleModel[]> response, Retrofit retrofit) {
                    Log.e("mesaj geldi","başarlı girdi");
                    if (response.isSuccess()) {
                        for (MesajKutusuListeleModel modelvalues : response.body()) {
                            Log.e("mesaj geldidöngü",modelvalues.getKullaniciAdi());
                            MesajKutusuListeleAdapterModel items = new MesajKutusuListeleAdapterModel();
                            items.setMyUserid(modelvalues.getMyUserid());
                            items.setMesajgelenUserid(modelvalues.getMesajgelenUserid());
                            items.setSonMesaj(modelvalues.getSonMesaj());
                            items.setKullaniciAdi(modelvalues.getKullaniciAdi());
                            items.setProfilResmi(modelvalues.getProfilResmi());
                            items.setSonMesajZamanı(modelvalues.getSonMesajZamanı());
                            list.add(items);
                        }
                        mesajKutusuListeleAdapter = new MesajKutusuListeleAdapter(getApplicationContext(), list);
                        listview.setAdapter(mesajKutusuListeleAdapter);
                        mesajKutusuListeleAdapter.notifyDataSetChanged();
                        listview.refreshDrawableState();
                    } else {
                        Toast.makeText(getApplicationContext(), "Serviste Hata oluştu !!!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getApplicationContext(), "Hata oluştu !!!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }


public void MesajKutusuMesajsilServis(int userid,int mesajgelenUserid){
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MyApi.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    MesajKutusu_MesajlariSil servis = retrofit.create(MesajKutusu_MesajlariSil.class);
    MesajKutusu_MesajlarıSilModel model = new MesajKutusu_MesajlarıSilModel(String.valueOf(userid).toString(),String.valueOf(mesajgelenUserid).toString());
    Call<MesajKutusu_MesajlarıSilModelCB> call = servis.mesajsil(model);
    call.enqueue(new Callback<MesajKutusu_MesajlarıSilModelCB>() {
        @Override
        public void onResponse(Response<MesajKutusu_MesajlarıSilModelCB> response, Retrofit retrofit) {
            Toast.makeText(MesajKutusu.this, response.body().getName(), Toast.LENGTH_SHORT).show();
            list.clear();
            mesajKutusunuListele();
            progressDialog.cancel();
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(MesajKutusu.this, "Hata Oluştu..", Toast.LENGTH_SHORT).show();
            progressDialog.cancel();
        }
    });

}

}
