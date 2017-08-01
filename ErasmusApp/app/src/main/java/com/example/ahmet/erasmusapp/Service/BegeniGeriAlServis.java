package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.BegeniGeriAlModel;
import com.example.ahmet.erasmusapp.Model.BegeniGeriAlModelCb;
import com.example.ahmet.erasmusapp.Model.BegeniYollaModel;
import com.example.ahmet.erasmusapp.Model.BegeniYollaModelCb;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Yenen on 3.2.2017.
 */
public interface BegeniGeriAlServis {
    @POST("/api/PostbegeniGerial")
    Call<BegeniGeriAlModelCb> begenigerialServis(@Body BegeniGeriAlModel begeniGeriAlModel);

}
