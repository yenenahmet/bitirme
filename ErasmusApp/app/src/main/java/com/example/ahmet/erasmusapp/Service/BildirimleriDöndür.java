package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.ArkdasListesiModel;
import com.example.ahmet.erasmusapp.Model.BildirimleriDöndürModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Yenen on 30.10.2016.
 */
public interface BildirimleriDöndür {
    @GET("/getBildirimleriDöndür/{userid}")
    Call<BildirimleriDöndürModel[]> getBildirimDönüs(@Path("userid") int userid);
}
