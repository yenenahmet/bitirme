package com.example.ahmet.erasmusapp.TabFragmntlar;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.Adapter.ArkListesiAdapter;
import com.example.ahmet.erasmusapp.Adapter.ArkListesiAdapterModel;
import com.example.ahmet.erasmusapp.Adapter.BildirimleriDöndürAdapter;
import com.example.ahmet.erasmusapp.Adapter.BildirimleriDöndürAdapterModel;
import com.example.ahmet.erasmusapp.ArkadasListesi;
import com.example.ahmet.erasmusapp.ArkadaslikistekleriGoster;
import com.example.ahmet.erasmusapp.LoginAct;
import com.example.ahmet.erasmusapp.MesajKutusu;
import com.example.ahmet.erasmusapp.Model.BildirimleriDöndürModel;
import com.example.ahmet.erasmusapp.Model.KullaniciProfil;
import com.example.ahmet.erasmusapp.MyApi;
import com.example.ahmet.erasmusapp.R;
import com.example.ahmet.erasmusapp.Service.BildirimleriDöndür;
import com.example.ahmet.erasmusapp.Service.KullaniciProfliService;
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
public class FragmentC extends Fragment {
    private LocationManager locationManager;
    private List<BildirimleriDöndürAdapterModel> list = new ArrayList<BildirimleriDöndürAdapterModel>();
    private ListView listview;
    private BildirimleriDöndürAdapter bildirimleriDöndürAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private Button btnarkistekleri;
    private Button btnMesajKutusu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        list.clear();
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragc_yeni, container, false);
        listview = (ListView) view.findViewById(R.id.ArklistFragC);
        TabbedActivity.useridTanımla(getActivity());
        btnarkistekleri = (Button) view.findViewById(R.id.btnArkistekleri);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshFragc);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        // burada ise Swipe Reflesh olduğunda ne yapacaksanız onu eklemeniz yeterlidir. Örneğin bir listeyi clear edebilir yada yeniden veri doldurabilirsiniz.
                        try {
                            list.clear();
                            bildirimlericek();
                            listview.refreshDrawableState();
                        } catch (IndexOutOfBoundsException ex) {

                        }
                    }
                }, 1500);
            }
        });
        bildirimlericek();
        btnarkistekleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent arklist = new Intent(getActivity(), ArkadaslikistekleriGoster.class);
                startActivity(arklist);
            }
        });
        btnMesajKutusu = (Button) view.findViewById(R.id.button8);
        btnMesajKutusu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent msj = new Intent(getActivity(), MesajKutusu.class);
                startActivity(msj);
            }
        });
        return view;
    }

    public void bildirimlericek() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BildirimleriDöndür srvis = retrofit.create(BildirimleriDöndür.class);
        Call<BildirimleriDöndürModel[]> call = srvis.getBildirimDönüs(LoginAct.userid);
        call.enqueue(new Callback<BildirimleriDöndürModel[]>() {
            @Override
            public void onResponse(Response<BildirimleriDöndürModel[]> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    for (BildirimleriDöndürModel modelvalues : response.body()) {
                        if (modelvalues.getMyid() != modelvalues.getBildirimiGöderenId()) {
                            BildirimleriDöndürAdapterModel items = new BildirimleriDöndürAdapterModel();
                            items.setMyid(modelvalues.getMyid());
                            items.setBildirimiGöderenId(modelvalues.getBildirimiGöderenId());
                            items.setTarihZaman(modelvalues.getTarihZaman());
                            items.setYorum(modelvalues.getYorum());
                            items.setGörevId(modelvalues.getGörevId());
                            items.setAdimId(modelvalues.getAdimId());
                            items.setBildirimeBakıldımı(modelvalues.getBildirimeBakıldımı());
                            items.setBegeni(modelvalues.getBegeni());
                            items.setArkadasEkledin(modelvalues.getArkadasEkledin());
                            items.setArkadaslıkİsteğiGeldi(modelvalues.getArkadaslıkİsteğiGeldi());
                            items.setMesajGeldi(modelvalues.getMesajGeldi());
                            items.setKullaniciAdi(modelvalues.getKullaniciAdi());
                            items.setProfilResmi(modelvalues.getProfilResmi());
                            list.add(items);
                        }

                    }
                    try {
                        bildirimleriDöndürAdapter = new BildirimleriDöndürAdapter(getActivity(), list);
                        listview.setAdapter(bildirimleriDöndürAdapter);
                    } catch (IllegalStateException ex) {
                        Toast.makeText(getActivity(), "Hata oluştu", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "Hata oluştu", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), t.toString() + " Sorun  oluştu", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void NetworkProvider() {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String currentLat = String.valueOf(location.getLatitude());
                String currentLot = String.valueOf(location.getLongitude());
                TabbedActivity._curLat = location.getLatitude();
                TabbedActivity._curLng = location.getLongitude();
                Log.e("Currengps= ", currentLat + "," + currentLot);
            }
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {
            }
        });
    }

}
