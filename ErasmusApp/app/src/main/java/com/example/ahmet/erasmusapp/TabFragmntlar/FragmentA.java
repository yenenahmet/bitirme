package com.example.ahmet.erasmusapp.TabFragmntlar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.Adapter.GörevTamamlamaBegeniYorumAdapter;
import com.example.ahmet.erasmusapp.Adapter.GörevTamamlamaBegeniYorumModel;
import com.example.ahmet.erasmusapp.Adapter.GörevlerAlbumAdapter;
import com.example.ahmet.erasmusapp.BaskaProfileGec;
import com.example.ahmet.erasmusapp.GorevtamamlamabegeniyorumYorumaGec;
import com.example.ahmet.erasmusapp.LoginAct;
import com.example.ahmet.erasmusapp.Model.BegeniGeriAlModel;
import com.example.ahmet.erasmusapp.Model.BegeniGeriAlModelCb;
import com.example.ahmet.erasmusapp.Model.BegeniYollaModel;
import com.example.ahmet.erasmusapp.Model.BegeniYollaModelCb;
import com.example.ahmet.erasmusapp.Model.GörevTamamlamaBegeniYorum;
import com.example.ahmet.erasmusapp.MyApi;
import com.example.ahmet.erasmusapp.R;
import com.example.ahmet.erasmusapp.Service.BegeniGeriAlServis;
import com.example.ahmet.erasmusapp.Service.BegeniYollaServis;
import com.example.ahmet.erasmusapp.Service.GörevTamamlamaBegeniYoumService;
import com.example.ahmet.erasmusapp.TabbedActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Ahmet on 5.9.2016.
 */
public class FragmentA extends Fragment {
    public List<GörevTamamlamaBegeniYorumModel> list = new ArrayList<GörevTamamlamaBegeniYorumModel>();
    private ListView listView ;
    private GörevTamamlamaBegeniYorumAdapter Tamamlamaadapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    public static int durumGeriAl;
    private ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // TammamlananDoldur();
        // TODO Auto-generated method stub
        View view=inflater.inflate(R.layout.fragment_a, container, false);
        list.clear();
        Log.e("fraga girdi","Fraga");
        TabbedActivity.useridTanımla(getActivity());
        listView = (ListView) view.findViewById(R.id.ArklistFraga);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nt = new Intent(getContext(), GorevtamamlamabegeniyorumYorumaGec.class);
                nt.putExtra("BegeniSayisi",list.get(position).getBegeniSayisi());
                nt.putExtra("Görevid",list.get(position).getGörevId());
                nt.putExtra("Adimid",list.get(position).getAdımId());
                nt.putExtra("Userid",list.get(position).getUserId());
                nt.putExtra("GörevResmi",list.get(position).getGörevResmi());
                nt.putExtra("AdimResmi",list.get(position).getAdimResmi());
                nt.putExtra("KullanıcıAdı",list.get(position).getKullaniciAdi());
                // görevid;
                startActivity(nt);
            }
        });
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.RefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        // burada ise Swipe Reflesh olduğunda ne yapacaksanız onu eklemeniz yeterlidir. Örneğin bir listeyi clear edebilir yada yeniden veri doldurabilirsiniz.
                        list.clear();
                        TammamlananDoldur();
                        listView.refreshDrawableState();
                    }
                }, 1500);
            }
        });

        TammamlananDoldur();

        return view;
    }
    public void TammamlananDoldur() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Sayfa Yükleniyor Lütfen Biraz Bekleyin ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GörevTamamlamaBegeniYoumService servis = retrofit.create(GörevTamamlamaBegeniYoumService.class);
        Call<GörevTamamlamaBegeniYorum[]> call = servis.getGörevTamamlamaBegeniYoumService(LoginAct.userid);
        call.enqueue(new Callback<GörevTamamlamaBegeniYorum[]>() {
            @Override
            public void onResponse(Response<GörevTamamlamaBegeniYorum[]> response, Retrofit retrofit) {
                if(response.isSuccess()) {
                    for (GörevTamamlamaBegeniYorum modelvalues : response.body()) {
                        GörevTamamlamaBegeniYorumModel items = new GörevTamamlamaBegeniYorumModel();
                        items.setYorumSayisi(modelvalues.getYorumSayisi());
                        items.setBegeniSayisi(modelvalues.getBegeniSayisi());
                        items.setUserId(modelvalues.getUserId());
                        items.setProfilResmi(modelvalues.getProfilResmi());
                        items.setKullaniciAdi(modelvalues.getKullaniciAdi());
                        items.setAdımId(modelvalues.getAdımId());
                        items.setGörevId(modelvalues.getGörevId());
                        items.setTarihZaman(modelvalues.getTarihZaman());
                        items.setGörevAdi(modelvalues.getGörevAdi());
                        items.setGörevResmi(modelvalues.getGörevResmi());
                        items.setAdimAdi(modelvalues.getAdimAdi());
                        items.setAdimResmi(modelvalues.getAdimResmi());
                        items.setBegenildimi(modelvalues.getBegenildimi());
                        list.add(items);
                    }

                    Tamamlamaadapter = new GörevTamamlamaBegeniYorumAdapter(getActivity(), list);
                    Tamamlamaadapter.notifyDataSetChanged();
                    listView.refreshDrawableState();
                    listView.invalidateViews();
                    listView.setAdapter(Tamamlamaadapter);
                }
                    progressDialog.cancel();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(),"İnternet Bağlanıtınızı Kontrol edin.Sayfa yüklenemedi!!!" , Toast.LENGTH_LONG).show();
                progressDialog.cancel();
            }
        });


    }

