package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.ArkadaslikStekleriOnaylaModel;
import com.example.ahmet.erasmusapp.Model.ArkadaslikStekleriOnaylaModelCb;
import com.example.ahmet.erasmusapp.Model.YorumGonderModel;
import com.example.ahmet.erasmusapp.Model.YorumGonderModelCb;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Yenen on 24.12.2016.
 */
public interface ArkadaslikStekleriOnaylaServis {
    @POST("/api/ArkadaslikIStekleri/Onayla")
    Call<ArkadaslikStekleriOnaylaModelCb> onaylaServis(@Body ArkadaslikStekleriOnaylaModel arkadaslikStekleriOnaylaModel);

}
