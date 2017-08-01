package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.ArkadasEkleModel;
import com.example.ahmet.erasmusapp.Model.ArkadasEkleModelCB;
import com.example.ahmet.erasmusapp.Model.ArkadaslikStekleriOnaylaModel;
import com.example.ahmet.erasmusapp.Model.ArkadaslikStekleriOnaylaModelCb;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Yenen on 17.2.2017.
 */
public interface ArkadasEkleServis {
    @POST("/api/PostArkadasEkle")
    Call<ArkadasEkleModelCB> arkadasekleServis(@Body ArkadasEkleModel arkadasEkleModel);
}