public void begeniyolla(int adimid, int görevid, int gönderenid, final Context mContext){
  final  ProgressDialog progressDialog = new ProgressDialog(mContext);
    progressDialog.setMessage("Gönderiliyor...");
    progressDialog.setCancelable(false);
    progressDialog.show();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MyApi.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    BegeniYollaServis srv = retrofit.create(BegeniYollaServis.class);
    BegeniYollaModel model = new BegeniYollaModel(String.valueOf(adimid).toString(),String.valueOf(görevid).toString(),String.valueOf(gönderenid),String.valueOf(LoginAct.userid));
    Call<BegeniYollaModelCb> call = srv.begeniyollaServis(model);
    call.enqueue(new Callback<BegeniYollaModelCb>() {
        @Override
        public void onResponse(Response<BegeniYollaModelCb> response, Retrofit retrofit) {
            if(response.isSuccess()){
                TammamlananDoldur2(mContext);
                Toast.makeText(mContext,"Begeni Gönderildi..." , Toast.LENGTH_LONG).show();
                progressDialog.cancel();
            }
        }
        @Override
        public void onFailure(Throwable t) {
            Log.e("Begeni Yollama HATA ",t.toString());
            Toast.makeText(mContext,"Hata Oluştu !!!" , Toast.LENGTH_LONG).show();
            progressDialog.cancel();
        }
    });

}
   // int durum;
public void BegeniGeriAl(int adimid, int görevid, int gönderenid, final Context mContext){
    ProgressDialog progressDialog = new ProgressDialog(mContext);
    progressDialog.setMessage("Gönderiliyor...");
    progressDialog.setCancelable(false);
    progressDialog.show();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MyApi.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    BegeniGeriAlServis srv = retrofit.create(BegeniGeriAlServis.class);
    BegeniGeriAlModel model = new BegeniGeriAlModel(String.valueOf(adimid).toString(),String.valueOf(görevid).toString(),String.valueOf(gönderenid),String.valueOf(LoginAct.userid));
    Call<BegeniGeriAlModelCb> call =srv.begenigerialServis(model);
    call.enqueue(new Callback<BegeniGeriAlModelCb>() {
        @Override
        public void onResponse(Response<BegeniGeriAlModelCb> response, Retrofit retrofit) {
            if(response.isSuccess()){
                TammamlananDoldur2(mContext);
                Toast.makeText(mContext,"Begeni Geri Alındı..." , Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(mContext,"Hata Oluştu !!!" , Toast.LENGTH_LONG).show();
        }
    });

progressDialog.cancel();
 }
    public void TammamlananDoldur2(final Context mcontext) {
        final List<GörevTamamlamaBegeniYorumModel> list = new ArrayList<GörevTamamlamaBegeniYorumModel>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GörevTamamlamaBegeniYoumService servis = retrofit.create(GörevTamamlamaBegeniYoumService.class);
        Call<GörevTamamlamaBegeniYorum[]> call = servis.getGörevTamamlamaBegeniYoumService(LoginAct.userid);
        call.enqueue(new Callback<GörevTamamlamaBegeniYorum[]>() {
            @Override
            public void onResponse(Response<GörevTamamlamaBegeniYorum[]> response, Retrofit retrofit) {
                for(GörevTamamlamaBegeniYorum modelvalues:response.body()){
                    GörevTamamlamaBegeniYorumModel items = new GörevTamamlamaBegeniYorumModel();
                    items.setYorumSayisi(modelvalues.getYorumSayisi());
                    items.setBegeniSayisi(modelvalues.getBegeniSayisi());
                    items.setUserId(modelvalues.getUserId());
                    items.setProfilResmi(modelvalues.getProfilResmi());
                    items.setKullaniciAdi(modelvalues.getKullaniciAdi());
                    items.setAdımId(modelvalues.getAdımId());
                    items.setGörevId(modelvalues.getGörevId());
                    items.setTarihZaman(modelvalues.getTarihZaman());
                    items.setGörevAdi(modelvalues.getGörevAdi());
                    items.setGörevResmi(modelvalues.getGörevResmi());
                    items.setAdimAdi(modelvalues.getAdimAdi());
                    items.setAdimResmi(modelvalues.getAdimResmi());
                    items.setBegenildimi(modelvalues.getBegenildimi());
                    list.add(items);
                }
                    GörevTamamlamaBegeniYorumAdapter Tamamlamaadapter = new GörevTamamlamaBegeniYorumAdapter(mcontext,list);
                    Activity activity = (Activity) mcontext;
                    ListView  listView = (ListView) activity.findViewById(R.id.ArklistFraga);
                    listView.setAdapter(Tamamlamaadapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(),"patladi frag a" , Toast.LENGTH_LONG).show();
//                tw.setText("Hata");
            }
        });


    }

}
