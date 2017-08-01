package com.example.ahmet.erasmusapp.FoursquareApi;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.GorevAdimlari;
import com.example.ahmet.erasmusapp.LoginAct;
import com.example.ahmet.erasmusapp.Model.GörevtamamlamaPostAdim;
import com.example.ahmet.erasmusapp.Model.GörevtamamlamaPostAdimCb;
import com.example.ahmet.erasmusapp.MyApi;
import com.example.ahmet.erasmusapp.Service.GörevAdimlari;
import com.example.ahmet.erasmusapp.Service.GörevtamamlamaAdimService;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class foursquare {
    private static Context mContext;
    public static final String FOURL ="https://api.foursquare.com";
    public static String clientID = "U2FQ0C2GKLYKS3WI4EJNNTH2SYLMU5UBNRL0KBMC1NZTK1HH";
    public static String clientSecret = "KUYPRVN4JYGG33RMVBRTBT0K5HUTLH4VWBAWFMBGJGVQOLVS";
    public double latitude=0;
    public double longitude=0;
    public static String ll;
    public static Date simdikiZaman = new Date();
    public static SimpleDateFormat bicim3=new SimpleDateFormat("yyyyMMdd");
    public static String v = bicim3.format(simdikiZaman);
    public static String foodCode = "4d4b7105d754a06374d81259";
    public static int alan = 500;
    public static String radius = String.valueOf(alan).toString();

    public foursquare(double latitude, double longitude, Context context){

        this.mContext = context;
        this.latitude = latitude;
        this.longitude = longitude;
        ll = String.valueOf(latitude).toString() + "," +String.valueOf(longitude).toString();
            }
    public static void servisiDoldur(final String görev, final int AdimId, final int görevid){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(FOURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                FService servis = retrofit.create(FService.class);
                Call<FourquareModel> call = servis.getFourquaremodelQuery(clientID,clientSecret,v,ll,radius,foodCode);
                Log.e("konnum",ll);
                call.enqueue(new Callback<FourquareModel>() {
                    @Override
                    public void onResponse(Response<FourquareModel> response, Retrofit retrofit) {
                        int i=0;
                        if(response.isSuccess()){
                            Log.e("Başarılı Geldi=", String.valueOf(response.body().getResponse().getVenues().size()).toString());
                            Log.e("Başarılı Geldi=", "asdasdasd");
                            for(i = 0; i <response.body().getResponse().getVenues().size(); i++){
                                Log.e("Döngüye girdi", response.body().getResponse().getVenues().get(i).getName());
                                if(görev.equals(response.body().getResponse().getVenues().get(i).getName())){
                                    Log.e("Görev TAMAMLANDI", "TAMAM");
                                    Log.e("for", response.body().getResponse().getVenues().get(i).getName());
                                    ServisiDoldurPost(AdimId,görevid);
                                    // post atılacak
                                    // görev tamamlandı
                                }else{ // Tamamlancak Görev Bulunamadi
                                   // Log.e("Döngü", "Bulunamadi");
                                  //  Toast.makeText(mContext, "Görev için Belirlenen noktada değilsiniz", Toast.LENGTH_LONG).show();
                                }
                              //  Log.e("for", response.body().getResponse().getVenues().get(i).getName());
                            }

                        }else{
                            Toast.makeText(mContext, "Görev için Belirlenen noktada değilsiniz", Toast.LENGTH_LONG).show();
                        }
            }
            @Override
            public void onFailure(Throwable t) {
                Log.e("başarısız Geldi=","Bom");
            }
        });
    }
    public static void ServisiDoldurPost(final int AdimId, final int görevid){// görev tamamlandıktan sonra tablo değerini  tamamlandı yap
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MyApi.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    GörevtamamlamaAdimService srvis = retrofit.create(GörevtamamlamaAdimService.class);
    GörevtamamlamaPostAdim görevpost = new GörevtamamlamaPostAdim(LoginAct.userid,görevid,true,AdimId);
    Call<GörevtamamlamaPostAdimCb>  call = srvis.GörevtamamlamaAdimServiceF(görevpost);
    call.enqueue(new Callback<GörevtamamlamaPostAdimCb>() {
        @Override
        public void onResponse(Response<GörevtamamlamaPostAdimCb> response, Retrofit retrofit) {
            if(response.isSuccess()){
                Log.e("PostGörevTamamlama","Tamamlandi");
                Log.e("Kimin Ne oldugu",LoginAct.userid +"||"+görevid+"||"+AdimId+"");
                Log.e("cupcupcp",response.body().toString());
                Toast.makeText(mContext, "Görev Tamamlandi", Toast.LENGTH_LONG).show();

                GorevAdimlari aa = new GorevAdimlari();
                aa.adimlarigöster();
            }else{
                Toast.makeText(mContext, "Yanlış Konum !!!", Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(mContext, "Bağlantı Koptu !", Toast.LENGTH_LONG).show();
            Log.e("PostGörevTamamlama","HATA");
        }
    });

}
}
