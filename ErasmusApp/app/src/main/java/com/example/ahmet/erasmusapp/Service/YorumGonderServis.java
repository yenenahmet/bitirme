package com.example.ahmet.erasmusapp.Service;


import com.example.ahmet.erasmusapp.Model.YorumGonderModel;
import com.example.ahmet.erasmusapp.Model.YorumGonderModelCb;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Yenen on 30.11.2016.
 */
public interface YorumGonderServis {
    @POST("/api/sp_GörevTamamlamaPostYorum_iceAktarma")
    Call<YorumGonderModelCb> yorumtask(@Body YorumGonderModel yorumGonderModel);

}
