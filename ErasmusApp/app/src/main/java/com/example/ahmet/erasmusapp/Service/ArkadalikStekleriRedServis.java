package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.ArkadaslikStekleriOnaylaModel;
import com.example.ahmet.erasmusapp.Model.ArkadaslikStekleriOnaylaModelCb;
import com.example.ahmet.erasmusapp.Model.ArkadaslikStekleriREDCbModel;
import com.example.ahmet.erasmusapp.Model.ArkadaslikStekleriREDModel;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Yenen on 25.12.2016.
 */
public interface ArkadalikStekleriRedServis {
    @POST("/api/ArkadaslikIStekleri/REd")
    Call<ArkadaslikStekleriREDCbModel> redServis(@Body ArkadaslikStekleriREDModel arkadaslikStekleriREDModel);
}
