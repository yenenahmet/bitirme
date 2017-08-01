package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.GöreviBitirenlerModel;
import com.example.ahmet.erasmusapp.Model.MesajKutusuListeleModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Yenen on 6.3.2017.
 */
public interface MesajKutusuListeleServis {
    @GET("/api/MesajkutusuListele/{userid}")
    Call<MesajKutusuListeleModel[]> mesajkutusulisteleservis(@Path("userid") int userid);
}

