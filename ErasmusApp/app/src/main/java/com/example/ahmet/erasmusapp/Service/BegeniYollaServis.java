package com.example.ahmet.erasmusapp.Service;


import com.example.ahmet.erasmusapp.Model.BegeniYollaModel;
import com.example.ahmet.erasmusapp.Model.BegeniYollaModelCb;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Yenen on 7.1.2017.
 */
public interface BegeniYollaServis {
    @POST("/api/Postbegeni")
    Call<BegeniYollaModelCb> begeniyollaServis(@Body BegeniYollaModel begeniYollaModel);

}
