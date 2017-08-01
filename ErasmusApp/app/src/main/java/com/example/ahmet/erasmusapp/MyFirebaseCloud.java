package com.example.ahmet.erasmusapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ahmet.erasmusapp.Model.RegidKayitModel;
import com.example.ahmet.erasmusapp.Model.RegidKayitModelCB;
import com.example.ahmet.erasmusapp.Service.RegidPost;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MyFirebaseCloud extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token: " + token);
        Log.e(TAG, "Token: " + token);

        sendRegistrationToServer(token);
    }

    public void sendRegistrationToServer(String token) {
        // token'ı servise gönderme işlemlerini bu methodda yapmalısınız
        //ayrı bir servis oluştur login id alıp send edip , kullanıncının regidsine kaydet
        if(LoginAct.userid != 0) {// userid doluysa yolla
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MyApi.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RegidPost reg = retrofit.create(RegidPost.class);
            RegidKayitModel model = new RegidKayitModel(String.valueOf(LoginAct.userid).toString(), token);
            Call<RegidKayitModelCB> call = reg.reg(model);
            call.enqueue(new Callback<RegidKayitModelCB>() {
                @Override
                public void onResponse(Response<RegidKayitModelCB> response, Retrofit retrofit) {
                    Log.e(TAG, "Başarılı");
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e(TAG, "Başarısız");
                }
            });
        }
        else{
            Log.e(TAG, "Başarısız");
        }
    }
    public void TokeniTemizle(String Regid)  {
        try{
            FirebaseInstanceId.getInstance().deleteInstanceId();
            FirebaseInstanceId.getInstance().deleteToken(Regid, "FCM");
            Log.e("TEMİZLEND,","TOKENI-TEMİZLE");
        }catch (Exception ex){
                Log.e("Hata","TOKENI-TEMİZLE");
        }

    }
}
