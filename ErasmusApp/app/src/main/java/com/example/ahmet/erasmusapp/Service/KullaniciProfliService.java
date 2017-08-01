package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.KullaniciProfil;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Ahmet on 6.9.2016.
 */
public interface KullaniciProfliService {
    @GET("/api/Satinalma/getProfil/{userid}")
    Call<KullaniciProfil[]> getKullaniciProfili(@Path("userid") int userid);
}
