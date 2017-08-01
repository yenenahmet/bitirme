package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.ArkadaslıktanCıkarModel;
import com.example.ahmet.erasmusapp.Model.ArkadaslıktanCıkarModelCB;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Yenen on 18.2.2017.
 */
public interface ArkadasliktanCıkarServis {
    @POST("/api/PostArkadaslıktanCıkar")
    Call<ArkadaslıktanCıkarModelCB> arkadasliktanCıkarServis(@Body ArkadaslıktanCıkarModel arkadaslıktanCıkarModel);
}
