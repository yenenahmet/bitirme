package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.RegidKayitModel;
import com.example.ahmet.erasmusapp.Model.RegidKayitModelCB;
import com.example.ahmet.erasmusapp.Model.YorumGonderModel;
import com.example.ahmet.erasmusapp.Model.YorumGonderModelCb;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Yenen on 23.12.2016.
 */
public interface RegidPost {
    @POST("/api/RegID/KayitAl")
    Call<RegidKayitModelCB> reg(@Body RegidKayitModel regidKayitModel);

}
