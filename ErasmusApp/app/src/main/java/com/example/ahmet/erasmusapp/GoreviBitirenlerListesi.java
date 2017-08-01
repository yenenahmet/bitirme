package com.example.ahmet.erasmusapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.Adapter.ArkListesiAdapter;
import com.example.ahmet.erasmusapp.Adapter.ArkListesiAdapterModel;
import com.example.ahmet.erasmusapp.Adapter.GöreviBitirenlerAdapter;
import com.example.ahmet.erasmusapp.Adapter.GöreviBitirenlerAdapterModel;
import com.example.ahmet.erasmusapp.Model.GöreviBitirenlerModel;
import com.example.ahmet.erasmusapp.Service.GöreviBitirenlerService;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class GoreviBitirenlerListesi extends AppCompatActivity {
    private List<GöreviBitirenlerAdapterModel> list = new ArrayList<GöreviBitirenlerAdapterModel>();
    private ListView listview;
    private GöreviBitirenlerAdapter göreviBitirenlerAdapter;
    public int görevid;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gorevi_bitirenler_listesi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        görevid = extras.getInt("görevid");
        listview = (ListView) findViewById(R.id.görevibitirenler);
        GörebitirenleriGetir();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nt = new Intent(getApplicationContext(), BaskaProfileGec.class);
                nt.putExtra("BaskaId", list.get(position).getUserId());
                // görevid;
                startActivity(nt);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(getApplicationContext(), "Geri Tıklandı....", Toast.LENGTH_LONG).show();
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void GörebitirenleriGetir() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GöreviBitirenlerService servis = retrofit.create(GöreviBitirenlerService.class);
        Call<GöreviBitirenlerModel[]> call = servis.getGöreviBitirenler(görevid);
        call.enqueue(new Callback<GöreviBitirenlerModel[]>() {
            @Override
            public void onResponse(Response<GöreviBitirenlerModel[]> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    for (GöreviBitirenlerModel modevalues : response.body()) {
                        // adapateri doldur
                        if (LoginAct.userid != modevalues.getUserId()) {
                            GöreviBitirenlerAdapterModel model = new GöreviBitirenlerAdapterModel();
                            model.setUserId(modevalues.getUserId());
                            model.setKullaniciAdi(modevalues.getKullaniciAdi());
                            model.setProfilResmi(modevalues.getProfilResmi());
                            model.setGörevDerecesi(modevalues.getGörevDerecesi());
                            list.add(model);
                        }

                    }
                    göreviBitirenlerAdapter = new GöreviBitirenlerAdapter(getApplicationContext(), list);
                    listview.setAdapter(göreviBitirenlerAdapter);
                    göreviBitirenlerAdapter.notifyDataSetChanged();
                    listview.refreshDrawableState();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "Hata" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "GoreviBitirenlerListesi Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.ahmet.erasmusapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client2, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "GoreviBitirenlerListesi Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.ahmet.erasmusapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client2, viewAction);
        client2.disconnect();
    }
}
